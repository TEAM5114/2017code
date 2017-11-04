package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.auton.commands.AutonCmd;

public class ShootAutonCmd extends AutonCmd {
	
	protected int targetPosition;
	
	public ShootAutonCmd(double seconds) 
	{
		super(0.0, seconds, "Shoot for " + seconds + "seconds");
		
		requires(Robot.shootBall);
	}
	
	public void initialize(){
		super.initialize();	
		Robot.shootBall.enable();
	}
	
	public void execute() {
		Robot.shootBall.launch();
	}
	
	public boolean isFinished() {
		return (super.isFinished());
	}
	
	public void end() {
		Robot.shootBall.disable();
	}
	
	public void interrupted() {
		end();
	}
}
