package org.usfirst.frc5114.MyRobot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class ToggleFlashlight extends Command {
	
	public ToggleFlashlight() {
		requires(Robot.flashlight);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.flashlight.turnOn();
	
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.flashlight.turnOff();;
	}
	
	protected void interrupted() {
		end();
	}

}
