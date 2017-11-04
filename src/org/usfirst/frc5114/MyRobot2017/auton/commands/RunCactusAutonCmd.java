package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.AutonCmd;

public class RunCactusAutonCmd extends AutonCmd {
	
	protected int targetPosition;
	
	public RunCactusAutonCmd(double seconds) 
	{
		super(0.0, seconds, "Run Cactus for " + seconds + "seconds");
		
		requires(Robot.loadBalls);
	}
	
	public void initialize(){
		super.initialize();
	}
	
	public void execute() {
		if ((RobotMap.shooterLaunchMotor.getSpeed() < (Robot.shootBall.launchVelocity + (.025 * Robot.shootBall.launchVelocity))) && (RobotMap.shooterLaunchMotor.getSpeed() > Robot.shootBall.launchVelocity - (.025 * Robot.shootBall.launchVelocity)))
			Robot.loadBalls.load(Robot.loadBalls.getLoadPower());
		else
			Robot.loadBalls.load(0.0);
	}
	
	public boolean isFinished() {
		return (super.isFinished());
	}
	
	public void end() {
		Robot.loadBalls.load(0.0);
	}
	
	public void interrupted() {
		end();
	}
}
