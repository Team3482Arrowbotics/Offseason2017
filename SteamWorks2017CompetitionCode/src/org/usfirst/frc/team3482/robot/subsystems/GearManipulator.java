package org.usfirst.frc.team3482.robot.subsystems;
import org.usfirst.frc.team3482.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;


/*
 * This class defines commands within the Gear Manipulator subsystem
 * 
 * Anything running on the gear box traces back to here
 * 
 * Commands regarding the gear manipulator all reference this class, calling on the methods written here
 * 
 */


public class GearManipulator extends Subsystem {
	
	public static final double PEG = -0.3;
	
	public static double startPosition;
	
	public CANTalon manipulatorTalon = RobotMap.gearManipulator;
	
	public GearManipulator() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		manipulatorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		manipulatorTalon.reverseSensor(false);                  //Check this first when PID is being weird, incorrect value causes motor to spin indefinitely
		manipulatorTalon.configNominalOutputVoltage(+0f, -0f);
		manipulatorTalon.configPeakOutputVoltage(+12f, -12f);
		manipulatorTalon.setAllowableClosedLoopErr(0);
		manipulatorTalon.setProfile(0);
		manipulatorTalon.setF(0.0);
		manipulatorTalon.setP(0.75);     //Position value, corrects based on HOW FAR motor is from desired position
		manipulatorTalon.setI(0.0);		 //Integral value, corrects based on how corrections have ALREADY been made
		manipulatorTalon.setD(0.0);      //Derivative value, corrects based on how corrections are PREDICTED to be made
		startPosition = manipulatorTalon.getPosition();			//encoder can't use absolute positions, so we base everything off where it starts
																//this means we always have to start with the box in the same position (down)
	}

	public void moveGearManipStartPos() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		manipulatorTalon.set(startPosition);
	}
	
	public void moveGearManipReadyPos() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		manipulatorTalon.set(startPosition + PEG);
	}

	public double getGearManipPosition() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		return manipulatorTalon.get();
	}
	
	public void startGearWheels() {
		RobotMap.gearManipulatorWheels.set(0.6);
	}
	
	public void reverseGearWheels() {
		RobotMap.gearManipulatorWheels.set(-0.3);
	}
	
	public void stopGearWheels() {
		RobotMap.gearManipulatorWheels.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
