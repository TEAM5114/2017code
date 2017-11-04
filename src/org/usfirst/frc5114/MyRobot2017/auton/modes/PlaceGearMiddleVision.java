package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Trajectory;

import org.usfirst.frc5114.MyRobot2017.GenerateSRXMotionProfile;
import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionGear;
import org.usfirst.frc5114.MyRobot2017.commands.DriveTrajectory;

public class PlaceGearMiddleVision extends CommandGroup{

	public PlaceGearMiddleVision() {
		
		super("Place Gear Middle");
		
		addSequential(new DriveToPositionAutonCmd(.5, 1.5, 1));
//		
//		if(Robot.centerX.length != 2)
//			addSequential(new DriveToPositionAutonCmd(.5, .5, 1));
			
		addSequential(new VisionGear());
		addSequential(new DriveToPositionAutonCmd(.5, 1, 1));
		addSequential(new DriveToPositionAutonCmd(.25, .5, 1));
		
	}
}
