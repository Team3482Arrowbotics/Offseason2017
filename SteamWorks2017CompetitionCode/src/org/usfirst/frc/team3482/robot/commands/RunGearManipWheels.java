package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

	/*
	 * Spins wheels on gear box inwards
	 * 
	 * Used exclusively for picking up gears
	 */



public class RunGearManipWheels extends Command {
	protected void initialize() {
		Robot.gearManipulator.startGearWheels();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.gearManipulator.stopGearWheels();
	}
	
	protected void interrupted() {
		end();
	}
}