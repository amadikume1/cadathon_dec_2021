// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
    private WPI_TalonFX rightFalcon1;
    private WPI_TalonFX rightFalcon2;

    private DifferentialDrive diffDrive;

    public enum DriveMode {
        ARCADE, 
        CHEEZY, 
        TANK
    }

    public DrivetrainSubsystem() {
        leftFalcon1 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_1);
        leftFalcon2 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_2);
        rightFalcon1 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_1);
        rightFalcon2 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_2);

        // have second motor on either side follow the first motor
        leftFalcon2.follow(leftFalcon1);
        leftFalcon2.setInverted(InvertType.FollowMaster); // prevents fighting from inversions
        rightFalcon2.follow(rightFalcon1);
        rightFalcon2.setInverted(InvertType.FollowMaster);

        diffDrive = new DifferentialDrive(leftFalcon1, rightFalcon1);
        diffDrive.setDeadband(DriveConstants.DEADBAND); // ignores tiny movements in joysticks

        leftFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        rightFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        leftFalcon1.setNeutralMode(NeutralMode.Brake);
        rightFalcon1.setNeutralMode(NeutralMode.Brake);
        
        resetEncoders();
    }

    @Override
    public void periodic() {

    }

    private void resetEncoders(){
        leftFalcon1.setSelectedSensorPosition(0);
        rightFalcon1.setSelectedSensorPosition(0);
    }
}
