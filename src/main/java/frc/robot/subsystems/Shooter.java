package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Robot_Framework;

public class Shooter implements Robot_Framework {
    WPI_TalonFX ShooterR = new  WPI_TalonFX(shooter_R);
    WPI_TalonFX ShooterL = new  WPI_TalonFX(shooter_L);
    
    public Shooter() {

    }

    public void Shoot(int dist){
        //Math dist(ft) -> Power(motor Units)
        int power = 0;
        ShooterR.set(power);
    }
}