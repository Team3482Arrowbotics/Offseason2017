package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private final RobotDrive robotDrive = RobotMap.driveRobot;
	double turnSpeed = -0.75;

	public void drive(Joystick s) {
		double deadZone = 0.1;
		double leftY = s.getRawAxis(1);
		double rightX = s.getRawAxis(4);

		if (leftY < deadZone && leftY > -deadZone) {
			leftY = 0;
		}

		if (rightX < deadZone && rightX > -deadZone) {
			rightX = 0;
		}

		if ((Robot.oi.xboxController.getRawAxis(2) != 0) && Robot.isDrive) {
			robotDrive.arcadeDrive(leftY * 0.5, rightX * turnSpeed * 0.75);
		} else if (Robot.isDrive){
			robotDrive.arcadeDrive(leftY, rightX * turnSpeed);
		}
	}

	public void stopDrive() {
		robotDrive.stopMotor();
	}
	
	public void prepareShoot() {
		//RobotMap.shooter.set(-0.075*(2 - (Robot.oi.getflightStick().getRawAxis(3) + 1)) - 0.6);
		//RobotMap.shooter.set(-((1-Robot.oi.getflightStick().getRawAxis(3))/2));
		RobotMap.shooter.set(Robot.shooterSpeed);
	}
	
	public void stopPrepareShoot() {
		RobotMap.feederBelts.set(0.0);
		RobotMap.feederWheels.set(0.0);
		RobotMap.shooter.set(0.0);
		RobotMap.feeder.set(0.0);
	}
	
	public void startFeed() {
		RobotMap.feeder.set(-0.75);
		RobotMap.feederBelts.set(0.6);
		RobotMap.feederWheels.set(-0.6);
	}
	
	public void reverseFeed() {
		RobotMap.feeder.set(0.75);
		RobotMap.feederBelts.set(-0.6);
		RobotMap.feederWheels.set(0.6);
	}

	public void stopFeed() {
		RobotMap.feeder.set(0.0);
		RobotMap.feederBelts.set(0.0);
		RobotMap.feederWheels.set(0.0);
		RobotMap.shooter.set(0.0);
	}

	public void startIntake() {
		RobotMap.intake.set(-0.6);
	}
	
	public void stopIntake() {
		RobotMap.intake.set(0.0);
	}
	
	public void startGearWheels() {
		RobotMap.gearManipulatorWheels.set(0.6);
	}
	
	public void reverseGearWheels() {
		RobotMap.gearManipulatorWheels.set(-0.3);
	}
	
	public void stopGearWheels() {
		RobotMap.gearManipulatorWheels.set(0.0);
	}
	
	public void startClimb() {
		RobotMap.climber.set(-1.0);
	}
	
	public void stopClimb() {
		RobotMap.climber.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
