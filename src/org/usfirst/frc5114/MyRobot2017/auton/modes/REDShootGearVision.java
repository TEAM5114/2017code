package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunAgitatorAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunCactusAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.ShootAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.VisionGear;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class REDShootGearVision extends CommandGroup{

	public REDShootGearVision() {
		
		super("RED Shoot Gear Vision");
		
		addSequential(new Wait(.25));
		addParallel(new RunAgitatorAutonCmd());
		addParallel(new RunCactusAutonCmd(3));
		addSequential(new ShootAutonCmd(3));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(1.0, .6, 4));
		
		RobotMap.ahrs.zeroYaw();
		addSequential(new Wait(.25));
		addSequential(new TurnAutonCmd(.8, 2, 100));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(1, .8, 18));
		
		System.out.println("before turn");
		RobotMap.ahrs.zeroYaw();
		addSequential(new Wait(1));
		addSequential(new TurnAutonCmd(.8, 10, -40));
		addSequential(new Wait(.25));
		System.out.println("after turn");
		
		addSequential(new VisionGear());
		addSequential(new DriveToPositionAutonCmd(.5, 1, 1));
		addSequential(new DriveToPositionAutonCmd(.25, .5, 1));
	}
}
