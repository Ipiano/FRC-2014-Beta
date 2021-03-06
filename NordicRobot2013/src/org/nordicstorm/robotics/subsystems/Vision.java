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
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.nordicstorm.robotics.Robot;
import org.nordicstorm.robotics.RobotMap;
import org.nordicstorm.robotics.Target;
import org.nordicstorm.robotics.commands.*;
/**
 *
 */
public class Vision extends Subsystem {
    
    private boolean m_visionWidgetMissingReported = false;
    private double m_lastAngleOffset = Double.MAX_VALUE;
    private boolean m_noTargetDetectedReported = false;
    private double m_ratio;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Servo cameraServo = RobotMap.visionCameraServo;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TrackGoal());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }
    
    
    public void trackTarget(){
        
        Target targetFromDashboard = null;
        String visionData = SmartDashboard.getString("VisionData", null);
        if (visionData == null) {
            // Only report error once
            if (!m_visionWidgetMissingReported) {
                System.out.println("VisionData is empty, does the SmartDashboard have the vision widget?");
                m_visionWidgetMissingReported = true;
            }
            Robot._gyroTarget = 0;
        }
        else if  (visionData.length() != 0) {
            //System.out.println("Vision Data : " + visionData);
            m_noTargetDetectedReported = false;
            targetFromDashboard = Target.loadFromString(visionData);
            double newAngleOffset = targetFromDashboard.getCenterAngleOffset() + m_ratio*(targetFromDashboard.getRightAngleOffset() - targetFromDashboard.getCenterAngleOffset());
            //System.out.println("Vision::" + targetFromDashboard.getBoundingRectBottom());
            // Only report angle offset when it changes
            if (((int)m_lastAngleOffset) != ((int)newAngleOffset)) {
                System.out.println("Target Angle Offset (From Dashboard) : " + targetFromDashboard.getCenterAngleOffset());
                m_lastAngleOffset = newAngleOffset;
            }
            Robot._gyroTarget = newAngleOffset;
            Robot._bottomPixel = targetFromDashboard.getBoundingRectBottom();
            Robot._topPixel = targetFromDashboard.getBoundingRectTop();
        }
        else {
            // No targets are being tracked
            if (!m_noTargetDetectedReported) {
                System.out.println("No target is detected.");
                m_noTargetDetectedReported = true;
            }
            Robot._gyroTarget = 0;
        }
    }
    public void updateStatus(){
        if (SmartDashboard.getBoolean("PrintDebug")) {
            //Put temporary print statements here, they can be turned off by
            //the PrintDebug checkbox on SmartDashboard
            trackTarget();
        }
    }
    
    public void setRatio(double ratio){
        m_ratio = ratio;
    }
}
       
