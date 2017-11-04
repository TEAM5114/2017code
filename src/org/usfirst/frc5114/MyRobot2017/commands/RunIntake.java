package org.usfirst.frc5114.MyRobot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

public class RunIntake extends Command {
	
	public RunIntake() {
		requires(Robot.intakeBall);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.intakeBall.intake(Robot.intakeBall.getIntakePower());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.intakeBall.intake(0.0);
	}
	
	protected void interrupted() {
		end();
	}

}
