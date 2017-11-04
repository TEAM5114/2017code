package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;

public class DriveToPositionAutonCmd extends AutonCmd {
	
	protected int targetPosition;
	
	public DriveToPositionAutonCmd(double percentVolt, double seconds, double distance) {
		super(percentVolt, seconds, "Drive To Position");
		
		int counts = (int)(distance / (6 * Math.PI) * 1000);
		
		
		targetPosition = counts;
		requires(Robot.driveTrain);
	}
	
	public void initialize(){
		super.initialize();	
		Robot.driveTrain.zeroSensor();
		Robot.driveTrain.setTargetPos(targetPosition);
	    SmartDashboard.putNumber("Target Counts", targetPosition);

	}
	
	public void execute() {
		//OPTIMAL FEED-FORWARD FOR BLUE MIDDLE AUTON IS -.3
		Robot.driveTrain.robotDrive.arcadeDrive(-power, -.21 + Robot.driveTrain.turnCorrection(Robot.newMiddleScalar));
	}
	
	public boolean isFinished() {
		return (Math.abs(Robot.driveTrain.frontRightMotor.getPosition()) > Math.abs(Robot.driveTrain.targetPosition)) || (super.isFinished());
	}
	
	public void end() {
		Robot.driveTrain.robotDrive.arcadeDrive(0.0, 0.0);
	}
	
	public void interrupted() {
		end();
	}
}
