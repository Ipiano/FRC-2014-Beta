// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.nordicstorm.robotics.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.nordicstorm.robotics.Robot;
/**
 *
 */
/*public class  ApproachGoal extends Command {
    private final int BOTTOM_TARGET = 162;
    private double m_speed;
    private double m_brakeStart;
    public ApproachGoal(double speed) {
	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	m_speed = Math.abs(speed);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    
    public ApproachGoal(){
        this(4.5);
    }
    protected void initialize() {
    }
    protected void execute() {
        double speed = m_speed;
         if (Robot._bottomPixel < BOTTOM_TARGET + 3 && Robot._bottomPixel > BOTTOM_TARGET -3){
            speed = 0;
            if (m_brakeStart == 0){
                m_brakeStart = Timer.getFPGATimestamp();
            }
         }else {
            if (Robot._bottomPixel > BOTTOM_TARGET + 3){
                speed = -speed;
            }
            if (m_brakeStart != 0){
                m_brakeStart = 0;
            }
        }
        Robot.driveTrain.voltageDrive(speed, speed);
    }
    protected boolean isFinished() {
        return Robot._bottomPixel < BOTTOM_TARGET + 3 && Robot._bottomPixel > BOTTOM_TARGET -3 && Timer.getFPGATimestamp()-m_brakeStart > 0.5 ;
    }
    protected void end() {
        Robot.driveTrain.voltageDrive(0, 0);
    }
    protected void interrupted() {
        end();
    }
}*/
public class  ApproachGoal extends Command {
    public final double DISTANCE_TO_GOAL = 16.5;
    public ApproachGoal() {
	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    protected void initialize() {
        Robot.driveTrain.setPidMode(Robot.driveTrain.DRIVE_TO_DISTANCE_MODE);
        Robot.driveTrain.setSetpoint(DISTANCE_TO_GOAL);
        Robot.driveTrain.getPIDController().reset();
        Robot.driveTrain.enable();
    }
    protected void execute() {
    }
    protected boolean isFinished() {
        return Robot.driveTrain.onTarget();
    }
    protected void end() {
        Robot.driveTrain.voltageDrive(0, 0);
        Robot.driveTrain.disable();
    }
    protected void interrupted() {
        end();
    }
}
