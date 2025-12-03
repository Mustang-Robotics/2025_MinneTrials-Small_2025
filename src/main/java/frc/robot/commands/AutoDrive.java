package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class AutoDrive extends Command{
    Drive m_drive;
    double m_x;
    double m_y;
    public AutoDrive(Drive drive, double fwd, double rot){
        m_drive = drive;
        m_x = rot;
        m_y = fwd;

        addRequirements(m_drive);
    }

    @Override
    public void execute(){
        m_drive.arcadeDrive(
            m_y, m_x);        
    }
    
}
