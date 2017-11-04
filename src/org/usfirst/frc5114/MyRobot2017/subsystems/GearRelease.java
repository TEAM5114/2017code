package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearRelease extends Subsystem
{
	private Servo leftServo = RobotMap.leftServo;
	private Servo rightServo = RobotMap.rightServo;
	
	private DoubleSolenoid solenoid1 = RobotMap.solenoid1;
	
	public void initDefaultCommand() {
	}
	
	public void release() {
		leftServo.setBounds(2.6, 2.6, 1.5, 0.8, 0.8);
		leftServo.setAngle(180);
	
		rightServo.setBounds(2.6, 2.6, 1.5, 0.52, 0.52);
		rightServo.setAngle(0);
	}
	
	public void kickOutBucket()
	{
		//solenoid1.set(true);
		solenoid1.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractBucket()
	{
		//solenoid1.set(false);
		solenoid1.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void neutral()
	{
		solenoid1.set(DoubleSolenoid.Value.kOff);
	}
	
	/* public void retract() {
		leftServo.setBounds(2.6, 2.6, 1.5, 0.8, 0.8);
		leftServo.setAngle(45);
	
		rightServo.setBounds(2.6, 2.6, 1.5, 0.8, 0.8);
		rightServo.setAngle(80);
	} */
	
	public void disable() {
		leftServo.setDisabled();
		rightServo.setDisabled();
	}

	
}
