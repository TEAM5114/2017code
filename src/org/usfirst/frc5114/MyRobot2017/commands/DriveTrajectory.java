package org.usfirst.frc5114.MyRobot2017.commands;

import org.usfirst.frc5114.MyRobot2017.GenerateSRXMotionProfile;
import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import com.ctre.*;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class DriveTrajectory extends Command{

	private long startTime = 0;
	private long maxTime = 0;
	private int iterations;

	int loops = 0;

	boolean motionProfileStarted = true;
	GenerateSRXMotionProfile tankProfile;
	CANTalon leftTalon;
	CANTalon rightTalon;


	public DriveTrajectory(Subsystem driveTrain, CANTalon leftTalon, CANTalon rightTalon, GenerateSRXMotionProfile profile) {
		requires(driveTrain);
		//true indicates left profile
		this.tankProfile = profile;
		
		this.leftTalon = leftTalon;
		this.rightTalon = rightTalon;
	}

	@Override
	protected void initialize() {
	    loops = 0;
	    tankProfile.reset();
	    motionProfileStarted = true;
	    iterations = 1;
	}

	@Override
	protected void execute() {
		System.out.println("Middle Execute");
		this.tankProfile.control();
				
    	rightTalon.changeControlMode(TalonControlMode.MotionProfile);
    	leftTalon.changeControlMode(TalonControlMode.MotionProfile);

    	CANTalon.SetValueMotionProfile setRightOutput = this.tankProfile.getSetValue();
        CANTalon.SetValueMotionProfile setLeftOutput = this.tankProfile.getSetValue();

        rightTalon.set(setRightOutput.value);
    	leftTalon.set(setLeftOutput.value);
    	//System.out.println("GetSetValue: "+this.tankProfile.getSetValue()+" LOUT: "+setLeftOutput.value + " ROUT: "+setRightOutput.value);

    	SmartDashboard.putNumber("left output", leftTalon.get());
    	SmartDashboard.putNumber("right output", rightTalon.get());
    	
    	System.out.println("left output: " + leftTalon.get() +" right output: " + rightTalon.get());
    	
    	if(motionProfileStarted){
        	this.tankProfile.startMotionProfile();
        	motionProfileStarted = false;
    	}
    	iterations++;
	}

	@Override
	protected boolean isFinished() {
		if( this.tankProfile.getTimeoutCnt() >2){
	    	return true;
    	}else if (this.tankProfile.isFinished()==true){
	        return true;
    	}else{
    		return false;
    	}
	}

	@Override
	protected void end() {
    	this.tankProfile.reset();
    	System.out.println("End");
	}

	@Override
	protected void interrupted() {
    	System.out.println("Interrupted");
		// TODO Auto-generated method stub
	}

}
