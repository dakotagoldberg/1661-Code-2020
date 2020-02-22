package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;

import frc.robot.*;

import frc.robot.Robot_Framework;
import frc.robot.Robot_Framework.*;

public class Drive implements Robot_Framework{

    /* Quick description of the error: the code does not register unless
    the compressor and talon objects are declared in the drive class.
    for some reason they are not coming correctly from the interface.
    The code builds and deploys without a problem but the "robot code"
    light is off when you try to enable the robot. Look into this.
    */
    Compressor compressor = new Compressor(0);
    WPI_TalonFX fLeft = new WPI_TalonFX(front_left_drive);
    WPI_TalonFX fRight = new WPI_TalonFX(front_right_drive);

    double x, y, throttle, turn, speedL, speedR, t_left, t_right;

    public Drive() { 
        System.out.println(test);

        fLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fLeft.configOpenloopRamp(open_ramp);

        fRight.configOpenloopRamp(open_ramp);

        compressor.setClosedLoopControl(true);
    }

    public void executeTank() {
        y = -driveBox.getRawAxis(left_y_axis);
        x = driveBox.getRawAxis(right_x_axis);

        if (Math.abs(y) > 0.1)
            throttle = y;
        else
            throttle = 0.0;

        if (Math.abs(x) > 0.2)
            turn = x;
        else
            turn = 0.0;

        t_left = throttle + turn;
        t_right = throttle - turn;

        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);

        tank.tankDrive(speedL, speedR);
    }

    private double skim(double v) {
        if (v > 1.0)
            return -((v - 1.0) * gain_skim);        
        else if (v < -1.0)
            return -((v + 1.0) * gain_skim);
        return 0;
    }
}