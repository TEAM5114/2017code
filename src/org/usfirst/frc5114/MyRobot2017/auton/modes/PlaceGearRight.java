package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class PlaceGearRight extends CommandGroup{

	public PlaceGearRight() {
		
		super("Place Gear Right");

		Timer timer = new Timer();
		timer.start();
		//POWER THEN TIME OUT THEN DEGREES
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(.70, 15, 56));
		addSequential(new Wait(0.5));
		
		addSequential(new TurnAutonCmd(.65, 15, -45));
		addSequential(new Wait(.5));
		
		addSequential(new DriveToPositionAutonCmd(.55, 15, 4.5));
		addSequential(new Wait(2));
		
		addSequential(new DriveToPositionAutonCmd(-.55, 15, 10));
		addSequential(new Wait(.5));

		addSequential(new TurnAutonCmd(.7, 15, -130));
		addSequential(new Wait(.25));
		
		addSequential(new DriveToPositionAutonCmd(.7, 15, 48));
		addSequential(new Wait(.5));
		
		addSequential(new TurnAutonCmd(.7, 15, -15));
		
	}
}
