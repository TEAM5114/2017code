package org.usfirst.frc5114.MyRobot2017.auton.modes;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionTurnTest extends CommandGroup {
	
	public VisionTurnTest()
	{
		super("Vision Turn Test");
		
		addSequential(new VisionGear());
		addSequential(new DriveToPositionAutonCmd(.5, 1, 1));
		addSequential(new DriveToPositionAutonCmd(.25, .5, 1));
	}

}
