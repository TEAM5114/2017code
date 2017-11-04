package org.usfirst.frc5114.MyRobot2017.subsystems;

import org.usfirst.frc5114.MyRobot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;

public class Agitator extends Subsystem {

	private final CANTalon agitatorMotor = RobotMap.agitatorMotor;
	private boolean shouldBeOn;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void setAgitatorPower(double power){
		agitatorMotor.set(power);
	}
	
	
	public void toggle(){
		shouldBeOn = !shouldBeOn;
	}
	
	public boolean getOn(){
		return shouldBeOn;
	}
}
