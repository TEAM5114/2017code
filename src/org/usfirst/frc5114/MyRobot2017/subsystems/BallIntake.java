package org.usfirst.frc5114.MyRobot2017.subsystems;


import org.usfirst.frc5114.MyRobot2017.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class BallIntake extends Subsystem
{
	private final CANTalon intakeMotor = RobotMap.intakeBallMotor;
	
	private double intakePower = -.60;
	private double outtakePower = 1;

	public void initDefaultCommand() {
		
	}
	
	public void intake(double power){
		intakeMotor.set(power);
	}
	
	public double getIntakePower() {
		return intakePower;
	}
	
	public void setIntakePower(double x) {
		intakePower = x;
	}
	public void outtake () {
		intakeMotor.set(outtakePower);
	}
}	
		
