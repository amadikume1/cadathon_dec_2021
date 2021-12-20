// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    WPI_TalonFX leftMotor1, leftMotor2, rightMotor1, rightMotor2;

    private Joystick joystick1 = new Joystick(0); // 0 is the port number

    private double startTime; // for keeping track of time in autonomous

    private RobotContainer m_robotContainer;



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
      leftMotor1 = new WPI_TalonFX(0); // declare motors
      leftMotor2 = new WPI_TalonFX(1);
      rightMotor1 = new WPI_TalonFX(2);
      rightMotor2 = new WPI_TalonFX(3);
      m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 3) { // 3 for 3 seconds.
        leftMotor1.set(0.6); // movement of the motors
        leftMotor2.set(0.6); // running them at 60% power, 1 is 100% and 0 is 0%
        rightMotor1.set(-0.6); // the right motors are negative because they are opposite the left motors, but we still want them to turn the same direction so the robot goes forward/backward instead of spinning.
        rightMotor2.set(-0.6);
    } else {
        leftMotor1.set(0);
        leftMotor2.set(0); // running them at 0% power
        rightMotor1.set(0); // the right motors are negative because they are opposite the left motors, but we still want them to turn the same direction so the robot goes forward/backward instead of spinning.
        rightMotor2.set(0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {  // TeleopPeriodic runs during teleop
        double speed = -joystick1.getRawAxis(1) - 0.6; // 1 is the axis number for power
        double turn = joystick1.getRawAxis(2) - 0.3; // 2 (in this case) is the axis number for turning

        double left = speed + turn; // This is cheesy drive
        double right = speed - turn;

      leftMotor1.set(left); // the actual movement of the motors :)
      leftMotor2.set(left);
      rightMotor1.set(-right); // the right motors are negative because they are opposite the left motors, but we still want them to turn the same direction so the robot goes forward/backward instead of spinning.
      rightMotor2.set(-right);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
