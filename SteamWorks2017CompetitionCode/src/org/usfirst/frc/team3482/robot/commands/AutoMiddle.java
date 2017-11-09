package org.usfirst.frc.team3482.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

	/*
	 * Drives forward, drops gear on peg, backs up
	 * 
	 * Runs in Autonomous mode when selected on SmartDashboard
	 */



public class AutoMiddle extends CommandGroup{
	public AutoMiddle() {
		addSequential(new AutoDrive(0.72, 0.8));
		addSequential(new AutoDrive(1.2, 0.4));
		addSequential(new AutoDrive(0.6, 0.0));
		addSequential(new RotateManipulator());
		addSequential(new AutoDrive(2, 0.0));
		addSequential(new AutoDrive(1.4, -0.4));
	}
}