package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DrivetrainSubsystem extends SubsystemBase {
  private WPI_TalonFX leftFalcon1, leftFalcon2, rightFalcon1, rightFalcon2;  
  
  // The robot's drive
  public final DifferentialDrive m_drive;

  private DriveMode driveMode;

  public enum DriveMode {
      TANK, CHEEZY, ARCADE;
  }

  public DrivetrainSubsystem() {

      leftFalcon1 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_1);
      leftFalcon2 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_2);
      leftFalcon3 = new WPI_TalonFX(DriveConstants.LEFT_FALCON_3);
      rightFalcon1 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_1);
      rightFalcon2 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_2);
      rightFalcon3 = new WPI_TalonFX(DriveConstants.RIGHT_FALCON_3);
      
      leftFalcon2.follow(leftFalcon1);
      leftFalcon3.follow(leftFalcon1);

      rightFalcon2.follow(rightFalcon1);
      rightFalcon2.follow(rightFalcon1);
  }


}