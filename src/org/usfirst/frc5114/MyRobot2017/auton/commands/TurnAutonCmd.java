package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

public class TurnAutonCmd extends AutonCmd {
	
	protected double targetAngle;
	
	public TurnAutonCmd(double percentVolt, double seconds, double angle)
	{
		super(percentVolt, seconds, "Turn to Angle");
		
		targetAngle = angle;
		requires(Robot.driveTrain);		
	}
	
	public void initialize() 
	{
		super.initialize();

		RobotMap.frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		RobotMap.frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);

		Robot.driveTrain.zeroSensor();
		//Robot.driveTrain.gyro.zeroYaw();
		SmartDashboard.putNumber("Target Angle", targetAngle);
		SmartDashboard.putNumber("Yaw Angle", RobotMap.ahrs.getYaw());
	}
	
	public void execute()
	{
		Robot.driveTrain.setAngleTurn(targetAngle);
		//while(Math.abs(Robot.driveTrain.gyro.getYaw()) <= Math.abs(Robot.driveTrain.angleTurn))
		//
		SmartDashboard.putNumber("Yaw", RobotMap.ahrs.getYaw());
			
			if((targetAngle >= 0 && targetAngle <= 180) || (targetAngle <= -180 && targetAngle >= -360))
				Robot.driveTrain.robotDrive.arcadeDrive(0.0, power);
			else
				Robot.driveTrain.robotDrive.arcadeDrive(0.0, -power);
	
		//}	
	}
	
	public boolean isFinished()
	{
		return Math.abs(Robot.driveTrain.gyro.getYaw()) > Math.abs(Robot.driveTrain.angleTurn) || super.isFinished();
	}
	
	public void end()
	{
		Robot.driveTrain.robotDrive.arcadeDrive(0.0, 0.0);
	}
	
	public void interrupted()
	{
		end();
	}

}
