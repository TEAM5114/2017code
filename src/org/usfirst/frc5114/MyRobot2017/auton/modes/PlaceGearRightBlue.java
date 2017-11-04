package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class PlaceGearRightBlue extends CommandGroup{

	public PlaceGearRightBlue() {
		
		super("Place Gear Right Blue");
		
		addSequential(new Wait(.25));
		
		addSequential(new DriveToPositionAutonCmd(0.55, 15, 73));
		addSequential(new Wait(.25));

		addSequential(new TurnAutonCmd(.55, 15, -56));
		addSequential(new Wait(.5));

		addSequential(new DriveToPositionAutonCmd(.55, 15, 28));
		addSequential(new Wait(2));

		addSequential(new DriveToPositionAutonCmd(-.55, 15, 28));
		addSequential(new Wait(.5));

		addSequential(new TurnAutonCmd(.55, 15, 70));

	}
}
