package org.usfirst.frc.team3482.robot.subsystems;
import org.usfirst.frc.team3482.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
	
	public static final double PEG = -0.3;
	public static double startPosition;
	
	public CANTalon manipulatorTalon = RobotMap.gearManipulator;
	
	public GearManipulator() {
		manipulatorTalon.changeControlMode(TalonControlMode.Position);
		manipulatorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		manipulatorTalon.reverseSensor(false);
		manipulatorTalon.configNominalOutputVoltage(+0f, -0f);
		manipulatorTalon.configPeakOutputVoltage(+12f, -12f);
		manipulatorTalon.setAllowableClosedLoopErr(0);
		manipulatorTalon.setProfile(0);
		manipulatorTalon.setF(0.0);//0
		manipulatorTalon.setP(0.75);//0
		manipulatorTalon.setI(0.0);//0
		manipulatorTalon.setD(0.0);//0
		startPosition = manipulatorTalon.getPosition();
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

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
