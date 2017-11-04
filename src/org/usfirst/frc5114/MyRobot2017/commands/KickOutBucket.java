package org.usfirst.frc5114.MyRobot2017.commands;

import org.usfirst.frc5114.MyRobot2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class KickOutBucket extends Command{

	//public Timer timer;
	//public boolean running = super.isRunning();
	
	public KickOutBucket(){
		requires(Robot.gearRelease);
		//timer = new Timer();
	}
	
	protected void intialize(){
		Robot.gearRelease.neutral();
	}
	
	protected void execute(){
		//Robot.gearRelease.release();
		Robot.gearRelease.kickOutBucket();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end(){
		Robot.gearRelease.retractBucket();
	}
	
	
	
	
	
	//beemer is a butt head
}
