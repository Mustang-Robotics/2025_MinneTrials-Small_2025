package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public class BlueAuto extends SequentialCommandGroup {


    public BlueAuto(Drive drive, Intake intake, ADIS16448_IMU m_gyro, PIDController m_PID){
        addCommands(
            new TurnToAngle(drive, .5, 0, m_PID, m_gyro).withTimeout(2.2),
            new TurnToAngle(drive,0,  -90, m_PID, m_gyro).withTimeout(2),
            new TurnToAngle(drive, .5, -90, m_PID, m_gyro).withTimeout(.7),
            new ParallelCommandGroup(new AutoDrive(drive, 0,.0),
                new ShootCommand(intake, .6))
        );
    }
}
