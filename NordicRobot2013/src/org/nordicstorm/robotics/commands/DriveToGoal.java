package org.nordicstorm.robotics.commands;
 
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.nordicstorm.robotics.Robot;
import org.nordicstorm.robotics.RobotMap;
 
/**
 * Drive to the goal using vision tracking.
 */
public class DriveToGoal extends Command {
    
    private double m_lastGyroTarget;
    private double m_numberOfSeconds = 2;
    private double m_speed = -1;
    private double m_stopPixel = 130;
    private double m_wheelieStartTime;
    /**
     * Construct an instance of the DriveToGoal command
     * with the default time.
     */
    public DriveToGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    
    /**
     * Construct an instance of the DriveToGoal command
     * with a custom time.
     */
    public DriveToGoal(double numberOfSeconds, double speed, double stopPixel, double ratio) {
        this(); // chain to parameter-less constructor
        m_numberOfSeconds = numberOfSeconds;
        m_speed = speed;
        m_stopPixel = stopPixel;
        Robot.vision.setRatio(ratio);
    }
    
    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        setTimeout(m_numberOfSeconds);
        Robot.driveTrain.setSetpointRelative(Robot._gyroTarget);
        m_lastGyroTarget = Robot._gyroTarget;
        Robot.driveTrain.setPidMode(Robot.driveTrain.DRIVE_TO_ANGLE_MODE);
        Robot.driveTrain.setPidDrivespeed(m_speed);
        Robot.driveTrain.enable();
        m_wheelieStartTime = Timer.getFPGATimestamp();
        System.out.println("InitDriveSpeed = " + m_speed);
    }
    
    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        if (Robot._gyroTarget != m_lastGyroTarget) {
            System.out.println("** gyro target = " + Robot._gyroTarget + " current angle = " + RobotMap.driveTrainGyro.getAngle());
            Robot.driveTrain.setSetpoint(Robot._gyroTarget + RobotMap.driveTrainGyro.getAngle());
            m_lastGyroTarget = Robot._gyroTarget;
        }
        
    }
    
    /**
     * Make this return true when this Command no longer needs to run execute().
     */
    protected boolean isAtTarget() {
        return Robot._bottomPixel < m_stopPixel;
    }
    
    protected boolean isStableState() {
        return Timer.getFPGATimestamp() - m_wheelieStartTime > m_numberOfSeconds/4.0;
    }
    protected boolean isFinished() {
        System.out.println(isTimedOut() + " " + isAtTarget() + " " + isStableState() + " " + Timer.getFPGATimestamp());
        return isTimedOut() || (isAtTarget() && isStableState());
    }
    /**
     * Called once after isFinished returns true.
     */
    protected void end() {
        Robot.driveTrain.voltageDrive(0, 0);
        Robot.driveTrain.disable(); // Disable the PID
    }
    
    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    protected void interrupted() {
        end();
    }
}
