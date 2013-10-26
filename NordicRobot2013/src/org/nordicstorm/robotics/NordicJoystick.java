    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nordicstorm.robotics;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;

/**
 *
 * @author Andrew
 */
public class NordicJoystick extends GenericHID {
    private GenericHID _stick = null;
    private boolean _isInvertedX = false;
    private boolean _isInvertedY = false;
    private boolean _isInvertedZ = false;
    private boolean _isThrottled = false;
    private boolean _isMergedXandZ = false;
    private boolean _isTurnDisabled = false;
    
    public static final int kDefaultXAxis = 1;
    public static final int kDefaultYAxis = 2;
    public static final int kDefaultZAxis = 3;
    public static final int kDefaultTwistAxis = 3;
    public static final int kDefaultThrottleAxis = 4;
    
    public final double TURN_LOCK_THRESHOLD = 0.25;
    
    public NordicJoystick(Joystick stick) {
        _stick = stick;
    }

    public double getX(Hand hand) {
        return adjustValue(_stick.getX(), _isInvertedX, _isThrottled);
    }

    public double getY(Hand hand) {
        return adjustValue(_stick.getY(), _isInvertedY, _isThrottled);
    }

    public double getZ(Hand hand) {
        return adjustValue(_stick.getZ(), _isInvertedZ, _isThrottled);    
    }

    public double getTwist() {
        return _stick.getTwist();
    }

    public double getThrottle() {
        return _stick.getThrottle();
    }

    
    /*
     * Most important method, controls driving
     */
    public double getRawAxis(int which) {
        boolean isInverted = false;
        boolean isThrottled = true;
        
        double currentXReading = _stick.getRawAxis(kDefaultXAxis);
        double currentZReading = _stick.getRawAxis(kDefaultZAxis);
        double currentYReading = _stick.getRawAxis(kDefaultYAxis);
        
        
        /*
         * Whether the axis is reversed or not is set by the class that created a Joystick object
         */
        switch(which) {
            case kDefaultXAxis:
                isInverted = _isInvertedX;
                break;
            case kDefaultYAxis:
                isInverted = _isInvertedY;
                break;
            case kDefaultZAxis:
                isInverted = _isInvertedZ;
                break;
        }
 
        double currentReading = 0;
        
        //Check if the X and Z axis can both be used for turning
        if (_isMergedXandZ && (which == kDefaultXAxis || which == kDefaultZAxis)) {
            
            //If the X axis is being used, use that
            if (Math.abs(currentXReading) > TURN_LOCK_THRESHOLD){
                currentReading = currentXReading/1.5;
                isThrottled = false;
                
            //Otherwise, use the Z axis
            }else{
                currentReading = currentZReading*Preferences.getInstance().getDouble("MaxTurnSpeed", 0.8);
            }
            
        //If tuning the thumb button is pressed to disable turning, drive slightly slower
        }else if (Robot.oi.getTurnDisabled() && which == kDefaultYAxis){
                currentReading = 0.85*_stick.getRawAxis(which);
                isThrottled = false;
        }else if(which == kDefaultYAxis && Math.abs(currentXReading) > TURN_LOCK_THRESHOLD && _isMergedXandZ){
            currentReading = 0.75;
        }else{
        //Otherwise, just used the joystick value
            currentReading = _stick.getRawAxis(which);
        }
        if((which == kDefaultXAxis || which == kDefaultZAxis) && _isTurnDisabled){
            currentReading = 0;
        }
        
        
        return adjustValue(currentReading, isInverted, isThrottled, which);
    }

    public boolean getTrigger(Hand hand) {
        return _stick.getTrigger(hand);
    }

    public boolean getTop(Hand hand) {
        return _stick.getTop(hand);
    }

    public boolean getBumper(Hand hand) {
        return _stick.getBumper(hand);
    }

    public boolean getRawButton(int button) {
        return _stick.getRawButton(button);
    }
    
    public boolean getIsInvertedX() {
        return _isInvertedX;
    }
    
    public void setIsInvertedX(boolean isInverted) {
        _isInvertedX = isInverted;
    }

    public boolean getIsInvertedY() {
        return _isInvertedY;
    }
    
    public void setIsInvertedY(boolean isInverted) {
        _isInvertedY = isInverted;
    }

    public boolean getIsInvertedZ() {
        return _isInvertedZ;
    }
    
    public void setIsInvertedZ(boolean isInverted) {
        _isInvertedZ = isInverted;
    }
    
    public void setTurnDisabled(boolean isDisabled){
        _isTurnDisabled = isDisabled;
    }
    
    private double adjustValue(double value, boolean isInverted, boolean isThrottled, int which) {
        
        if (isThrottled) {
            if (which == kDefaultZAxis) {
                value = value * ((-getThrottle()+1.0)/1.25);
            } else {
                value = value * ((-getThrottle()+1.0)/2.0);
            }
        }
        
        if (isInverted) {
            return -value;
        } else {
            return value;
        }
    }
    
    private double adjustValue(double value, boolean isInverted, boolean isThrottled) {
        if (isThrottled) {            
            value = value * ((-getThrottle()+1.0)/2.0);            
        }
        
        if (isInverted) {
            return -value;
        } else {
            return value;
        }
    }
    
    public boolean getIsThrottled() {
        return _isThrottled;
    }
    
    public void setIsThrottled(boolean isThrottled) {
        _isThrottled = isThrottled;
    }

    public boolean getIsMergedXandZ() {
        return _isMergedXandZ;
    }
    
    public void setIsMergedXandZ(boolean isMergedXandZ) {
        _isMergedXandZ = isMergedXandZ;
    }

}
