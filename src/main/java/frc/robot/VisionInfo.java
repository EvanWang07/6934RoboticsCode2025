package frc.robot;

import edu.wpi.first.math.util.Units;

import frc.robot.LimelightHelpers;
import frc.robot.Constants.Vision;
import frc.robot.Constants;

public final class VisionInfo {
    private static double tx;
    private static double ty;
    private static boolean tv;
    private static double ta;

    public static double getTX() { // Gets the horizontal angle error
        tx = LimelightHelpers.getTX(Vision.limelightName);
        return tx;
    }

    public static double getTY() { // Gets the vertical angle error
        ty = LimelightHelpers.getTY(Vision.limelightName);
        return ty;
    }

    public static boolean hasValidTargets() { // Determines if there is a valid limelight target
        tv = LimelightHelpers.getTV(Vision.limelightName);
        return tv;
    }

    public static double getTA() { // Gets the % of the camera frame the target takes up
        ta = LimelightHelpers.getTA(Vision.limelightName);
        return ta;
    }

    public static double getDistance(double targetHeight) { // Only works for a two-dimensional scenario with flat ground; gets the distance from the target
        double angleInRadians = Units.degreesToRadians(Vision.limelightAngle + getTY());
        double distance = (Vision.limelightHeight - targetHeight) / Math.tan(angleInRadians);
        return distance;
    }
}
