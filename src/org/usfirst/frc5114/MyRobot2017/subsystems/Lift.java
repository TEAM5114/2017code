package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;

public class Lift extends Subsystem
{
	private final CANTalon liftMotor = RobotMap.liftMotor;

	public void initDefaultCommand() {
			
	}
	  
	public void lift(double x){
		liftMotor.set(-1*Math.abs(x));
	}
}
