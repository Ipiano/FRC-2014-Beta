package org.nordicstorm.robotics.commands;
 
import edu.wpi.first.wpilibj.command.Command;
import org.nordicstorm.robotics.Robot;
 
/**
 * Drive in voltage mode for a set period of time.
 * You can chain these together in a CommandGroup 
 * to drive a set pattern in Autonomous.
 * 
 * Pass the desired amount of time, and how many 
 * volts to apply to each side on the constructor like this
 * 
 * public class Autonomous extends CommandGroup {
 *   
 *   public Autonomous() {
 *       addSequential(new DriveForTime(1.5, 8, 8));
 *       addSequential(new DriveToGoal());
 *   }
 * 
 */
public class DriveForTime extends Command {
    
    private double m_leftVolts = 0;
    private double m_rightVolts = 0;
    
    public DriveForTime(double time, double leftVolts, double rightVolts) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        setTimeout(time);
        m_leftVolts = leftVolts;
        m_rightVolts = rightVolts;
    }
    
    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
    }
    
    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        Robot.driveTrain.voltageDrive(m_leftVolts, m_rightVolts);
    }
    
    /**
     * Make this return true when this Command no longer needs to run execute().
     */
    protected boolean isFinished() {
        return isTimedOut();
    }
    
    /**
     * Called once after isFinished returns true.
     */
    protected void end() {
        Robot.driveTrain.voltageDrive(0, 0);
    }
    
    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    protected void interrupted() {
        end();
    }
}
