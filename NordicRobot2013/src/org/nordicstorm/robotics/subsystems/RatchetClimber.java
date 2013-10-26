// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.nordicstorm.robotics.subsystems;
import org.nordicstorm.robotics.RobotMap;
import org.nordicstorm.robotics.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class RatchetClimber extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DigitalInput topSwitch = RobotMap.ratchetClimberTopSwitch;
    DigitalInput bottomSwitch = RobotMap.ratchetClimberBottomSwitch;
    CANJaguar ratchet = RobotMap.ratchetClimberRatchet;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private boolean m_startedClimb = false;
    
    public RatchetClimber(){
        try {
            ratchet.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            ratchet.enableControl();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        m_startedClimb = false;
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public boolean topIsSwitched(){
        return !topSwitch.get();
    }
    public boolean bottomIsSwitched(){
        return !bottomSwitch.get();
    }
    public void driveMotor(double speed){
        //speed = speed*12;
        try {
            ratchet.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        System.out.println("Driving Ratchet: " + speed);
    }
    public double getCurrent(){
        //System.out.println("Entering GetCurrent() "+ Timer.getFPGATimestamp());
        try {
            //System.out.println("Exiting GetCurrent() "+ Timer.getFPGATimestamp());
            return ratchet.getOutputCurrent();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        //System.out.println("Exiting GetCurrent() "+ Timer.getFPGATimestamp());
        return 0;
    }
    public void updateStatus(){
        SmartDashboard.putNumber("ClimberCurrent", getCurrent()); 
    }
    public void setStartedClimb(boolean newValue){
        m_startedClimb =newValue;
    }
    public boolean hasStartedClimbing(){
        return m_startedClimb;
    }
}