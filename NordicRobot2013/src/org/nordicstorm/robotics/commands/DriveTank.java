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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.nordicstorm.robotics.Robot;
import org.nordicstorm.robotics.RobotMap;
/**
 *
 */
public class  DriveTank extends Command {
    public DriveTank() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean inverted = Robot.oi.getIsInverted();
        double rightSpeed = DriveRobot.checkInverted(Robot.oi.getRightY(), inverted);
        double leftSpeed = DriveRobot.checkInverted(Robot.oi.getLeftY(), inverted);
   
        
        Robot.driveTrain.drive(leftSpeed, rightSpeed);
        //SmartDashboard.putString("Control Setup", "Tank");
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.oi.getDriveType() != RobotMap.DRIVE_TANK);
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.stop();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
