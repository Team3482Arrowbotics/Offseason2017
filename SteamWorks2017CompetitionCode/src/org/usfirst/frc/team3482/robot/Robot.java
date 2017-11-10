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

/*
 * This class is what actually runs in game
 * 
 * It defines what happens when the robot initializes and what it does while it runs
 * 
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
	public int wait;
	
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

	
	
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	
	
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
			shooterSpeed=-.7;
			wait=0;
			chassis.front=1;
			SmartDashboard.putString("Robot Orientation", "Intake Forward");
	}
	
	
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		chassis.drive(oi.xboxController);
		
		if(oi.xboxController.getRawAxis(3) > 0.1){					//the right trigger isn't a button so we can't define it in OI
			RobotMap.climber.set(-1);								//OI only runs once, we need to constantly check the value
		} else{
			RobotMap.climber.set(0);
		}
		
		wait++;
		if(oi.xboxController.getPOV() == 0 && shooterSpeed>-.9 && wait>10)      
		{										 //*EXPERIMENTAL*
			shooterSpeed-=.025;					 //*Meant to raise/lower shooter speed using up/down on D Pad*
			wait=0;								 //*Same as right trigger, isn't a button but a POV so it gives angles 0-315*
		}										 //*Up is 0, Right is 90, Down is 180, Left is 270, half intervals at 45s*
		else if(oi.xboxController.getPOV()==180 && shooterSpeed<-.6 && wait>10) 
		{										 //*
			shooterSpeed+=.025;					 //*EXPERIMENTAL*
			wait=0;
		}
		
		SmartDashboard.putNumber("D-Pad: ", oi.xboxController.getPOV());         //Displays relevant info on SmartDashboard
		SmartDashboard.putNumber("Current Shooter Percentage: ", shooterSpeed);
		SmartDashboard.putNumber("Gear Manipulator Position: ", Robot.gearManipulator.getGearManipPosition()); 
	}
    
	
	
	public void testPeriodic() {
		LiveWindow.run();		    //this lets you change values manually for each CANTalon, moving motors without code
	}
}