// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DrivetrainSubsystem extends SubsystemBase {
    private WPI_TalonFX leftFalcon1;
    private WPI_TalonFX leftFalcon2;
    private WPI_TalonFX leftFalcon3;
    private WPI_TalonFX rightFalcon1;
    private WPI_TalonFX rightFalcon2;
    private WPI_TalonFX rightFalcon3;

    private DifferentialDrive diffDrive;

    public enum DriveMode {
        ARCADE, 
        CHEEZY, 
        TANK
    }

    private DriveMode driveMode; 

    public DrivetrainSubsystem() {
        leftFalcon1 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_1);
        leftFalcon2 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_2);
        leftFalcon3 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_3);
        rightFalcon1 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_1);
        rightFalcon2 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_2);
        rightFalcon3 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_3);

        // have motors follow first motor on either side
        leftFalcon2.follow(leftFalcon1);
        leftFalcon3.follow(leftFalcon1);
        leftFalcon2.setInverted(InvertType.FollowMaster); // prevents fighting from inversions
        leftFalcon3.setInverted(InvertType.FollowMaster);

        rightFalcon2.follow(rightFalcon1);
        rightFalcon3.follow(rightFalcon1);
        rightFalcon2.setInverted(InvertType.FollowMaster);
        rightFalcon3.setInverted(InvertType.FollowMaster);

        diffDrive = new DifferentialDrive(leftFalcon1, rightFalcon1);
        diffDrive.setDeadband(DriveConstants.DEADBAND); // ignores tiny movements in joysticks

        leftFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        rightFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

        leftFalcon1.setNeutralMode(NeutralMode.Brake);
        rightFalcon1.setNeutralMode(NeutralMode.Brake);
        
        resetEncoders();

        driveMode = DriveMode.TANK;
    }

    public void tankDrive(double leftPower, double rightPower) {
        diffDrive.tankDrive(leftPower, rightPower);
    }
    
    public void cheezyDrive(double straight, double turn) {
        diffDrive.curvatureDrive(straight, -turn, false);
    }
    
    public void arcadeDrive(double straight, double turn) {
        diffDrive.arcadeDrive(straight, -turn);
    }

    public void stopDrive(){
        leftFalcon1.set(ControlMode.PercentOutput, 0.0);
        rightFalcon1.set(ControlMode.PercentOutput, 0.0);
    }

    // returns distance in inches
    private double getLeftEncoder(){
        return (leftFalcon1.getSelectedSensorPosition() * DriveConstants.DISTANCE_PER_PULSE) / 
               (ShiftingSubsystem.isHighGear() ? DriveConstants.HIGH_GEAR_RATIO : DriveConstants.LOW_GEAR_RATIO);
    }

    // returns distance in inches
    private double getRightEncoder(){
        return (rightFalcon1.getSelectedSensorPosition() * DriveConstants.DISTANCE_PER_PULSE) / 
               (ShiftingSubsystem.isHighGear() ? DriveConstants.HIGH_GEAR_RATIO : DriveConstants.LOW_GEAR_RATIO);
    }

    // returns distance in inches
    public double getEncoders(){
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    private void resetEncoders(){
        leftFalcon1.setSelectedSensorPosition(0);
        rightFalcon1.setSelectedSensorPosition(0);
    }

    public DriveMode getDriveMode(){
        return driveMode;
    }
}
