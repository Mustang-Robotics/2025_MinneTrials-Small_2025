package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;


public class TurnToAngle extends Command{
    Drive m_drive;
    double m_angle;
    double m_fwd;
    PIDController m_PID;
    ADIS16448_IMU m_gyro;
    

    public TurnToAngle(Drive drive, double fwd, double angle, PIDController PID, ADIS16448_IMU gyro){
        m_drive = drive;
        m_angle = angle;
        m_PID = PID;
        m_gyro = gyro;
        m_fwd = fwd;

        addRequirements(m_drive);
    }

    @Override
    public void execute(){
        m_drive.arcadeDrive(m_fwd, -m_PID.calculate(m_gyro.getAngle(), m_angle));
    }


}