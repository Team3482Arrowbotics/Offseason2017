package org.usfirst.frc.team3482.robot;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
import org.usfirst.frc.team3482.robot.commands.AutoMiddle;
import org.usfirst.frc.team3482.robot.commands.AutoSide;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.GearManipulator;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
//	UsbCamera cam;
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
		
		oi = new OI();
		gearManipulator = new GearManipulator();
		chassis = new Chassis();
		
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Auto", null);
		autoChooser.addObject("Side Auto", new AutoSide());
		autoChooser.addObject("Middle Auto", new AutoMiddle());
		SmartDashboard.putData("Auto mode", autoChooser);
		
		gearManipulator.moveGearManipReadyPos();
		
//		new Thread(() -> {
//			cam = CameraServer.getInstance().startAutomaticCapture(0);
//			cam.setBrightness(30);
//			cam.setExposureHoldCurrent();
//			cam.setExposureManual(5);
//			cam.setResolution(640, 480);
//			
//			CvSink cvSink = CameraServer.getInstance().getVideo();
//			CvSource outputStream = CameraServer.getInstance().putVideo("CrossHair", 640, 480);
//			
//			Mat source = new Mat();
//			Mat flipped = new Mat();
//			//Mat output = new Mat();
//			
//			while(!Thread.interrupted()) {
//				cvSink.grabFrame(source);
//				Core.flip(source, flipped, -1);
//				Imgproc.line(flipped, new Point(320, 0), new Point(320, 480), new Scalar(0, 255, 0), 6);
//				Imgproc.line(flipped, new Point(0, 240), new Point(640, 240), new Scalar(0, 255, 0), 6);
//				outputStream.putFrame(flipped);
//			}		
//		}).start();
	}

	public void disabledInit() {
		
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();
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
		shooterSpeed=(.6); //range -.5 to -.833
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		
		SmartDashboard.putNumber("Current Shooter Percentage: ", shooterSpeed);
		SmartDashboard.putNumber("Gear Manipulator Position: ", Robot.gearManipulator.getGearManipPosition());
	}
    
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}