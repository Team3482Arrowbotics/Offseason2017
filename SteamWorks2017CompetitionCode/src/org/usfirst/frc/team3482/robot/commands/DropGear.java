package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

	/*
	 * Spins gearbox wheels outwards while dropping the box
	 * 
	 * Used to place gear on peg
	 */



public class DropGear extends Command {
	protected void initialize() {
		Robot.gearManipulator.reverseGearWheels();
		Robot.gearManipulator.moveGearManipStartPos();

	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.gearManipulator.stopGearWheels();
		Robot.gearManipulator.moveGearManipReadyPos();

	}
	
	protected void interrupted() {
		end();
	}
}