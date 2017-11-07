package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {
	protected void initialize() {
		Robot.chassis.startClimb();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopClimb();
	}
	
	protected void interrupted() {
		end();
	}
}
