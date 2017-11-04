package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveStraight;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.commands.DriveTrajectory;
import org.usfirst.frc5114.MyRobot2017.commands.KickOutBucket;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class DriveTest extends CommandGroup{

	public DriveTest() {
		
		super("Drive Test");
//		//addSequential(new DriveToPositionAutonCmd(.5, 30, 12));
//		//addSequential(new TurnAutonCmd(0.5, 60, 45));
//	  	//addSequential(new DriveToPositionAutonCmd(-.5, 30, 6));
//		
//		//addSequential(new Wait(10));
//		
//		//addSequential(new DriveTrajectory(Robot.driveTrain, RobotMap.frontLeftMotor, RobotMap.frontRightMotor));
//		//System.out.println("motion proile ended");
//		
//		//LEFT SIDE
//		addSequential(new Wait(.1));
//		addSequential(new TurnAutonCmd(.55, 10, 60));
//		addSequential(new Wait(.25));
//		addSequential(new DriveToPositionAutonCmd(.55, 10, 24));
//		
//		//RIGHT SIDE
////		addSequential(new Wait(.1));
////		addSequential(new TurnAutonCmd(.55, 10, -60));
////		addSequential(new Wait(.25));
////		addSequential(new DriveToPositionAutonCmd(.55, 10, 24));
		
		addSequential(new Wait(10));
		
	}
}
