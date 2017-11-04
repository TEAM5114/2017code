package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class REDPlaceGearChuteSide extends CommandGroup{

	public REDPlaceGearChuteSide() {
		
		super("RED Place Gear Chute Side");
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(0.6, 10, 98));
		
		addSequential(new Wait(.25));
		addSequential(new TurnAutonCmd(.80, 10, 25));
	}
}
