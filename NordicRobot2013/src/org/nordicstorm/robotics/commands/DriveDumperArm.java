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
import edu.wpi.first.wpilibj.command.Command;
import org.nordicstorm.robotics.Robot;
/**
 *
 */
public class  DriveDumperArm extends Command {
    public DriveDumperArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        if (Robot.dumper != null) {
            requires(Robot.dumper.getArm());
        }
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.dumper.getArm().disable();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.climberActuator.getPot() <= Robot.climberActuator.getVerticalSetting() + 10){
            Robot.dumper.getArm().driveMotor(Robot.oi.getLeftY());
            System.out.println("Driving Dumper Arm "+ Robot.oi.getLeftY());            
        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.dumper.getArm().driveMotor(0);
        Robot.dumper.getArm().setSetpoint(Robot.dumper.getArm().getPot());
        Robot.dumper.getArm().enable();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
