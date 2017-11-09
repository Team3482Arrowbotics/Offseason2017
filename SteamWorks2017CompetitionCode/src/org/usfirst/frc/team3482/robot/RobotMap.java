package org.usfirst.frc.team3482.robot;


import com.ctre.CANTalon;
//import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This class defines all the motors, CANTalons etc
 * 
 * Always use this to connect port numbers to names that make sense
 * 
 */
public class RobotMap {
	public static CANTalon rearLeft;
	public static CANTalon frontLeft;
	public static CANTalon rearRight;
	public static CANTalon frontRight;
	public static CANTalon shooter;
	public static PIDController turnController;
	public static PIDController moveController;
	public static RobotDrive driveRobot;
	public static Counter counter;
	public static CANTalon gearManipulator;
	public static CANTalon gearManipulatorWheels;
	public static CANTalon feederWheel;
	public static CANTalon intake;
	public static CANTalon climber;
	public static CANTalon feederBelts;
	public static CANTalon feederWheels;

	public static void init() {
		driveRobot = new RobotDrive(rearLeft, frontLeft, rearRight, frontRight);
		driveRobot.setSafetyEnabled(false);
		rearLeft = new CANTalon(5);
		frontLeft = new CANTalon(6);
		rearRight = new CANTalon(7);
		frontRight = new CANTalon(4);
		gearManipulator = new CANTalon(11);
		gearManipulatorWheels = new CANTalon(10);
		intake = new CANTalon(8);
		climber = new CANTalon(9);
		climber.enableBrakeMode(true);
		climber.setCurrentLimit(39);
		shooter = new CANTalon(12);
		feederWheel = new CANTalon(3);
		feederBelts = new CANTalon(1);
		feederWheels = new CANTalon(2);
	}
}
