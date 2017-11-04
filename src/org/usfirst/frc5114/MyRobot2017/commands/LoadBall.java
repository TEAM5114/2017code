package org.usfirst.frc5114.MyRobot2017.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LoadBall extends Command {
	
	public LoadBall() {
		requires(Robot.loadBalls);	
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		if ((RobotMap.shooterLaunchMotor.getSpeed() < (Robot.shootBall.launchVelocity + (.025 * Robot.shootBall.launchVelocity))) && (RobotMap.shooterLaunchMotor.getSpeed() > Robot.shootBall.launchVelocity - (.025 * Robot.shootBall.launchVelocity)))
			Robot.loadBalls.load(Robot.loadBalls.getLoadPower());
		else
			Robot.loadBalls.load(0.0);
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
