package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.Robot;
import org.usfirst.frc5114.MyRobot2017.RobotMap;
import org.usfirst.frc5114.MyRobot2017.commands.*;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem implements PIDOutput{
	private CANTalon driveLeftA = RobotMap.frontLeftMotor;
	private CANTalon driveLeftB = RobotMap.frontRightMotor;
	
	private CANTalon driveRightA = RobotMap.rearLeftMotor;
	private CANTalon driveRightB = RobotMap.rearRightMotor;
	
	private RobotDrive robotDrive = RobotMap.driveTrainRobotDrive2;

	private double encOffsetValueRight = 0;
	private double encOffsetValueLeft = 0;

	//using the Nav board
	public PIDController turnController;
	public AHRS ahrs = RobotMap.ahrs;

	static final double kP = 0.03; //TODO: adjust these
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;

	static final double kToleranceDegrees = 2.0f;

	boolean rotateToAngle = false;

	double rotateToAngleRate;

	public Chassis() {
		
		driveLeftA.enableBrakeMode(true);
		driveLeftB.enableBrakeMode(true);
		driveRightA.enableBrakeMode(true);
		driveRightB.enableBrakeMode(true);
		
		robotDrive = RobotMap.driveTrainRobotDrive2;

		// Set some safety controls for the drive system
		robotDrive.setSafetyEnabled(true);
		robotDrive.setExpiration(0.1);
		robotDrive.setSensitivity(0.5);
		robotDrive.setMaxOutput(1.0);
		//for the NavBoards

		//turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
		//turnController.setInputRange(-180.0f,  180.0f);
		//turnController.setOutputRange(-1.0, 1.0);
		//turnController.setAbsoluteTolerance(kToleranceDegrees);
		//turnController.setContinuous(true);
		//turnController.enable();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand( new DriveWithGamePad() );
	}

	public void driveByJoystick(double Y, double X) {
		SmartDashboard.putString("driveByJoystick?", Y + "," + X);
		robotDrive.arcadeDrive(Y,X);
	}

	public void drive(double moveValue, double rotateValue){
		robotDrive.arcadeDrive(moveValue, rotateValue);
	}
	
	public void tankDrive(double leftValue, double rightValue){
		robotDrive.tankDrive(leftValue, rightValue);
	}

	public void driveSpeed(double speed){
		robotDrive.drive(speed, 0);
	}

	public void stop() {
		robotDrive.drive(0, 0);
	}
	

	public void printEncoderValues() {
		getEncoderDistance();
	}

	public double getEncoderRight() {
		return -driveRightA.getPosition();
	}

	public double getEncoderLeft() {
		return driveLeftA.getPosition();
	}

	public double getEncoderDistance() {
		return (getEncoderRight() - encOffsetValueLeft) * RobotMap.DISTANCE_PER_PULSE;
	}

	public void resetEncoderDistance() {
		encOffsetValueRight = getEncoderRight();
		encOffsetValueLeft = getEncoderLeft();
		ahrs.resetDisplacement();
		getEncoderDistance();
	}

	public double getRotationAngleRate() {
		return rotateToAngleRate;
	}

	public double getGyroAngle() {
		return ahrs.getYaw();
	}

	public void resetGyro() {
		ahrs.zeroYaw();
	}

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}

	public void ahrsToSmartDashboard() {
		SmartDashboard.putBoolean("IMU_Connected",			ahrs.isConnected());
		SmartDashboard.putNumber("IMU_Yaw",              	ahrs.getYaw());
		SmartDashboard.putNumber("IMU_Pitch",            	ahrs.getPitch());
		SmartDashboard.putNumber("IMU_Roll",             	ahrs.getRoll());
		SmartDashboard.putNumber("IMU_RotateToAngleRate",	rotateToAngleRate);
		SmartDashboard.putNumber("IMU_X_Displacement", 		ahrs.getDisplacementX());
		SmartDashboard.putNumber("IMU_Y_Displacement", 		ahrs.getDisplacementY());
		SmartDashboard.putNumber("IMU_Z_Displacement", 		ahrs.getDisplacementZ());

		getEncoderDistance();
	}
}
