package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.IntakeBalls;
import org.usfirst.frc.team3482.robot.commands.ReverseFeed;
import org.usfirst.frc.team3482.robot.commands.RotateManipulator;
import org.usfirst.frc.team3482.robot.commands.RunGearManipWheels;
import org.usfirst.frc.team3482.robot.commands.DropGear;
import org.usfirst.frc.team3482.robot.commands.Shoot;
import org.usfirst.frc.team3482.robot.commands.FlipDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is used to connect all commands to the controller
 * 
 * Use this for setting up buttons and defining their usage
 * 
 */
public class OI {
	public Joystick xboxController;
	private static JoystickButton shootSequenceButton;
	private static JoystickButton intakeBallsButton;
	private static JoystickButton rotateGearManipButton;
	private static JoystickButton reverseFeedButton;
	public static JoystickButton gearWheelsButton;
	public static JoystickButton dropGearButton;
	public static JoystickButton flipDriveButton;
	
	public OI () {
		xboxController = new Joystick(0);
		
		shootSequenceButton = new JoystickButton(xboxController, 6); //button 6 is Right Bumper
		shootSequenceButton.whileHeld(new Shoot());
		
		intakeBallsButton = new JoystickButton(xboxController, 5); //button 5 is Left Bumper
		intakeBallsButton.whileHeld(new IntakeBalls());
		
		rotateGearManipButton = new JoystickButton(xboxController, 2); //button 2 is B
		rotateGearManipButton.whileHeld(new RotateManipulator());
		
		reverseFeedButton = new JoystickButton(xboxController, 3); //button 3 is X
		reverseFeedButton.whileHeld(new ReverseFeed());
		
		dropGearButton = new JoystickButton(xboxController, 4);  //button 4 is Y
		dropGearButton.whileHeld(new DropGear());
		
		gearWheelsButton = new JoystickButton(xboxController, 1); //button 1 is A
		gearWheelsButton.whileHeld(new RunGearManipWheels());
		
		flipDriveButton = new JoystickButton(xboxController, 7); //button 7 is Back
		flipDriveButton.whenPressed(new FlipDrive());		
	}

	public Joystick getxboxController () {
		return xboxController;
	}
}
