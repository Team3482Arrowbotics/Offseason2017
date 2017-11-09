package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	/*
	 * Includes entire sequence for shooting, starts fly wheel then adds feed
	 * 
	 * Wait  loop ensures the wheel gets up to speed before balls start shooting
	 */



public class Shoot extends Command {
	CANTalon shooterMotor = RobotMap.shooter;
	private double waitLoop;
	
	protected void initialize() {
		waitLoop = 0;
	}
	
	protected void execute() {
		Robot.chassis.prepareShoot();
		waitLoop ++;
		if(waitLoop > 120) {
			Robot.chassis.startFeed();
			SmartDashboard.putNumber("Current Shooter Percentage", RobotMap.shooter.get());
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.chassis.stopFeed();
	}
	
	protected void interrupted() {
		end();
	}
}
