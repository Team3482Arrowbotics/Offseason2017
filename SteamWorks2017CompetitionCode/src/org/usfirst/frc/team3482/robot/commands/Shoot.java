package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot extends Command {
	CANTalon shooterMotor = RobotMap.shooter;
	//private double speedThreshold = -72000;
	private double waitLoop = 0;
	protected void initialize() {
		waitLoop = 0;
	}
	
	protected void execute() {
		Robot.chassis.prepareShoot();
		waitLoop ++;
		if(/*(shooterMotor.getSpeed() < speedThreshold) && */(waitLoop > 120)) {
			Robot.chassis.startFeed();
			SmartDashboard.putNumber("Current Shooter Percentage", RobotMap.shooter.get());
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopPrepareShoot();
		Robot.chassis.stopFeed();
	}
	
	protected void interrupted() {
		end();
	}
}
