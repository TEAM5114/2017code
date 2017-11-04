package org.usfirst.frc5114.MyRobot2017.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5114.MyRobot2017.auton.commands.DriveToPositionAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.RunCactusAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.ShootAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.TurnAutonCmd;
import org.usfirst.frc5114.MyRobot2017.auton.commands.Wait;
import org.usfirst.frc5114.MyRobot2017.subsystems.*;

public class BLUEShoot extends CommandGroup{

	public BLUEShoot() {
		
		super("BLUE Shoot");
		
		addSequential(new Wait(.25));
		
		addParallel(new RunCactusAutonCmd(10));
		addSequential(new ShootAutonCmd(10));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(1.0, .4, 6));
		
		addSequential(new Wait(.25));
		addSequential(new TurnAutonCmd(1, 1, -65));
		
		addSequential(new Wait(.25));
		addSequential(new DriveToPositionAutonCmd(1, 1, 60));
		
	}
}
