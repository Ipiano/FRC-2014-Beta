/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    RobotDrive chassis;
    Joystick joystick = new Joystick(1);
    
    public void robotInit(){
        try{
            chassis = new RobotDrive(new CANJaguar(3),new CANJaguar(2),new CANJaguar(5),new CANJaguar(4));
        }catch(CANTimeoutException ex){
            ex.printStackTrace();
        }
        
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        if(null != chassis){
            chassis.setSafetyEnabled(false);
            chassis.drive(-.5,0);
            Timer.delay(2.0);
            chassis.drive(0,0);
        }else{
            System.out.println("Chassis not found for autonomous");
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        chassis.setSafetyEnabled(true);
        while(isOperatorControl() && isEnabled() && null != chassis){
            chassis.arcadeDrive(joystick.getY(), joystick.getZ());
            Timer.delay(0.01);
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
