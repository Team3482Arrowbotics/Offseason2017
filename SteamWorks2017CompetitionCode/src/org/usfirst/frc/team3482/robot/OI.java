package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.IntakeBalls;
import org.usfirst.frc.team3482.robot.commands.ReverseFeed;
import org.usfirst.frc.team3482.robot.commands.RotateManipulator;
import org.usfirst.frc.team3482.robot.commands.RunGearManipWheels;
import org.usfirst.frc.team3482.robot.commands.ReverseGearManipWheels;
import org.usfirst.frc.team3482.robot.commands.Shoot;
import org.usfirst.frc.team3482.robot.subsystems.ManipulatorPosition;

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
	public Joystick joystick;
	private static JoystickButton reversePolyButton;
	public static JoystickButton GearWheelsButton;
	
	public OI () {
		xboxController = new Joystick(0);
		
		shootSequenceButton = new JoystickButton(xboxController, 6);
		shootSequenceButton.whileHeld(new Shoot());
		
		intakeBallsButton = new JoystickButton(xboxController, 5);
		intakeBallsButton.whileHeld(new IntakeBalls());
		
		gearManipMoveButton = new JoystickButton(xboxController, 2);
		gearManipMoveButton.whileHeld(new RotateManipulator(ManipulatorPosition.PEG));
		
		reversePolyButton = new JoystickButton(xboxController, 3); //button 3 is X
		reversePolyButton.whileHeld(new ReverseFeed());
		
		reversePolyButton = new JoystickButton(xboxController, 4);  //button 4 is Y
		reversePolyButton.whileHeld(new ReverseGearManipWheels());
		
		GearWheelsButton = new JoystickButton(xboxController, 1); //button 1 is A
		GearWheelsButton.whileHeld(new RunGearManipWheels());
	}

	public Joystick getxboxController () {
		return xboxController;
	}
}
