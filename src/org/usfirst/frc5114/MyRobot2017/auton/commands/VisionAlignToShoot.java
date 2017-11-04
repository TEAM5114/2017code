package org.usfirst.frc5114.MyRobot2017.auton.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAlignToShoot extends Command {
	
	CANTalon leftTalon;
	CANTalon rightTalon;
	private double[] xCenter;
	private double[] yCenter;
	private double[] area;
	private double[] width;
	private double[] height;
	private double degreeError;
	private double yError;
	private double turn;
	private double drive;
	
	public VisionAlignToShoot()
	{
		this.leftTalon = RobotMap.frontLeftMotor;
		this.rightTalon = RobotMap.frontRightMotor;
	}
	
	protected void initialize()
	{
		leftTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightTalon.changeControlMode(TalonControlMode.PercentVbus);

	}
	
	protected void execute()
	{
		xCenter = Robot.shootCamCenterX;
		yCenter = Robot.shootCamCenterY;
		width = Robot.shootCamWidth;
		height = Robot.shootCamHeight;
		area = Robot.shootCamArea;
		
		try{
			if(xCenter.length == 2)
			{
				double visionTargetCenterX = xCenter[0];
				double camCenterX = (double)(Robot.shootCamXRes / 2.0);
				double pixelError = visionTargetCenterX - camCenterX;
				degreeError = pixelError * Robot.shootCamDegreesPerPixelX;
				
				turn = 0.8 * (1.0/20.0) * degreeError;
				turn = (turn>0)?(.1 + turn):(-.1 + turn);
				
				System.out.println("Center1: " + xCenter[0] + " Center2: " + xCenter[1]);
				System.out.println( "Cam Center " + camCenterX + " Vision Target: " + visionTargetCenterX);
				System.out.println("Degree error:" + degreeError +" Turn: " + turn);
			
				double visionTargetCenterY = (yCenter[0] + yCenter[1])/2.0;
				System.out.println("Vision Target Center Y Avg: " + visionTargetCenterY);
				double idealY = 215;
				yError = visionTargetCenterY - idealY;
				System.out.println(yError);
				
				if(Math.abs(yError) < 100)
					drive = (yError<0)?(-.4):(.8);
				else
				{
					drive = -.8 * (1.0/20.0) * yError;
					drive = (drive<-1)?-1:drive;
				}
				
				Robot.driveTrain.robotDrive.arcadeDrive(drive, turn);
			}
		}
		catch(Exception e)
		{
			System.out.println("targets not equal to two" + e);
		}
		
	}
	
	protected boolean isFinished()
	{
		if(area.length == 2)
			//return Math.abs(degreeError) < 1 && Math.abs(yError) < 12;
			return Math.abs(yError) < 12;
		else
			return false;
	}
	
	protected void end()
	{
		System.out.println("Vision Turn Complete");
		Robot.driveTrain.robotDrive.arcadeDrive(0.0, 0.0);
	}
	
	protected void interrupted()
	{
		
	}

}
