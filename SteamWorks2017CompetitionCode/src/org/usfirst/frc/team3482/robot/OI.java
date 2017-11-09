package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.IntakeBalls;
import org.usfirst.frc.team3482.robot.commands.ReverseFeed;
import org.usfirst.frc.team3482.robot.commands.RotateManipulator;
import org.usfirst.frc.team3482.robot.commands.RunGearManipWheels;
import org.usfirst.frc.team3482.robot.commands.ReverseGearManipWheels;
import org.usfirst.frc.team3482.robot.commands.Shoot;
import org.usfirst.frc.team3482.robot.commands.FlipDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xboxController;
	private static JoystickButton shootSequenceButton;
	private static JoystickButton intakeBallsButton;
	private static JoystickButton gearManipMoveButton;
	private static JoystickButton reverseFeedButton;
	public static JoystickButton gearWheelsButton;
	public static JoystickButton reverseGearWheelsButton;
	public static JoystickButton flipDriveButton;
	
	public OI () {
		xboxController = new Joystick(0);
		
		shootSequenceButton = new JoystickButton(xboxController, 6);
		shootSequenceButton.whileHeld(new Shoot());
		
		intakeBallsButton = new JoystickButton(xboxController, 5);
		intakeBallsButton.whileHeld(new IntakeBalls());
		
		gearManipMoveButton = new JoystickButton(xboxController, 2);
		gearManipMoveButton.whileHeld(new RotateManipulator());
		
		reverseFeedButton = new JoystickButton(xboxController, 3); //button 3 is X
		reverseFeedButton.whileHeld(new ReverseFeed());
		
		reverseGearWheelsButton = new JoystickButton(xboxController, 4);  //button 4 is Y
		reverseGearWheelsButton.whileHeld(new ReverseGearManipWheels());
		
		gearWheelsButton = new JoystickButton(xboxController, 1); //button 1 is A
		gearWheelsButton.whileHeld(new RunGearManipWheels());
		
		flipDriveButton = new JoystickButton(xboxController, 7); //button 7 is Back
		flipDriveButton.whenPressed(new FlipDrive());		
	}

	public Joystick getxboxController () {
		return xboxController;
	}
}
