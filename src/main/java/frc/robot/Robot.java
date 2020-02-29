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
        fLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fLeft.configOpenloopRamp(open_ramp);

        fRight.configOpenloopRamp(open_ramp);

        compressor.setClosedLoopControl(true);

        fLeft.setNeutralMode((NeutralMode.Coast));
        fRight.setNeutralMode((NeutralMode.Coast));
        bLeft.setNeutralMode((NeutralMode.Coast));
        bRight.setNeutralMode((NeutralMode.Coast));
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
    System.out.println("Time: " + time);
    System.out.println("Time elapsed: " + (time - startTime));

    if (time - startTime < 3) {
      bRight.set(ControlMode.PercentOutput, .5);
      fRight.set(ControlMode.PercentOutput, .5);
      bLeft.set(ControlMode.PercentOutput, -.5);
      fLeft.set(ControlMode.PercentOutput, -.5);
      System.out.println("Running auto");
    }
    else {
      // tank.tankDrive(0.0, 0.0);
      System.out.println("Not running auto");
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

  /**
   * This function is called periodically during operator control.
   */

  @Override
  public void teleopInit() {

    intakeLowered = false;
    agitatorReverse = false;

    leftIntake.configContinuousCurrentLimit(intake_continuous_current);
    rightIntake.configContinuousCurrentLimit(intake_continuous_current);
    leftIntake.configPeakCurrentLimit(intake_peak_current);
    rightIntake.configPeakCurrentLimit(intake_peak_current);
    kicker.configContinuousCurrentLimit(intake_continuous_current);
    kicker.configPeakCurrentLimit(intake_peak_current);

    horizontalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    verticalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    shooterLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    shooterRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));

    horizontalAgitator.setNeutralMode((NeutralMode.Coast));
    verticalAgitator.setNeutralMode((NeutralMode.Coast));
    shooterLeft.setNeutralMode((NeutralMode.Coast));
    shooterRight.setNeutralMode((NeutralMode.Coast));

  }

  @Override
  public void teleopPeriodic() {

    drive.executeTank();

    // Shifting Gears --> BUMPERS
    if (driveBox.getRawButton(left_bumper)) {
      gearSole.set(DoubleSolenoid.Value.kReverse);
      System.out.println("Gear mode reverse");
    } 
    else if (driveBox.getRawButton(right_bumper)) {
      gearSole.set(DoubleSolenoid.Value.kForward);
      System.out.println("Gear mode forward");
      // driveBox.setRumble(RumbleType.kLeftRumble, .5);
      // driveBox.setRumble(RumbleType.kRightRumble, .5);
    } 
    // else {
    //   gearSole.set(DoubleSolenoid.Value.kOff);
    //   System.out.println("Gear mode off");
    // }


    // Raising/Lowering Intake --> A BUTTON
    if (mechBox.getRawButtonPressed(a_button)) {
      intakeLowered = !intakeLowered;
    }

    // Reversing Agitators --> Left Stick BUTTON
    if (mechBox.getRawButtonPressed(left_stick_button)) {
      agitatorReverse = !agitatorReverse;
      System.out.println("Reversed agitators.");
    }

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
      // intakePosition.set(DoubleSolenoid.Value.kOff);
      System.out.println("Intake is down.");
    } 
    else if (!intakeLowered) {
      intakePosition.set(DoubleSolenoid.Value.kReverse);
      // intakePosition.set(DoubleSolenoid.Value.kOff);
      System.out.println("Intake is up.");
    } 
    else {
      intakePosition.set(DoubleSolenoid.Value.kOff);
      System.out.println("Intake is off.");
    }


    // Vertical Agitator --  Later tie to shooting and make sensor dialogue
    double va = mechBox.getRawAxis(right_trigger);
    if (va > .1 && !agitatorReverse){
      verticalAgitator.set(ControlMode.PercentOutput, va *.4);
      System.out.println("Vertical Agitator spinning.");
    }
    else if (va > .1 && agitatorReverse){
      verticalAgitator.set(ControlMode.PercentOutput, -va * .4);
      System.out.println("Vertical Agitator spinning in reverse.");
    }
    else {
      verticalAgitator.set(ControlMode.PercentOutput, 0);
    }

    // Testing Intake and HA
    double ha = mechBox.getRawAxis(left_trigger);
    if (ha > .1 && !agitatorReverse){
      rightIntake.set(ControlMode.PercentOutput, .5);
      leftIntake.set(ControlMode.PercentOutput, -.5);
      horizontalAgitator.set(ControlMode.PercentOutput, -ha * .2);
      System.out.println("Horizontal Agitator spinning.");
      // System.out.println("Intake spinning.");
    }
    else if (ha > .1 && agitatorReverse){
      rightIntake.set(ControlMode.PercentOutput, .5);
      leftIntake.set(ControlMode.PercentOutput, -.5);
      horizontalAgitator.set(ControlMode.PercentOutput, ha * .2);
      System.out.println("Horizontal Agitator spinning in reverse.");
    } 
    else {
      rightIntake.set(ControlMode.PercentOutput, 0);
      leftIntake.set(ControlMode.PercentOutput, 0);
      horizontalAgitator.set(ControlMode.PercentOutput, 0);
      // System.out.println("Intake not spinning.");
    }

    // Shooting (very basic) -- calculate later
    if (mechBox.getXButton()) {
      shooterRight.set(ControlMode.PercentOutput, 1);
      shooterLeft.set(ControlMode.PercentOutput, -1);
      kicker.set(ControlMode.PercentOutput, -1);
      if (!agitatorReverse) {
        horizontalAgitator.set(ControlMode.PercentOutput, -.2);
        // verticalAgitator.set(ControlMode.PercentOutput, .4);
      }
      else {
        horizontalAgitator.set(ControlMode.PercentOutput, .2);
        // verticalAgitator.set(ControlMode.PercentOutput, -.4);
      }
    }
    else {
      shooterRight.set(ControlMode.PercentOutput, 0);
      shooterLeft.set(ControlMode.PercentOutput, 0);
      kicker.set(ControlMode.PercentOutput, 0);
      // horizontalAgitator.set(ControlMode.PercentOutput, 0);
      // verticalAgitator.set(ControlMode.PercentOutput, 0);
    }

   
    
    

    




    
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testInit() {
    
    fLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fLeft.configOpenloopRamp(open_ramp);

        fRight.configOpenloopRamp(open_ramp);

        compressor.setClosedLoopControl(true);

        fLeft.setNeutralMode((NeutralMode.Coast));
        fRight.setNeutralMode((NeutralMode.Coast));
        bLeft.setNeutralMode((NeutralMode.Coast));
        bRight.setNeutralMode((NeutralMode.Coast));

  }

   
  @Override
  public void testPeriodic() {

    tank.tankDrive(0.1, 0.1);

  }
}

