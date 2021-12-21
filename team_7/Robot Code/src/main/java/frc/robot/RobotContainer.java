package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeCommandGroup;
import frc.robot.commands.IntakeStopCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class RobotContainer {
  Joystick driverstation;
  DrivetrainSubsystem drivetrainSubsystem;
  IntakeSubsystem intakeSubsystem;

  public RobotContainer() {
    driverstation = new Joystick(0);
    drivetrainSubsystem = new DrivetrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    configureButtonBindings();
    drivetrainSubsystem.setDefaultCommand(
        new RunCommand(() -> drivetrainSubsystem.drive(-driverstation.getRawAxis(0), -driverstation.getRawAxis(2)),
            drivetrainSubsystem));
  }

  private void configureButtonBindings() {
    JoystickButton button1 = new JoystickButton(driverstation, 1);
    button1.whenPressed(new IntakeCommand(intakeSubsystem));

    JoystickButton button2 = new JoystickButton(driverstation, 2);
    button2.whenPressed(new IntakeStopCommand(intakeSubsystem));

    JoystickButton button3 = new JoystickButton(driverstation, 3);
    button3.whenPressed(new IntakeCommandGroup(intakeSubsystem, drivetrainSubsystem));

    JoystickButton autoButton = new JoystickButton(driverstation, 6);
    autoButton.whenPressed(new SequentialCommandGroup(
        new RunCommand(() -> drivetrainSubsystem.drive(.4, .4), drivetrainSubsystem).withTimeout(2),
        new RunCommand(() -> drivetrainSubsystem.drive(.2, -.2), drivetrainSubsystem).withTimeout(2),
        new RunCommand(() -> drivetrainSubsystem.drive(.4, .4), drivetrainSubsystem).withTimeout(2),
        new RunCommand(() -> drivetrainSubsystem.drive(-.2, .2), drivetrainSubsystem).withTimeout(2),
        new RunCommand(() -> drivetrainSubsystem.drive(.4, .4), drivetrainSubsystem).withTimeout(2),
        new RunCommand(() -> drivetrainSubsystem.drive(.2, -.2), drivetrainSubsystem).withTimeout(2),
        new RunCommand(() -> drivetrainSubsystem.drive(.4, .4), drivetrainSubsystem).withTimeout(2)));

  }

}
