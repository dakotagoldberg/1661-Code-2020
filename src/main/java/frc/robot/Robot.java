/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private TalonSRX testTalon = new TalonSRX(2);

  

  XboxController driveBox = new XboxController(1);
  // final static int right_trigger = 4;


  String status = "Intake: Down";
  WPI_TalonFX oneMotor = new  WPI_TalonFX(2);
  WPI_TalonFX anotherMotor = new  WPI_TalonFX(3);
  int right_bumper = 5;

  WPI_TalonFX okayFalcon = new  WPI_TalonFX(11);

  // Counterclockwise, Master
  TalonSRX intakeRight = new TalonSRX(2);
  

  // Counterclockwise, Slave
  TalonSRX intakeLeft = new TalonSRX(4);

  // Up-Down Pneumatics
  // DoubleSolenoid intakeRaise = new DoubleSolenoid(0, 1);


  // // Drive
  // Compressor compressor = new Compressor(0);

  // int front_left_drive = 0;
  // int front_right_drive = 0;
  // int back_left_drive = 0;
  // int back_right_drive = 0; 
    
  // WPI_TalonFX fLeft = new WPI_TalonFX(front_left_drive);
  // WPI_TalonFX fRight = new WPI_TalonFX(front_right_drive);
  // WPI_TalonFX bLeft = new WPI_TalonFX(back_left_drive);
  // WPI_TalonFX bRight = new WPI_TalonFX(back_right_drive);

  // SpeedControllerGroup left = new SpeedControllerGroup(fLeft, bLeft);
  // SpeedControllerGroup right = new SpeedControllerGroup(fRight, bRight);

  // DifferentialDrive tank = new DifferentialDrive(left, right);

   Drive drive = new Drive();

    



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
    // m_autoSelected = m_chooser.getSelected();
    // // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    // System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
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
  public void teleopPeriodic() {

  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testInit() {
    System.out.print("crystal clear cutlery");
    // oneMotor.setSelectedSensorPosition(0);
    // ControlMode position = 
    //  oneMotor.set(ControlMode.Position, 50000);

    // oneMotor.config_kP(3, 0.45, 0);
    // oneMotor.config_kI(3, 0.0, 0);
    // oneMotor.config_kD(3, 4, 0);

    // oneMotor.set(ControlMode.Position, 5000);

    // intakeRight.configFactoryDefault();
    // intakeLeft.configFactoryDefault();

    // intakeLeft.set(ControlMode.Follower, intakeRight.getDeviceID());

  }

   
  @Override
  public void testPeriodic() {
    System.out.println("bruh");

    //testTalon.set(ControlMode.PercentOutput, 0.3);
    oneMotor.set(0.1);
    // System.out.println("oneMotor: " + oneMotor.getSelectedSensorPosition());
     anotherMotor.set(0.1);
    // System.out.println("anotherMotor: " + anotherMotor.getSelectedSensorPosition());

    // Testing the turret
    // double leftTrigger = driveBox.getRawAxis(2);
    // Testing the Intake
    // double x = driveBox.getRawAxis(3);

    // boolean intakeIsRaised = false;
    // System.out.println(driveBox.getRawButton(right_bumper));

    // Just for the left trigger
    // if(leftTrigger > .1) {
    //   testTalon.set(ControlMode.PercentOutput, leftTrigger/2);
    //   System.out.println(leftTrigger);
    // }
    

    // if (x > .1) {
    //   // intakeRight.set(ControlMode.PercentOutput, x);
    //   intakeLeft.set(ControlMode.PercentOutput, x);

    // }
    // else {
    //   // intakeRight.set(ControlMode.PercentOutput, 0);
    //   intakeLeft.set(ControlMode.PercentOutput, x);
    // }
    

    // if (driveBox.getAButton()) {
    //   intakeIsRaised = !intakeIsRaised;
    //   System.out.println("BUTTON WORKS");
    // }

    // Raise/Lower Intake
    
    // if (driveBox.getAButton()) {
    //   intakeRaise.set(DoubleSolenoid.Value.kForward);
    //   status = "Intake: Up";
    // }
    // else if (driveBox.getBButton()) {
    //   intakeRaise.set(DoubleSolenoid.Value.kReverse);
    //   status = "Intake: Down";
    // }
    // System.out.println(status);

    // intakeRaise.set(DoubleSolenoid.Value.kReverse);

    // Write code for up/down w/ pneumatics (one)




    // Drive

    // drive.executeTank(); //Uses both sticks on driveBox        





  
  }
}

