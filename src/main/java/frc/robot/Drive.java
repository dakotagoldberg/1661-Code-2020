package frc.robot;

import frc.robot.Robot_Framework;


import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;

public class Drive implements Robot_Framework{
    

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

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("speedR: " + speedR);
        System.out.println("speedL: " + speedL);

    }

    private double skim(double v) {
        if (v > 1.0)
            return -((v - 1.0) * gain_skim);        
        else if (v < -1.0)
            return -((v + 1.0) * gain_skim);
        return 0;
    }
}