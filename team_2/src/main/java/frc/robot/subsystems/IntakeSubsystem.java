package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.util.WPILibVersion;

public class IntakeSubsystem extends SubsystemBase{

    public enum ClawStatus{
        OPEN, CLOSED
    }

    public ClawStatus clawStatus;

    private WPI_TalonFX spinRollerRight, spinRollerLeft, centeringRollers;
    private DoubleSolenoid pistons;

    public IntakeSubsystem() {
        pistons = new DoubleSolenoid(IntakeConstants.SOLENOID_CLAW[0], IntakeConstants.SOLENOID_CLAW[1]);
        spinRollerRight = new WPI_TalonFX(IntakeConstants.SPIN_FALCON_1);
        spinRollerLeft = new WPI_TalonFX(IntakeConstants.SPIN_FALCON_2);
        centeringRollers = new WPI_TalonFX(IntakeConstants.CENTERING_FALCON_1);
    }
    
}
