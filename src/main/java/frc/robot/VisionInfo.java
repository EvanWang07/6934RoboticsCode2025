package frc.robot;

import edu.wpi.first.math.util.Units;

import frc.robot.Constants.Vision;
import frc.robot.LimelightHelpers;

public final class VisionInfo {
    private static double tx;
    private static double ty;
    private static boolean tv;
    private static double ta;

    public static double getTX(boolean asOutput) { // Gets the horizontal angle error
        tx = LimelightHelpers.getTX(Vision.limelightName);
        if (asOutput) {
            tx /= 31;
        }
        return tx;
    }

    public static double getTY(boolean asOutput) { // Gets the vertical angle error
        ty = LimelightHelpers.getTY(Vision.limelightName);
        if (asOutput) {
            ty /= 31;
        }
        return ty;
    }

    public static boolean hasValidTargets() { // Determines if there is a valid limelight target
        tv = LimelightHelpers.getTV(Vision.limelightName);
        return tv;
    }

    public static double getTA(boolean asOutput) { // Gets the % of the camera frame the target takes up
        ta = LimelightHelpers.getTA(Vision.limelightName);
        if (asOutput) {
            ta /= 100;
        }
        return ta;
    }

    public static void switchPipeline(int newPipeline) { // Swaps the camera "mode" (used if there are multiple targets)
        LimelightHelpers.setPipelineIndex(Vision.limelightName, newPipeline);
    }

    public static double getDistance(double targetHeight) { // Only works for a two-dimensional scenario with flat ground; gets the distance from the target
        double angleInRadians = Units.degreesToRadians(Vision.limelightAngle + getTY(false));
        double distance = (Vision.limelightHeight - targetHeight) / Math.tan(angleInRadians);
        return distance;
    }

    public static boolean isHorizontallyAligned() {
        boolean aligned = Math.abs(getTX(false)) < Vision.TXTolerance;
        return aligned;
    }

    public static boolean isVerticallyAligned() {
        boolean aligned = Math.abs(getTY(false)) < Vision.TYTolerance;
        return aligned;
    }

    public static double getRotationalCorrectionOutput() {
        double correctionOutput = getTX(true) * Vision.visionAngleKP;
        return correctionOutput;
    }

    public static double getTranslationalCorrectionOutput() {
        double correctionOutput = getTY(true) * Vision.visionTranslationKP;
        return correctionOutput;
    }
}
