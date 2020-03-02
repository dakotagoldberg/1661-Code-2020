/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Robot_Framework;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements Robot_Framework {
  
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  
  boolean intakeLowered;
  boolean agitatorReverse;

  double startTime;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {


    // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    // m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    gearSole.set(DoubleSolenoid.Value.kForward);
    // m_autoSelected = m_chooser.getSelected();
    // // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    // System.out.println("Auto selected: " + m_autoSelected);
    startTime = Timer.getFPGATimestamp();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    double time = Timer.getFPGATimestamp();

    double diff = time - startTime;

    // if (diff < 2) {
    //   bRight.set(ControlMode.PercentOutput, .4);
    //   fRight.set(ControlMode.PercentOutput, .4);
    //   bLeft.set(ControlMode.PercentOutput, -.4);
    //   fLeft.set(ControlMode.PercentOutput, -.4);
    // auto.execute(.4);
    // }

    if (diff < 3) {
      shooterRight.set(ControlMode.PercentOutput, 1);
      shooterLeft.set(ControlMode.PercentOutput, -1);
      kicker.set(ControlMode.PercentOutput, -1);
    }
    if (diff > 3 && diff < 8) {
      verticalAgitator.set(ControlMode.PercentOutput, .4);
      shooterRight.set(ControlMode.PercentOutput, 1);
      shooterLeft.set(ControlMode.PercentOutput, -1);
      kicker.set(ControlMode.PercentOutput, -1);
  
    }
    if (diff > 8 && diff < 10) {
      bRight.set(ControlMode.PercentOutput, .4);
      fRight.set(ControlMode.PercentOutput, .4);
      bLeft.set(ControlMode.PercentOutput, -.4);
      fLeft.set(ControlMode.PercentOutput, -.4);
    }
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }
  }

  @Override
  public void teleopInit() {

    intakeLowered = false;
    agitatorReverse = false;
    gearSole.set(DoubleSolenoid.Value.kForward);

    // leftIntake.configContinuousCurrentLimit(intake_continuous_current);
    // rightIntake.configContinuousCurrentLimit(intake_continuous_current);
    // leftIntake.configPeakCurrentLimit(intake_peak_current);
    // rightIntake.configPeakCurrentLimit(intake_peak_current);
    // kicker.configContinuousCurrentLimit(intake_continuous_current);
    // kicker.configPeakCurrentLimit(intake_peak_current);

    // horizontalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    // verticalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    // shooterLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    // shooterRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));

    horizontalAgitator.setNeutralMode((NeutralMode.Coast));
    verticalAgitator.setNeutralMode((NeutralMode.Coast));
    shooterLeft.setNeutralMode((NeutralMode.Coast));
    shooterRight.setNeutralMode((NeutralMode.Coast));

    turret.setSelectedSensorPosition(0);
    

  }

  @Override
  public void teleopPeriodic() {

    drive.executeTank();

    // Shifting Gears --> BUMPERS
    if (driveBox.getRawButton(left_bumper)) { 
      gearSole.set(DoubleSolenoid.Value.kReverse); // High Gear
    } 
    else if (driveBox.getRawButton(right_bumper)) {
      gearSole.set(DoubleSolenoid.Value.kForward); // Low Gear
    }

    // Raising/Lowering Intake --> A BUTTON
    if (driveBox.getRawButtonPressed(a_button) || mechBox.getRawButtonPressed(a_button)) {
      intakeLowered = !intakeLowered;
    }

    // Reversing Agitators --> Left Stick BUTTON
    if (mechBox.getRawButtonPressed(left_stick_button)) {
      agitatorReverse = !agitatorReverse;
    }

    // int aRev; // Used as a multiplier for agitator reverse

    // if(agitatorReverse) {
    //   aRev = -1;
    // }
    // else {
    //   aRev = 1;
    // }


    if (agitatorReverse) {
      mechBox.setRumble(RumbleType.kRightRumble, 1);
      mechBox.setRumble(RumbleType.kLeftRumble, 1);
    }
    else {
      mechBox.setRumble(RumbleType.kRightRumble, 0);
      mechBox.setRumble(RumbleType.kLeftRumble, 0);
    } 
 
    if (intakeLowered) {
      intakePosition.set(DoubleSolenoid.Value.kForward);
      // intake.lower();
    } 
    else if (!intakeLowered) {
      intakePosition.set(DoubleSolenoid.Value.kReverse);
      // intake.raise();
    }

    // Intake -- holding B button
    if (mechBox.getRawButton(b_button)) {
      rightIntake.set(ControlMode.PercentOutput, .5);
      leftIntake.set(ControlMode.PercentOutput, -.5);
      // intake.spin(.5);
    }
    else if (mechBox.getRawButton(y_button)) {
      // Running Intake in Reverse
      rightIntake.set(ControlMode.PercentOutput, -.5);
      leftIntake.set(ControlMode.PercentOutput, .5);
      // intake.spin(-.5);
    }
    else {
      rightIntake.set(ControlMode.PercentOutput, 0);
      leftIntake.set(ControlMode.PercentOutput, 0);
      // intake.spin(0);
    }

    // Vertical Agitator

    double va = mechBox.getRawAxis(right_trigger);
    if (va > .1 && !agitatorReverse){
      verticalAgitator.set(ControlMode.PercentOutput, va *.4);
      // vAgitator.spin(va);
    }
    else if (va > .1 && agitatorReverse){
      verticalAgitator.set(ControlMode.PercentOutput, -va * .4);
      // vAgitator.spin(-va);
    }
    else {
      verticalAgitator.set(ControlMode.PercentOutput, 0);
      // vAgitator.spin(0);
    }

 

    // HA
    double ha = mechBox.getRawAxis(left_trigger);
    if (ha > .1 && !agitatorReverse){
      horizontalAgitator.set(ControlMode.PercentOutput, -ha * .2);
      // hAgitator.spin(ha);
    }
    else if (ha > .1 && agitatorReverse){
      horizontalAgitator.set(ControlMode.PercentOutput, ha * .2);
      // hAgitator.spin(-ha);
    } 
    else {
      horizontalAgitator.set(ControlMode.PercentOutput, 0);
      // hAgitator.spin(0);
    }

    // Shooting (very basic) -- calculate later
    if (mechBox.getXButton() || mechBox.getRawButton(right_bumper)) {
      shooterRight.set(ControlMode.PercentOutput, 1);
      shooterLeft.set(ControlMode.PercentOutput, -1);
      kicker.set(ControlMode.PercentOutput, -1);
    }
    else if (mechBox.getRawButton(left_bumper)) {
      shooterRight.set(ControlMode.PercentOutput, .5);
      shooterLeft.set(ControlMode.PercentOutput, -.5);
      kicker.set(ControlMode.PercentOutput, -.5);
    }
    else {
      shooterRight.set(ControlMode.PercentOutput, 0);
      shooterLeft.set(ControlMode.PercentOutput, 0);
      kicker.set(ControlMode.PercentOutput, 0);
    }

   
    // TURRET

    // if (driveBox.getRawButton(b_button)) {
    //   turret.set(ControlMode.PercentOutput, 0.1);
    // }
    // else if (driveBox.getRawButton(x_button)) {
    //   turret.set(ControlMode.PercentOutput, -0.1);
    // } 
    // else {
    //   turret.set(ControlMode.PercentOutput, 0);
    // }

    double turretStick = mechBox.getRawAxis(4);

    if (Math.abs(turretStick) > .1) {
      turret.set(ControlMode.PercentOutput, turretStick * 1);
    }
    else {
      turret.set(ControlMode.PercentOutput, 0);
    
    System.out.println(turret.getSelectedSensorPosition());
    System.out.println("Turret Degree: " + (turret.getSelectedSensorPosition() / one_turret_degree));
    
  }


    
    

    




    
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testInit() {
    
      }

   
  @Override
  public void testPeriodic() {


  }
}

