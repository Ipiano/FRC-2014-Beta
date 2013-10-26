/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nordicstorm.robotics;

/**
 * This class represents a single tracked target.
 * It can be used both in the dashboard widget, and on the Robot.
 * 
 */
public class Target {

    private double m_centerOfMassX;
    private double m_centerOfMassY;
    private double m_boundingRectLeft;
    private double m_boundingRectTop;
    private double m_boundingRectRight;
    private double m_boundingRectBottom;
    private double m_equivalentRectLongSide;
    private double m_equivalentRectShortSide;
    private double m_area;

    // Computed values
    private double m_computedDistance;
    private double m_centerPixelOffset;
    private double m_centerAngleOffset;
    private double m_leftPixelOffset;
    private double m_leftAngleOffset;
    private double m_rightPixelOffset;
    private double m_rightAngleOffset;    
    
    
    public double getCenterOfMassX() {
        return m_centerOfMassX;
    }

    public void setCenterOfMassX(double value) {
        m_centerOfMassX = value;
    }

    public double getCenterOfMassY() {
        return m_centerOfMassY;
    }
    
    public void setCenterOfMassY(double value) {
        m_centerOfMassY = value;
    }
    public double getBoundingRectLeft() {
        return m_boundingRectLeft;
    }
    
    public void setBoundingRectLeft(double value) {
        m_boundingRectLeft = value;
    }
         
    public double getBoundingRectTop() {
        return m_boundingRectTop;
    }
    
    public void setBoundingRectTop(double value) {
        m_boundingRectTop = value;
    }
    
    public double getBoundingRectRight() {
        return m_boundingRectRight;
    }
    
    public void setBoundingRectRight(double value) {
         m_boundingRectRight= value;
    }    
    
    public double getBoundingRectBottom() {
        return m_boundingRectBottom ;
    }
    
    public void setBoundingRectBottom(double value) {
        m_boundingRectBottom = value;
    }

    
    
    public double getBoundingRectWidth() {
        return m_boundingRectRight - m_boundingRectLeft;
    }

    public double getBoundingRectHeight() {
        return m_boundingRectBottom - m_boundingRectTop;
    }
    
    
    public double getEquivalentRectLongSide() {
        return m_equivalentRectLongSide ;
    }
    
    public void setEquivalentRectLongSide(double value) {
         m_equivalentRectLongSide= value;
    }
    
    public double getEquivalentRectShortSide() {
        return m_equivalentRectShortSide;
    }
    
    public void setEquivalentRectShortSide(double value) {
        m_equivalentRectShortSide = value;
    }
    
    public double getArea() {
        return m_area;
    }
    
    public void setArea(double value) {
        m_area = value;
    }
    
    public double getComputedDistance() {
        return m_computedDistance;
    }

    public void setComputedDistance(double value) {
        m_computedDistance = value;
    }

    public double getCenterPixelOffset() {
        return m_centerPixelOffset;
    }

    public void setCenterPixelOffset(double value) {
        m_centerPixelOffset = value;
    }
    public double getLeftPixelOffset() {
        return m_leftPixelOffset;
    }

    public void setLeftPixelOffset(double value) {
        m_leftPixelOffset = value;
    }    
    public double getRightPixelOffset() {
        return m_rightPixelOffset;
    }

    public void setRightPixelOffset(double value) {
        m_rightPixelOffset = value;
    }
    
    public double getCenterAngleOffset() {
        return m_centerAngleOffset;
    }

    public void setCenterAngleOffset(double value) {
        m_centerAngleOffset = value;
    }
    
    public double getLeftAngleOffset() {
        return m_leftAngleOffset;
    }

    public void setLeftAngleOffset(double value) {
        m_leftAngleOffset = value;
    }
    
    public double getRightAngleOffset() {
        return m_rightAngleOffset;
    }

    public void setRightAngleOffset(double value) {
        m_rightAngleOffset = value;
    }

