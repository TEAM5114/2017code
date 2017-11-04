package org.usfirst.frc5114.MyRobot2017.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

import java.io.File;

import org.usfirst.frc5114.MyRobot2017.GenerateTrajectories;
import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

public class DriveStraight extends Command 
{
	private Trajectory.Config config;
	private Waypoint[] points;
	private Trajectory trajectory;
	private DistanceFollower left;
	private DistanceFollower right;
	private TankModifier modifier;
	
	private Trajectory leftTraj;
	private Trajectory rightTraj;
	
	public double l;
	public double r;
	public double turn;
	
	public int i;
	
	public DriveStraight() 
	{
		requires(Robot.driveTrain);
	}
	
	public void initialize()
	{		
        RobotMap.frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
        RobotMap.frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
        RobotMap.rearRightMotor.changeControlMode(TalonControlMode.PercentVbus);
        RobotMap.rearLeftMotor.changeControlMode(TalonControlMode.PercentVbus);

		//config = new Trajectory.Config(fit, samples, dt, max_velocity, max_acceleration, max_jerk)
		this.config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 120, 240, 8000);
		this.points = new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(75, 0, 0)
		};
		
		this.trajectory = Pathfinder.generate(points, config);
		
		this.modifier = new TankModifier(trajectory).modify(19);
		
		this.leftTraj = modifier.getLeftTrajectory();
		this.rightTraj = modifier.getRightTrajectory();
		
		this.left = new DistanceFollower(leftTraj);
		left.reset();
		left.configurePIDVA(1.0, 0.0, 0.0, 1.0/67.0, 0.0);
		this.right = new DistanceFollower(rightTraj);
		right.reset();
		right.configurePIDVA(1.0, 0.0, 0.0, 1.0/67.0, 0);
        
        i = 1;
	}
	
	public void execute() 
	{
		
		SmartDashboard.putNumber("iterations", this.i);
		SmartDashboard.putNumber("left trajectory length", leftTraj.length());
		SmartDashboard.putNumber("right trajectory length", rightTraj.length());
		SmartDashboard.putNumber("left position", RobotMap.frontLeftMotor.getPosition());
		SmartDashboard.putNumber("right position", RobotMap.frontRightMotor.getPosition());
		
		this.l = left.calculate(RobotMap.frontLeftMotor.getPosition() * -6 * Math.PI);
		this.r = right.calculate(RobotMap.frontRightMotor.getPosition() * -6 * Math.PI);
		
		SmartDashboard.putNumber("left", (this.l / 12.0));
		SmartDashboard.putNumber("right", (this.r / 12.0));
		
		double gyroHeading = RobotMap.ahrs.getYaw();
		double desiredHeading = Pathfinder.r2d(left.getHeading());
		
		double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
		this.turn = 0.8 * (-1.0/80.0) * angleDifference;
		
		SmartDashboard.putNumber("turn", turn);

		Robot.chassis.tankDrive(((-1*(this.l + this.turn)) / 12.0), ((-1*(this.r - this.turn)) / 12.0));
		
		i++;
	}
	
	public boolean isFinished() 
	{
		return i > rightTraj.length();
	}
	
	public void end() 
	{
		
	}
	
	public void interrupted() 
	{
		
	}
}
