package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class AutoDrive extends Command{
    Drive m_drive;
    DoubleSupplier m_x;
    DoubleSupplier m_y;
    public AutoDrive(Drive drive, DoubleSupplier x, DoubleSupplier y){
        m_drive = drive;
        m_x = x;
        m_y = y;

        addRequirements(m_drive);
    }

    @Override
    public void initialize(){
        m_drive.arcadeDriveCommand(
            m_y, m_x);        
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