    public static Target loadFromString(String targetString) {
        

        Target target = new Target();

        int currentPos = 0;
        while (true) {
            int linePos = targetString.indexOf("\n", currentPos);
            if (linePos < 0) {
                break;
            }
            String line = targetString.substring(currentPos, linePos);
            int colonPos = line.indexOf(":");
            String key = line.substring(0,colonPos).trim();
            if (key.startsWith("- ")) {
                key = key.substring(2);
            }
            String value = line.substring(colonPos+1).trim();
            currentPos = linePos + 1;


            if (key.equalsIgnoreCase("CenterOfMassX")) {
                double doubleValue = Double.parseDouble(value);
                target.setCenterOfMassX(doubleValue);
            }
            else if (key.equalsIgnoreCase("CenterOfMassY")) {
                double doubleValue = Double.parseDouble(value);
                target.setCenterOfMassY(doubleValue);
            }
            else if (key.equalsIgnoreCase("BoundingRectLeft")) {
                double doubleValue = Double.parseDouble(value);
                target.setBoundingRectLeft(doubleValue);
            }
            else if (key.equalsIgnoreCase("BoundingRectTop")) {
                double doubleValue = Double.parseDouble(value);
                target.setBoundingRectTop(doubleValue);
            }
            else if (key.equalsIgnoreCase("BoundingRectRight")) {
                double doubleValue = Double.parseDouble(value);
                target.setBoundingRectRight(doubleValue);
            }
            else if (key.equalsIgnoreCase("BoundingRectBottom")) {
                double doubleValue = Double.parseDouble(value);
                target.setBoundingRectBottom(doubleValue);
            }
            else if (key.equalsIgnoreCase("EquivalentRectLongSide")) {
                double doubleValue = Double.parseDouble(value);
                target.setEquivalentRectLongSide(doubleValue);
            }
            else if (key.equalsIgnoreCase("EquivalentRectShortSide")) {
                double doubleValue = Double.parseDouble(value);
                target.setEquivalentRectShortSide(doubleValue);
            }
            else if (key.equalsIgnoreCase("Area")) {
                double doubleValue = Double.parseDouble(value);
                target.setArea(doubleValue);
            }
            else if (key.equalsIgnoreCase("CenterPixelOffset")) {
                double doubleValue = Double.parseDouble(value);
                target.setCenterPixelOffset(doubleValue);
            }else if (key.equalsIgnoreCase("CenterAngleOffset")) {
                double doubleValue = Double.parseDouble(value);
                target.setCenterAngleOffset(doubleValue);
            }else if (key.equalsIgnoreCase("LeftPixelOffset")) {
                double doubleValue = Double.parseDouble(value);
                target.setLeftPixelOffset(doubleValue);
            }else if (key.equalsIgnoreCase("LeftAngleOffset")) {
                double doubleValue = Double.parseDouble(value);
                target.setLeftAngleOffset(doubleValue);
            }else if (key.equalsIgnoreCase("RightPixelOffset")) {
                double doubleValue = Double.parseDouble(value);
                target.setRightPixelOffset(doubleValue);
            }else if (key.equalsIgnoreCase("RightAngleOffset")) {
                double doubleValue = Double.parseDouble(value);
                target.setRightAngleOffset(doubleValue);
            }else if (key.equalsIgnoreCase("ComputedDistance")) {
                double doubleValue = Double.parseDouble(value);
                target.setComputedDistance(doubleValue);
            }
        }
        return target;
    }

    public String toString() {
        
        String output = "- CenterOfMassX:" + getCenterOfMassX() +
            "\n  CenterOfMassY:" + getCenterOfMassY() +
            "\n  BoundingRectLeft: " + getBoundingRectLeft() +
            "\n  BoundingRectTop: " + getBoundingRectTop() +
            "\n  BoundingRectRight: " + getBoundingRectRight() +
            "\n  BoundingRectBottom: " + getBoundingRectBottom() +
            "\n  EquivalentRectLongSide: " + getEquivalentRectLongSide() +
            "\n  EquivalentRectShortSide: " + getEquivalentRectShortSide() +
            "\n  Area: " + getArea() +
            "\n  CenterPixelOffset: " + getCenterPixelOffset() +
            "\n  CenterAngleOffset: " + getCenterAngleOffset() +
            "\n  LeftPixelOffset: " + getLeftPixelOffset() +
            "\n  LeftAngleOffset: " + getLeftAngleOffset() +
            "\n  RightPixelOffset: " + getRightPixelOffset() +
            "\n  RightAngleOffset: " + getRightAngleOffset() +  
            "\n  ComputedDistance: " + getComputedDistance() + "\n";
        
        return output;
    }

}
