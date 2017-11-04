package org.usfirst.frc5114.MyRobot2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class StopShooter extends Command {

	Timer timer;
	
	public StopShooter() {
		requires(Robot.shootBall);
		timer = new Timer();
	}
	
	protected void initialize() {
		timer.start();
		Robot.shootBall.launch(-1.0);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return timer.get() >= 0.5;
	}
	
	public void end() {
		Robot.shootBall.launch(0.0);
	}
	
	public void interrupted() {
		end();
	}
}
