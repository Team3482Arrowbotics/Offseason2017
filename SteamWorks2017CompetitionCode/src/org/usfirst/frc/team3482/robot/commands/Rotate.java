package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command 
{
	private double angle;
	public Rotate(double a)
	{
		angle=a;
	}
	protected void initialize(){
		RobotMap.turnController.reset();
		RobotMap.ahrs.reset();
		RobotMap.turnController.setSetpoint(angle);
		RobotMap.turnController.enable();
		
	}
	
	@Override
	protected void execute(){
		
	}
	protected boolean isFinished(){
		return true;
	}
	protected void end()
	{
		RobotMap.turnController.disable();
		isFinished();
	}
}