package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

public class Shooter extends Subsystem
{
	private final CANTalon shooterLaunchMotor = RobotMap.shooterLaunchMotor;

	//private double newSpeed;
	public double launchVelocity; // = Robot.newSpeed;//6200 original

	public void init() {
		shooterLaunchMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
	}
	
	public void initDefaultCommand() {
		
	}
	
	public void launch() {
		launchVelocity = Robot.newSpeed;
		shooterLaunchMotor.setSetpoint(launchVelocity);
		//shooterLaunchMotor.set(0.51);
	}
	
	public void launch(double velocity) {
		shooterLaunchMotor.setSetpoint(velocity);
	}
	
	public void disable() {
		shooterLaunchMotor.disable();
	}
	
	public void enable() {
		shooterLaunchMotor.enable();
	}

}
