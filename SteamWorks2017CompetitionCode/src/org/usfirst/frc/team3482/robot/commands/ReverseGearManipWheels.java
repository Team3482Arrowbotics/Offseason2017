package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ReverseGearManipWheels extends Command {
	protected void initialize() {
		Robot.chassis.reverseGearWheels();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopGearWheels();
	}
	
	protected void interrupted() {
		end();
	}
}