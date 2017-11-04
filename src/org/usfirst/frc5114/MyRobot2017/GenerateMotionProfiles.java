package org.usfirst.frc5114.MyRobot2017;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;

import com.ctre.CANTalon.TrajectoryPoint;

public class GenerateMotionProfiles 
{
	private Trajectory.Config config;
	private Waypoint[] points;
	private Trajectory trajectory;
	private DistanceFollower left;
	private DistanceFollower right;
	private TankModifier modifier;

	public Trajectory generateTrajectory(Waypoint[] points){
		config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 67, 79, 2362);
		points = new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(75, 0 ,0)
		};
		
		trajectory = Pathfinder.generate(points, config);
		
		return trajectory;
	}

	public Trajectory[] generateTankTrajectory(Waypoint[] points){
		Trajectory[] tank = new Trajectory[2];
		
		config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 67, 79, 2362);
		points = new Waypoint[] {
				new Waypoint(0, 0, 0),
				new Waypoint(75, 0 ,0)
		};
		
		trajectory = Pathfinder.generate(points, config);
		
		modifier = new TankModifier(trajectory).modify(19);
		
		tank[0] = modifier.getLeftTrajectory();
		tank[1] = modifier.getRightTrajectory();
		
		return tank;
	}

	public TrajectoryPoint[] generateMotionProfile(Trajectory t, boolean invert) {
		TrajectoryPoint[] trajPoints = new TrajectoryPoint[t.length()];
		for (int i = 0; i < trajPoints.length; i++) {
			Segment s = t.get(i);
			TrajectoryPoint point = new TrajectoryPoint();
			point.position = s.position/(6*Math.PI);
			point.velocity = s.velocity * 60/(6*Math.PI);
			point.timeDurMs = (int) (s.dt * 1000.0);
			point.profileSlotSelect = 1;
			point.velocityOnly = false;
			point.zeroPos = i == 0;
			point.isLastPoint = i == t.length() - 1;

			if (invert) {
				point.position = -point.position;
				point.velocity = -point.velocity;
			}

			trajPoints[i] = point;
		}
		return trajPoints;
	}
	
	public static TrajectoryPoint[][] generateTankMotionProfile(Trajectory[] t, boolean invert) {
		TrajectoryPoint[][] trajPoints = new TrajectoryPoint[t[0].length()][2];
		for (int i = 0; i < trajPoints[0].length; i++) {
			Segment left = t[0].get(i);
			Segment right = t[1].get(i);
			
			TrajectoryPoint leftPoint = new TrajectoryPoint();
			TrajectoryPoint rightPoint = new TrajectoryPoint();
			leftPoint.position = left.position/(6*Math.PI);
			rightPoint.position = right.position/(6*Math.PI);
			leftPoint.velocity = left.velocity * 60/(6*Math.PI);
			rightPoint.velocity = right.velocity * 60/(6*Math.PI);
			leftPoint.timeDurMs = (int) (left.dt * 1000.0);
			rightPoint.timeDurMs = (int) (right.dt * 1000.0);
			leftPoint.profileSlotSelect = 1;
			rightPoint.profileSlotSelect = 1;
			leftPoint.velocityOnly = false;
			rightPoint.velocityOnly = false;
			leftPoint.zeroPos = i == 0;
			rightPoint.zeroPos = i == 0;
			leftPoint.isLastPoint = i == t[0].length() - 1;
			rightPoint.isLastPoint = i == t[0].length() - 1;

			if (invert) {
				leftPoint.position = -leftPoint.position;
				leftPoint.velocity = -leftPoint.velocity;
				rightPoint.position = -rightPoint.position;
				rightPoint.velocity = -rightPoint.velocity;
			}

			trajPoints[i][0] = leftPoint;
			trajPoints[i][1] = rightPoint;
		}
		return trajPoints;
	}
}
