package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class REDPlaceGearMiddle extends CommandGroup{

	public REDPlaceGearMiddle() {
		
		super("RED Place Gear Middle");
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(0.6, 5, 64.5));
	}
}
