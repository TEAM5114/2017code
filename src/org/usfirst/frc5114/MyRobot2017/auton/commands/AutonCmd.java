package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5114.MyRobot2017.Robot;

/**
 * General form of an auton command: intended for use of only running 
 */
public class AutonCmd extends Command {

    protected String cmdName;
	protected double power, timeLimit;
    public static Timer timer;
    
    public AutonCmd(double percentVolt, double seconds, String name) {
        power =  percentVolt;
        timeLimit = seconds;
        cmdName = name;
        
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(cmdName + " Auton Command----------");
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= timeLimit;
    }

    // Called once after isFinished returns true
    protected void end(){
    	System.out.println("\t-Command isFinished...");
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("\t-Command interrupted...");
    	timer.stop();
    }
}
