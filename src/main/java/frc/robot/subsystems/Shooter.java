package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Robot_Framework;

public class Shooter implements Robot_Framework {
    
    public Shooter() {

    }

    public void Shoot(int dist){
        //Math dist(ft) -> Power(motor Units)
        int power = 0;
        ShooterR.set(power);
        ShooterL.set(-1 * power);
    }
}