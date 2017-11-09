package org.usfirst.frc.team3482.robot.commands;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;
public class AutoDrive extends TimedCommand{

	/*
	 * Base Autonomous command, called by actual sequences
	 * 
	 * This is NOT an autonomous to run, it is just used by the actual commands
	 * 
	 * Others are built off of this to do timed driving
	 */
	
	
	
	private double speed = 0.0;
	public AutoDrive(double timeout, double s) {
		super(timeout);
		speed = s;
		// TODO Auto-generated constructor stub
	}
	protected void initialize(){
		Robot.isDrive = false;
		RobotMap.driveRobot.arcadeDrive(speed, 0);
	}
	protected void interrupted(){
		end();
	}
	protected void end(){
		Robot.isDrive = true;
		Robot.chassis.stopDrive();
	}	
}