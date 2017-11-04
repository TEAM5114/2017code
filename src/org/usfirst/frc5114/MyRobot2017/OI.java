// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5114.MyRobot2017;

import org.usfirst.frc5114.MyRobot2017.auton.modes.VisionTurnTest;
import org.usfirst.frc5114.MyRobot2017.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick rightJoystick;
    public Joystick leftJoystick;
    public Joystick controlGamePad;
    public Joystick driveGamePad;
    public JoystickButton intake;
    public JoystickButton shoot;
    public JoystickButton agitators;
    public JoystickButton clearShooter;
    public JoystickButton zeroYaw;
    public JoystickButton bucket;
    public JoystickButton outtake;
    public static JoystickButton slowDrive;
    public JoystickButton autoPlace;
    public JoystickButton Flashlight;
    private static final double DEADBAND = 0.25;
	

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	
        
    	//Arcade Drive Controller (PLAYER 2)
    	driveGamePad = new Joystick(4);
    	
    	//A BUTTON: VISION GEAR PLACE
    	autoPlace = new JoystickButton(driveGamePad, 1);
        autoPlace.toggleWhenPressed(new VisionTurnTest());
        
        //RB: SLOW DRIVE
        slowDrive = new JoystickButton(driveGamePad, 6);
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    	//Robot Controls Controller (PLAYER 1)
    	controlGamePad = new Joystick(3);
    	
    	//A BUTTON: FLASHLIGHT
        Flashlight = new JoystickButton (controlGamePad, 1);
        Flashlight.toggleWhenPressed(new ToggleFlashlight());
        
        //B BUTTON: AGITATORS
        agitators = new JoystickButton(controlGamePad, 2);
        agitators.toggleWhenPressed(new RunAgitator());
        agitators.toggleWhenPressed(new LoadBall());
        
        //Y BUTTON: BUCKET
        bucket = new JoystickButton(controlGamePad, 4);
        bucket.toggleWhenPressed(new KickOutBucket());
        
        //LB: INTAKE
        intake = new JoystickButton(controlGamePad, 5);
        intake.toggleWhenPressed(new RunIntake());
        
        //RB: INTAKE
        shoot = new JoystickButton(controlGamePad, 6);
        shoot.toggleWhenPressed(new RunShooter());
        
        //BACK: CLEAR SHOOTER
        clearShooter = new JoystickButton(controlGamePad, 7);
        clearShooter.whileHeld(new ClearShooter());
        
        //START: ZERO YAW
        zeroYaw = new JoystickButton(controlGamePad, 8);
        


        // SmartDashboard Buttons

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getLeftJoystick() {
        return leftJoystick;
    }

    public Joystick getRightJoystick() {
        return rightJoystick;
    }

    public Joystick getDriveGamePad() {
    	return driveGamePad;
 
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

