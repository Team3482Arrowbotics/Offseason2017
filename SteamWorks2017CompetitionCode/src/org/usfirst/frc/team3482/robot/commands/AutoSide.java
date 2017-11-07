package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSide extends CommandGroup {
	
	public AutoSide(){
		addSequential(new AutoDrive(2.5, 0.8));
	}
}