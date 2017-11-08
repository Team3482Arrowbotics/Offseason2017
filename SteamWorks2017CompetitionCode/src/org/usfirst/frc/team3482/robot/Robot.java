package org.usfirst.frc.team3482.robot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
		
		oi = new OI();
		gearManipulator = new GearManipulator();
		chassis = new Chassis();
		
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Auto", null);
		autoChooser.addObject("Side Auto", new AutoSide());
		autoChooser.addObject("Middle Auto", new AutoMiddle());
		SmartDashboard.putData("Auto mode", autoChooser);
		
		gearManipulator.moveGearManipReadyPos();	
		
		cam = CameraServer.getInstance().startAutomaticCapture(0);
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
			shooterSpeed=-.6;
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		chassis.drive(oi.xboxController);
		
		if(oi.xboxController.getRawAxis(3) > 0.1){
			RobotMap.climber.set(-1);
		} else{
			RobotMap.climber.set(0);
		}
		
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