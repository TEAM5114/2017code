package org.usfirst.frc5114.MyRobot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class RunClimber extends Command {

	public RunClimber() {
		requires(Robot.liftBot);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.liftBot.lift((Robot.oi.controlGamePad.getRawAxis(1)));
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.liftBot.lift(0.0);
	}
	
	protected void interrupted() {
		end();
	}
}
