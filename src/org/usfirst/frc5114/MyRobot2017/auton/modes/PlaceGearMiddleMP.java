package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Trajectory;

import org.usfirst.frc5114.MyRobot2017.GenerateSRXMotionProfile;
import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.commands.DriveTrajectory;

public class PlaceGearMiddleMP extends CommandGroup{

	public PlaceGearMiddleMP() {
		
		super("Place Gear Middle");
		
		//addSequential(new DriveTrajectory(Robot.driveTrain, RobotMap.frontLeftMotor, RobotMap.frontRightMotor, Robot.gearMiddle));
		
	}
}
