package org.usfirst.frc5114.MyRobot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class RunShooter extends Command {

	public RunShooter() {
		requires(Robot.shootBall);
	}
	
	protected void initialize() {
		Robot.shootBall.enable();
	}
	
	protected void execute() {
		Robot.shootBall.launch();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		Robot.shootBall.disable();
	}
	
	public void interrupted() {
		end();
	}
}
