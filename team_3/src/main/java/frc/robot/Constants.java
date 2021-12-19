// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    public static final class DriveConstants {
        public static final int LEFT_FALCON_1 = 0;
        public static final int LEFT_FALCON_2 = 1;
        public static final int LEFT_FALCON_3 = 2;
        public static final int RIGHT_FALCON_1 = 3;
        public static final int RIGHT_FALCON_2 = 4;
        public static final int RIGHT_FALCON_3 = 5;
        public static final double DEADBAND = 0.02; // 0.0-1.0 value to reduce possible joystick drift
        public static final int[] SHIFTING_SOLENOID = {0, 1};
        public static final double WHEEL_DIAM = 4.0; // inches
        public static final double CYCLES_PER_REV = 2048.0; // falcon 500 CPR
        public static final double DISTANCE_PER_PULSE = WHEEL_DIAM * Math.PI / CYCLES_PER_REV;
        public static final double HIGH_GEAR_RATIO = 5.88; // JVN calculation
        public static final double LOW_GEAR_RATIO = 11.85; // JVN calculation
    }

    public static final class OIConstants {
        public static final int DRIVER_STATION_JOY = 0;
    }
}
