package org.usfirst.frc5114.MyRobot2017;

import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class GenerateTrajectories 
{
	private Trajectory.Config config;
	private Waypoint[] points;
	private Trajectory trajectory;
	
	public TankModifier modifier;
	public static Trajectory leftTraj;
	public static Trajectory rightTraj;
	
	public static DistanceFollower left;
	public static DistanceFollower right;
	
	public GenerateTrajectories()
	{
		
	}
	
	public void generate()
	{
		config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 67, 79, 2362);
		points = new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(75, 0 ,0)
		};
		
		trajectory = Pathfinder.generate(points, config);
		
		modifier = new TankModifier(trajectory).modify(1);
		
		Trajectory leftTraj = modifier.getLeftTrajectory();
		Trajectory rightTraj = modifier.getRightTrajectory();
		
		left = new DistanceFollower(leftTraj);
		left.configurePIDVA(1.0, 0.0, 0.0, 1.0/67.0, 0.0);
		right = new DistanceFollower(rightTraj);
		right.configurePIDVA(1.0, 0.0, 0.0, 1.0/67.0, 0);
	}
}
