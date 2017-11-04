package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunAgitatorAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunCactusAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.ShootAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class REDShoot extends CommandGroup{

	public REDShoot() {
		
		super("RED Shoot");
		
		addSequential(new Wait(.25));
		addParallel(new RunAgitatorAutonCmd());
		addParallel(new RunCactusAutonCmd(10));
		addSequential(new ShootAutonCmd(10));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(1.0, .4, 4));
		
		addSequential(new Wait(.25));
		addSequential(new TurnAutonCmd(1, 1, 80));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(1, 1, 18));
	}
}
