package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.AutonCmd;

public class RunAgitatorAutonCmd extends Command{
	
	protected int targetPosition;
	
	public RunAgitatorAutonCmd() 
	{		
		requires(Robot.agitator);
	}
	
	public void initialize(){
		super.initialize();
		Robot.agitator.setAgitatorPower(1.0);
	}
	
	public void execute() {
	}
	
	public boolean isFinished() {
		return true;
	}
	
	public void end() {
	}
	
	public void interrupted() {
		end();
	}
}
