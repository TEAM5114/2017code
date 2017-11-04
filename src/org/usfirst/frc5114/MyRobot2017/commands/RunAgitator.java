package org.usfirst.frc5114.MyRobot2017.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunAgitator extends Command{

	public RunAgitator(){
		requires(Robot.agitator);
	}
	
	protected void intialize(){
	}
	protected void execute(){
		Robot.agitator.setAgitatorPower(-1);
	}
	
	protected void end(){
		Robot.agitator.setAgitatorPower(0.0);
	}
	
	@Override
	protected boolean isFinished() {
		//In order to end, call Robot.agitator.toggle();
		return false;
	}
	

}
