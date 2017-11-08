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
 * The RobotMap is a mapping from the ports. Sensors and actuators are wired
 * into to a variable name. This provides flexibility changing wiring, makes
 * checking the wiring easier and significantly reduces the number of magic
 * numbers floating around.
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
	public static CANTalon feeder;
	public static CANTalon intake;
	public static CANTalon climber;
	public static CANTalon feederBelts;
	public static CANTalon feederWheels;
//	public static Encoder driveEncoder1;
//	public static Encoder driveEncoder2;

	public static void init() {
		rearLeft = new CANTalon(5); //5
		frontLeft = new CANTalon(6); //6
		rearRight = new CANTalon(7); //7
		frontRight = new CANTalon(4); //4
		driveRobot = new RobotDrive(rearLeft, frontLeft, rearRight, frontRight);
		driveRobot.setSafetyEnabled(false);
		gearManipulator = new CANTalon(11);//11  //0 on test board
		gearManipulatorWheels = new CANTalon(10);

//		driveEncoder1 = new Encoder(0,1);
//		driveEncoder1.reset();
//		driveEncoder2 = new Encoder(2,3);
//		driveEncoder2.reset();
//		
//		ahrs = new AHRS(SPI.Port.kMXP);
//		turnController = new PIDController(0.3, 0.0, 0.0, 0.0, ahrs, new TurnPID(driveRobot));
//		turnController.setInputRange(-180f, 180f);
//		turnController.setOutputRange(-1, 1);
//		turnController.setAbsoluteTolerance(5f);
//		turnController.setContinuous(true);
//		
//		moveController = new PIDController(0.005,0.00001,0,500, driveEncoder2, new TalonDrive(driveRobot));
//		moveController.setInputRange(-20000, 20000);
//		moveController.setOutputRange(-1,1);
//		moveController.setAbsoluteTolerance(4);
//		moveController.setContinuous(true);
		
		intake = new CANTalon(8);
		
		climber = new CANTalon(9);
		climber.enableBrakeMode(true);
		climber.setCurrentLimit(39);
		
		feeder = new CANTalon(3);
		shooter = new CANTalon(12);
		feederBelts = new CANTalon(1);
		feederWheels = new CANTalon(2);
	}
}
