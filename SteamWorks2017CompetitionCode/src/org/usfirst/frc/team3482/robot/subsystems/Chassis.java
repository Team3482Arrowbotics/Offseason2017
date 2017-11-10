package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/*
 * This class defines commands within the Chassis subsystem
 * 
 * Basically anything running on the main bot traces back to here
 * 
 * Most commands reference this class, calling on the methods written here
 * 
 */


public class Chassis extends Subsystem {

	private final RobotDrive robotDrive = RobotMap.driveRobot;
	double turnSpeed = -0.75;
	public int front = 1;
	
	public void drive(Joystick s) {
		double deadZone = 0.1;
		double leftY = s.getRawAxis(1)*front;       //Left joystick, forward/backward directions
		double rightX = s.getRawAxis(4);			//Right joystick, left/right directions

		if (leftY < deadZone && leftY > -deadZone) {
			leftY = 0;
		}													//this section prevents unintentional movement/drift

		if (rightX < deadZone && rightX > -deadZone) {
			rightX = 0;
		}
		
		if ((Robot.oi.xboxController.getRawAxis(2) != 0) && Robot.isDrive) {     //slow drive functionality
			robotDrive.arcadeDrive(leftY * 0.5, rightX * turnSpeed * 0.75);       
		} else if (Robot.isDrive){
			robotDrive.arcadeDrive(leftY, rightX * turnSpeed);                   //robotDrive.arcadeDrive is what's doing the real work here
		}
	}
	
	public void changeFront(){        //changes which end of the robot is forward on the left joystick
		if(front==1){				  //useful for driver orientation in placing gear vs shooting
			front=-1;
			SmartDashboard.putString("Robot Orientation", "Gear Forward");
		}
		else{
			front=1;
			SmartDashboard.putString("Robot Orientation", "Intake Forward");
		}
	}

	public void stopDrive() {
		robotDrive.stopMotor();
	}
	
	public void prepareShoot() {
		RobotMap.shooter.set(Robot.shooterSpeed);
	}
	
	public void startFeed() {
		RobotMap.feederWheel.set(-0.75);
		RobotMap.feederBelts.set(0.6);
		RobotMap.feederWheels.set(-0.6);
	}
	
	public void reverseFeed() {
		RobotMap.feederWheel.set(0.75);
		RobotMap.feederBelts.set(-0.6);
		RobotMap.feederWheels.set(0.6);
	}

	public void stopFeed() {
		RobotMap.feederWheel.set(0.0);
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

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
