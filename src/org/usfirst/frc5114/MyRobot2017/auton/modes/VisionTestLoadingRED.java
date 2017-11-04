package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionGear;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.commands.DriveTrajectory;

public class VisionTestLoadingRED extends CommandGroup{

	public VisionTestLoadingRED() {
		
		super("Vision Test Chute Side - RED");
		
		//addSequential(new DriveTrajectory(Robot.driveTrain, RobotMap.frontLeftMotor, RobotMap.frontRightMotor, Robot.gearLoadingRED));		
		addSequential(new Wait(.1));
		addSequential(new TurnAutonCmd(.7, 10, 20));
		addSequential(new Wait(.1));
		addSequential(new VisionGear());
		addSequential(new Wait(.1));
		addSequential(new DriveToPositionAutonCmd(.55, 10, 1));
		
	}
}
