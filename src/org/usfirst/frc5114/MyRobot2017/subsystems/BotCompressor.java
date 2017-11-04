package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class BotCompressor extends Subsystem
{
	private Compressor compressor = RobotMap.compressor;
	
	public BotCompressor()
	{
		
	}
	
	public void initDefaultCommand()
	{
		
	}
	
	public void startCompressor()
	{
		compressor.start();
	}
}
