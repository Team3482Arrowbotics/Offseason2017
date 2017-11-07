package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RotateManipulator extends Command
{
	public RotateManipulator() {
	}
	
	protected void initialize() {
		Robot.gearManipulator.moveGearManipStartPos();
	}
	
	protected void execute() {
		
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void interrupted() {
		Robot.gearManipulator.moveGearManipReadyPos();
	}
	
	protected void end() {
		
	}
}