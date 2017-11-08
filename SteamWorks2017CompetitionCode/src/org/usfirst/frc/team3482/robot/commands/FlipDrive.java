package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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