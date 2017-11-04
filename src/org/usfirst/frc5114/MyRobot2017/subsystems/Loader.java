package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;

public class Loader extends Subsystem
{
	private final CANTalon shooterLoadMotor = RobotMap.shooterLoadMotor;
	
	private double loadPower = 1.0;
	
	
	public void initDefaultCommand() {
		
	}
	
	public void load(double power) {
		shooterLoadMotor.set(power);
		
	}
	
	public double getLoadPower() {
		return loadPower;
	}
	
}
