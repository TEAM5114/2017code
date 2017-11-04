package org.usfirst.frc5114.MyRobot2017;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.modifiers.TankModifier;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GenerateSRXMotionProfile 
{
	private Trajectory.Config config;
	private Trajectory trajectory;
	private TankModifier modifier;
	private Trajectory[] tank = new Trajectory[2];
	
	private CANTalon.MotionProfileStatus mpStatus = new CANTalon.MotionProfileStatus();
	private CANTalon lTalon;
	private CANTalon rTalon;
	private int mpState = 0;
	private int mpLoopTimeout = -1;
	private boolean mpStart = false;
	private CANTalon.SetValueMotionProfile mpEnable = CANTalon.SetValueMotionProfile.Disable;
	private static final int kMinPointsInTalon = 5;
	private static final int kNumLoopsTimeout = 10;
	private boolean mpFin = false;
	private int mpTimeouts = 0;
	
	private int mpIndex;

	//private boolean isLeft;
	private boolean isInverted;

	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {  lTalon.processMotionProfileBuffer();rTalon.processMotionProfileBuffer();    }
	}
	Notifier notifer = new Notifier(new PeriodicRunnable());

	public GenerateSRXMotionProfile(CANTalon left, CANTalon right, boolean isInverted) {
		this.lTalon = left;
		this.rTalon= right;
		this.lTalon.changeMotionControlFramePeriod(5);
		this.rTalon.changeMotionControlFramePeriod(5);
		notifer.startPeriodic(0.005);

		this.isInverted  = isInverted;

	}

	public void reset() {
		this.lTalon.clearMotionProfileTrajectories();
		this.rTalon.clearMotionProfileTrajectories();
		this.mpEnable = CANTalon.SetValueMotionProfile.Disable;
		this.mpState = 0;
		this.mpIndex = 0;
		this.mpLoopTimeout = -1;
		this.mpStart = false;
//		System.out.println("Reset");
	}

	public void control() {
		System.out.println("mpState: "+ mpState + " mpStart: " + mpStart + " mpFin: " + mpFin);
		System.out.println("MP Timeouts: " + mpTimeouts+ " MP Loop Timeouts: " + mpLoopTimeout);
		this.lTalon.getMotionProfileStatus(mpStatus);
		this.rTalon.getMotionProfileStatus(mpStatus);
		if (this.mpLoopTimeout < 0) {
		} else {
			if (this.mpLoopTimeout == 0) {
				++this.mpTimeouts;
			} else {
				--this.mpLoopTimeout;
			}
		}

		if (lTalon.getControlMode() != TalonControlMode.MotionProfile) {
			this.mpState = 0;
			this.mpLoopTimeout = -1;
		} else {
			switch (mpState) {
				case 0: /* wait for application to tell us to start a MP */
					if (this.mpStart) {
						this.mpStart = false;
						this.mpEnable = CANTalon.SetValueMotionProfile.Disable;
						startFilling(this.tank, isInverted);
						this.mpState = 1;
						this.mpLoopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1: 
					if (mpStatus.btmBufferCnt > kMinPointsInTalon) {
						this.mpEnable = CANTalon.SetValueMotionProfile.Enable;
						this.mpState = 2;
						this.mpLoopTimeout = kNumLoopsTimeout;
					}
					
					break;
				case 2:
					if (this.mpStatus.isUnderrun == false) {
						this.mpLoopTimeout = kNumLoopsTimeout;
					}
					if (this.mpStatus.activePointValid && this.mpStatus.activePoint.isLastPoint) {
						this.mpEnable = CANTalon.SetValueMotionProfile.Hold;
						this.mpState = 0;
						this.mpLoopTimeout = -1;
						this.mpFin= true;
					}
					break;
			}
	
		}
	}

	private void startFilling(Trajectory[] t, boolean invert) {
		if (this.mpStatus.hasUnderrun) {
			this.lTalon.clearMotionProfileHasUnderrun();
			this.rTalon.clearMotionProfileHasUnderrun();
//			System.out.println("MP is in Underrun");
		}
		this.lTalon.clearMotionProfileTrajectories();
		this.rTalon.clearMotionProfileTrajectories();
		
//		double enterTime;
//		double exitTime;
		
		TrajectoryPoint[][] points = new TrajectoryPoint[t[0].length()][2];
		SmartDashboard.putNumber("Traj Point",t[0].length());
		//for (mpIndex; mpIndex < points.length; mpIndex++) {
		while (mpIndex < points.length) {
//			enterTime = Timer.getFPGATimestamp();
//			System.out.println("points.length: " + points.length);
//			System.out.println("i: " + mpIndex + ", traj points count: " + t[0].length());
//			System.out.println("Time:, "+enterTime);
			Segment left = t[0].get(mpIndex);
			Segment right = t[1].get(mpIndex);
			
			TrajectoryPoint leftPoint = new TrajectoryPoint();
			TrajectoryPoint rightPoint = new TrajectoryPoint();
			leftPoint.position = left.position/(6*Math.PI);
			rightPoint.position = -right.position/(6*Math.PI);
			leftPoint.velocity = left.velocity * 60/(6*Math.PI);
			rightPoint.velocity = -right.velocity * 60/(6*Math.PI);
			leftPoint.timeDurMs = (int) (left.dt * 1000.0);
			rightPoint.timeDurMs = (int) (right.dt * 1000.0);
			leftPoint.profileSlotSelect = 1;
			rightPoint.profileSlotSelect = 1;
			leftPoint.velocityOnly = false;
			rightPoint.velocityOnly = false;
			leftPoint.zeroPos = mpIndex == 0;
			rightPoint.zeroPos = mpIndex == 0;
			leftPoint.isLastPoint = mpIndex == t[0].length() - 1;
			rightPoint.isLastPoint = mpIndex == t[0].length() - 1;

			if (invert) {
				leftPoint.position = -leftPoint.position;
				leftPoint.velocity = -leftPoint.velocity;
				rightPoint.position = rightPoint.position;
				rightPoint.velocity = rightPoint.velocity;
			}
			this.lTalon.pushMotionProfileTrajectory(leftPoint);
			this.rTalon.pushMotionProfileTrajectory(rightPoint);
//			System.out.println("LTraj Pos: " + left.position + ", Left Velocity: " + left.velocity + ", Left Time: " + left.dt);
			System.out.println("Left Pos: " + leftPoint.position + ", Left Velocity: " + leftPoint.velocity + ", Left Time: " + leftPoint.timeDurMs + ", " + leftPoint.isLastPoint);
			System.out.println("LEncPos: " + lTalon.getPosition()+ " LEncVel: " + lTalon.getSpeed());
//			System.out.println("RTraj Pos: " + right.position + ", Right Velocity: " + right.velocity + ", Right Time: " + right.dt);System.out.println("Right Pos: " + rightPoint.position + ", Right Velocity: " + rightPoint.velocity + ", Right Time: " + rightPoint.timeDurMs + ", " + rightPoint.isLastPoint);
			System.out.println("Right Pos: " + rightPoint.position + ", Right Velocity: " + rightPoint.velocity + ", Right Time: " + rightPoint.timeDurMs + ", " + rightPoint.isLastPoint);
			System.out.println("REncPos: " + rTalon.getPosition()+ " REncVel: " + rTalon.getSpeed());
//			System.out.println("Bottom Buffer: "+ mpStatus.btmBufferCnt + " Top Buffer: " + mpStatus.topBufferCnt);
			
			mpIndex++;
		}
	}

	public void startMotionProfile() {
		this.mpStart = true;
		this.mpFin = false;
		this.mpTimeouts = 0;
	}

	public boolean isFinished(){
		return this.mpFin;
	}

	public int getTimeoutCnt(){
		return this.mpTimeouts;
	}

	public CANTalon.SetValueMotionProfile getSetValue() {
		return this.mpEnable;
	}
	
	public void generateTrajectory(Waypoint[] points){
		this.config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 100, 100, 2362);
		this.trajectory = Pathfinder.generate(points, config);
	}

	public void generateTankTrajectory(Waypoint[] points){
		this.config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 100, 50, 2362);
		this.trajectory = Pathfinder.generate(points, config);
		this.modifier = new TankModifier(trajectory).modify(19);
		this.tank[0] = modifier.getLeftTrajectory();
		this.tank[1] = modifier.getRightTrajectory();
	}

/*
	public TrajectoryPoint[] generateMotionProfile(Trajectory t, boolean invert) {
		if (mpStatus.hasUnderrun) {
			talon.clearMotionProfileHasUnderrun();
		}
		talon.clearMotionProfileTrajectories();
		
		trajPoints = new TrajectoryPoint[t.length()];
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
		TrajectoryPoint[][] points = new TrajectoryPoint[t[0].length()][2];
		for (int i = 0; i < points[0].length; i++) {
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

			points[i][0] = leftPoint;
			points[i][1] = rightPoint;
		}
		return points;
	}
	*/
}
