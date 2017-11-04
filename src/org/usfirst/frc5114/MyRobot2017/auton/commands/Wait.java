package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;

public class Wait extends AutonCmd {
	
	protected int targetPosition;
	
	public Wait(double seconds) {
		super(0.0, seconds, "Drive To Position");
		
		requires(Robot.driveTrain);
	}
	
	public void initialize(){
		super.initialize();	
		Robot.driveTrain.zeroSensor();
	}
	
	public void execute() {
		Robot.driveTrain.robotDrive.arcadeDrive(0, 0);
	}
	
	public boolean isFinished() {
		return (super.isFinished());
	}
	
	public void end() {
		Robot.driveTrain.robotDrive.arcadeDrive(0.0, 0.0);
	}
	
	public void interrupted() {
		end();
	}
}
