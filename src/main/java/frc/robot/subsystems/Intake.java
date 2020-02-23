package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Robot_Framework;

/*
    Spin horizontal agitator when we shoot and intake
        after we intake let the horizontal agitator spin a bit longer

*/

public class Intake implements Robot_Framework {


    public Intake() {
        leftIntake.configFactoryDefault();
        rightIntake.configFactoryDefault();

        leftIntake.setSensorPhase(false);
        rightIntake.setSensorPhase(false);
        
        leftIntake.setInverted(false);
        rightIntake.setInverted(false);
    }

}