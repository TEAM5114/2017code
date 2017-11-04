package org.usfirst.frc5114.MyRobot2017.auton.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class VisionGear extends Command {
	
	CANTalon leftTalon;
	CANTalon rightTalon;
	private double[] xCenter;
	private double[] area;
	private double degreeError;
	private double turn;
	
	public VisionGear()
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
		xCenter = Robot.gearCamCenterX;
		area = Robot.gearCamArea;
		
		try{
			if(xCenter.length == 1)
			{
				if(xCenter[0] < (double)Robot.gearCamXRes / 2.0)
				{
					leftTalon.set(.6);
					rightTalon.set(.6);
				}
				else
				{
					leftTalon.set(-.6);
					rightTalon.set(-.6);
				}
			}
			
			else if(xCenter.length == 2)
			{
				double visionTarget = (Robot.gearCamCenterX[0] + Robot.gearCamCenterX[1]) / 2.0;
				double camCenter = (double)(Robot.gearCamXRes / 2.0);
				double pixelError = visionTarget - camCenter;
				degreeError = pixelError * Robot.gearCamDegreesPerPixelX;
				
				turn = 0.8 * (1.0/20.0) * degreeError;
				turn = (turn>0)?(.2 + turn):(-.1 + turn);
				
				System.out.println("Center1: " + Robot.gearCamCenterX[0] + " Center2: " + Robot.gearCamCenterX[1]);
				System.out.println( "Cam Center " + camCenter + " Vision Target: " + visionTarget);
				System.out.println("Degree error:" + degreeError +" Turn: " + turn);
			
				Robot.driveTrain.robotDrive.arcadeDrive(-.55, turn);
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
			return (area[0] > 600 && area[1] > 600);
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
