package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Flashlight extends Subsystem {
	
	private final Relay flashlight = RobotMap.lightRelay;
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void turnOn() {
		flashlight.set(Value.kForward);
	}
	
	public void turnOff() {
		flashlight.set(Value.kOff);
	}
	
}
