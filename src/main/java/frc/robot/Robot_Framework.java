package frc.robot;

import frc.robot.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public interface Robot_Framework extends Constants {

    // Xbox Controllers
    XboxController driveBox = new XboxController(0);
    XboxController mechBox = new XboxController(1);

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

    DoubleSolenoid gearSole = new DoubleSolenoid(0, 1);

    Drive drive = new Drive();
    AutoDrive auto = new AutoDrive();

    // Intake
    TalonSRX rightIntake = new TalonSRX(right_intake);
    TalonSRX leftIntake = new TalonSRX(left_intake);
    DoubleSolenoid intakePosition = new DoubleSolenoid(2, 3);
    Intake intake = new Intake();

    // Shooter
    WPI_TalonFX shooterRight = new  WPI_TalonFX(shooter_R);
    WPI_TalonFX shooterLeft = new  WPI_TalonFX(shooter_L);
    TalonSRX kicker = new TalonSRX(kicker_motor); // Defined constant not working.

    // Vertical Agitator
    WPI_TalonFX verticalAgitator = new WPI_TalonFX(vertical_agitator);
    Vertical_Agitator vAgitator = new Vertical_Agitator();

    // Horizontal Agitator
    WPI_TalonFX horizontalAgitator = new WPI_TalonFX(horizontal_agitator);
    Horizontal_Agitator hAgitator = new Horizontal_Agitator();

    // Climb
    DoubleSolenoid climbSole = new DoubleSolenoid(4, 5);


}