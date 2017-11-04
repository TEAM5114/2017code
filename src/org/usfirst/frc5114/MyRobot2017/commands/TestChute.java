package org.usfirst.frc5114.MyRobot2017.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TestChute extends Command{

	public TestChute(){
		requires(Robot.gearRelease);
	}
	
	protected void intialize(){
	}
	
	protected void execute(){
		Robot.gearRelease.disable();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end(){
	}
}
