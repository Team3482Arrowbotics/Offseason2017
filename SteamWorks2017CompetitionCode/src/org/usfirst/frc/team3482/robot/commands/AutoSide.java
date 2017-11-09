package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSide extends CommandGroup {
	
	/*
	 * Supposed to drive, turn, drive, drop gear on peg, back up
	 * 
	 * Doesn't actually work because timing and positioning are hard
	 */
	
	
	public AutoSide(){
		addSequential(new AutoDrive(2.5, 0.8));
	}
}