package org.usfirst.frc5114.MyRobot2017.auton.modes;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunAgitatorAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunCactusAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.ShootAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionAlignToShoot;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionShootAlignTest extends CommandGroup {
	
	public VisionShootAlignTest()
	{
		super("Vision Shoot Align Test");
		
		addSequential(new VisionAlignToShoot());
		
		addParallel(new RunAgitatorAutonCmd());
		addParallel(new RunCactusAutonCmd(10));
		addSequential(new ShootAutonCmd(10));
	}

}
