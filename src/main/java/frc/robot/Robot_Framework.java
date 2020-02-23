package frc.robot;

import frc.robot.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public interface Robot_Framework extends Constants {

    // Xbox Controllers
    XboxController driveBox = new XboxController(1);

    // Compressor
    Compressor compressor = new Compressor(0);

    // Drive
    WPI_TalonFX fLeft = new WPI_TalonFX(front_left_drive);
    WPI_TalonFX fRight = new WPI_TalonFX(front_right_drive);
    WPI_TalonFX bLeft = new WPI_TalonFX(back_left_drive);
    WPI_TalonFX bRight = new WPI_TalonFX(back_right_drive);

    SpeedControllerGroup left = new SpeedControllerGroup(fLeft, bLeft);
    SpeedControllerGroup right = new SpeedControllerGroup(fRight, bRight);

    DifferentialDrive tank = new DifferentialDrive(left, right);

    DoubleSolenoid gearSole = new DoubleSolenoid(2, 3);

    Drive drive = new Drive();

    // Intake
    TalonSRX rightIntake = new TalonSRX(right_intake);
    TalonSRX leftIntake = new TalonSRX(left_intake);

    DoubleSolenoid intakePosition = new DoubleSolenoid(0, 1);

    // Shooter

    // Vertical Agitator
    WPI_TalonFX verticalAgitator = new WPI_TalonFX(vertical_agitator);

    // Horizontal Agitator
    WPI_TalonFX horizontalAgitator = new WPI_TalonFX(horizontal_agitator);


}