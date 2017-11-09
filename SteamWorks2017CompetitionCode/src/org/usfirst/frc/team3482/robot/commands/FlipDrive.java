package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

	/*
	 * Changes which end of the robot is regarded as front
	 * 
	 * Useful to prevent confusion in dropping off gear and shooting
	 * 
	 * Initially intake is front, pressing Back toggles
	 */



public class FlipDrive extends Command {
	protected void initialize() {
		Robot.chassis.changeFront();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
	}
	
	protected void interrupted() {
		end();
	}
}