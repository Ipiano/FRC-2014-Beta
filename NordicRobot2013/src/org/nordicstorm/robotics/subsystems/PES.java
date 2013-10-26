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
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class PES extends Subsystem {
    private double m_currentSet = Preferences.getInstance().getDouble("PESClose", 0);
    private boolean m_hasReleased = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Servo servo = RobotMap.pESServo;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        /*
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new KeepPESUnengaged());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	*/
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void releasePES(){
        if (! m_hasReleased){
            double setpoint = Preferences.getInstance().getDouble("PESOpen", 0);
            servo.set(1);
            m_currentSet = setpoint;
            System.out.println("Open Pes");
            m_hasReleased = true;
        }
    }
    
    public void releasePES(boolean override){
        if(override){
            m_hasReleased = false;
        }
        releasePES();
    }
    public void resetPES(){
        m_hasReleased = false;
        double setpoint = Preferences.getInstance().getDouble("PESClose", 0);
        servo.set(0);
        m_currentSet = setpoint;
    }
    public void stopServo(){
        servo.set(0.5);
    }
    public void keepPES(){
        servo.setAngle(m_currentSet);
        System.out.println("Keep Pes");
    }
}