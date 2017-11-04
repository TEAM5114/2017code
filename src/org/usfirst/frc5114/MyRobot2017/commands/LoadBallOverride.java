package org.usfirst.frc5114.MyRobot2017.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LoadBallOverride extends Command {
	
	public LoadBallOverride() {
		requires(Robot.loadBalls);	
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.loadBalls.load(Robot.loadBalls.getLoadPower());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.loadBalls.load(0.0);
	}
	
	protected void interrupted() {
		end();
	}
}
