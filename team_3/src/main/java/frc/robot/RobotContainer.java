// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShiftingSubsystem;

public class RobotContainer {
    private Joystick driveJoystick; 
    private DrivetrainSubsystem drivetrainSubsystem;
    private ShiftingSubsystem shiftingSubsystem;

    public RobotContainer(){
        driveJoystick = new Joystick(OIConstants.DRIVER_STATION_JOY);
        drivetrainSubsystem = new DrivetrainSubsystem();
        shiftingSubsystem = new ShiftingSubsystem();

        switch (drivetrainSubsystem.getDriveMode()) {
            case TANK:
                drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.tankDrive(getLeftY(), getRightY()), drivetrainSubsystem));
                break;
            case CHEEZY:
                drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.cheezyDrive(getLeftY(), getRightX()), drivetrainSubsystem));
                break;
            case ARCADE:
                drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.arcadeDrive(getLeftY(), getRightX()), drivetrainSubsystem));
                break;
        }

        configureButtonBindings();
    }
    
    private void configureButtonBindings(){}

    public double getLeftY() {
        return -driveJoystick.getRawAxis(0);
    }

    public double getLeftX() {
        return driveJoystick.getRawAxis(1);
    }

    public double getRightY() {
        return -driveJoystick.getRawAxis(2);
    }

    public double getRightX() {
        return driveJoystick.getRawAxis(3);
    }

    private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
        new JoystickButton(joystick, button).whenPressed(command);
    }

    private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
        new JoystickButton(joystick, button).whileHeld(command);
    }

    public Command getAutonomousCommand(){
        return null;
    }
}
