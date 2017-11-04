package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class BLUEPlaceGearChuteSide extends CommandGroup{

	public BLUEPlaceGearChuteSide() {
		
		super("BLUE Place Gear Chute Side");
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(0.7, 5, 107));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(0.6, 3, -3));
		
		addSequential(new Wait(.25));
		addSequential(new TurnAutonCmd(1, 3, -10));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(0.6, 3, 3));

	}
}
