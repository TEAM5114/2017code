package org.usfirst.frc5114.MyRobot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class ClearShooter extends Command {

	public ClearShooter() {
		requires(Robot.shootBall);
		requires(Robot.loadBalls);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.shootBall.launch(1.0);
		Robot.loadBalls.load(1.0);
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		Robot.shootBall.launch(0.0);
		Robot.loadBalls.load(0.0);
	}
	
	public void interrupted() {
		end();
	}
}
