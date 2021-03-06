package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunCactusAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.ShootAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionGear;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.commands.DriveTrajectory;

public class PlaceGearRightVision extends CommandGroup{

	public PlaceGearRightVision() {
		
		super("Place Gear Chute Side - BLUE");
		
		//addSequential(new DriveTrajectory(Robot.driveTrain, RobotMap.frontLeftMotor, RobotMap.frontRightMotor, Robot.gearRight));		
		addSequential(new DriveToPositionAutonCmd(.7, 1.3, 1));
		addSequential(new Wait(.1));
		addSequential(new TurnAutonCmd(.7, 10, -35));
		addSequential(new Wait(.25));
//		
//		if(Robot.centerX.length != 2)
//			addSequential(new DriveToPositionAutonCmd(.5, .5, 1));
		
		addSequential(new VisionGear());
		addSequential(new DriveToPositionAutonCmd(.5, 1, 1));
		addSequential(new DriveToPositionAutonCmd(.25, .5, 1));
		
		
	}
}
