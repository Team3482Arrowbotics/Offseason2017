package org.usfirst.frc.team3482.robot;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3482.robot.commands.AutoMiddle;
import org.usfirst.frc.team3482.robot.commands.autoOther;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	UsbCamera cam;
	public static double shooterSpeed = 0.0;
	public static boolean isDrive = true;
	public static FeedbackDeviceStatus status;
	public static Chassis chassis;
	public static GearManipulator gearManipulator;
	public static OI oi;
	SendableChooser<Command> autoChooser;
	public Command autonomousCommand;

	public void robotInit() {
		
		RobotMap.init();
		status = RobotMap.gearManipulator.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		
		gearManipulator = new GearManipulator();
		chassis = new Chassis();
		
		oi = new OI();
		
		//GearManipulator.toggleWheelsButton = new JoystickButton(Robot.oi.getflightStick(), 10);
		
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Default Auto", null);
		autoChooser.addObject("Select if on the side positions", new autoOther());
		autoChooser.addObject("Select if we start middle position", new AutoMiddle());

		
		RobotMap.ahrs.reset();
		
		LiveWindow.addActuator("Navx", "1", RobotMap.ahrs);
		LiveWindow.addActuator("Turn", "1", RobotMap.turnController);
		
		SmartDashboard.putData("Auto mode", autoChooser);
		gearManipulator.moveGearManipReadyPos();
		new Thread(() -> {
			cam = CameraServer.getInstance().startAutomaticCapture(0);
			cam.setBrightness(30);
			cam.setExposureHoldCurrent();
			cam.setExposureManual(5);
			cam.setResolution(640, 480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("CrossHair", 640, 480);
			
			Mat source = new Mat();
			Mat flipped = new Mat();
			//Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Core.flip(source, flipped, -1);
				Imgproc.line(flipped, new Point(320, 0), new Point(320, 480), new Scalar(0, 255, 0), 6);
				Imgproc.line(flipped, new Point(0, 240), new Point(640, 240), new Scalar(0, 255, 0), 6);
				outputStream.putFrame(flipped);
			}		
		}).start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		
	}
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		RobotMap.ahrs.reset();
		autonomousCommand = new AutoMiddle();
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		shooterSpeed=(.6); //range -.5 to -.833
		SmartDashboard.putNumber("Current Shooter Percentage: ", shooterSpeed);
		
		Robot.chassis.drive(Robot.oi.getxboxController());
		if(Robot.oi.getxboxController().getRawAxis(3) > 0) {
			Robot.chassis.startClimb();
		} else {
			Robot.chassis.stopClimb();
		}
		
		SmartDashboard.putNumber("Shooter Speed: ", RobotMap.shooter.getSpeed());
		SmartDashboard.putNumber("Gear Manipulator Position: ", Robot.gearManipulator.getGearManipPosition());
	
		SmartDashboard.putNumber("Feeder Speed: ", RobotMap.feeder.get());
	}
    
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}