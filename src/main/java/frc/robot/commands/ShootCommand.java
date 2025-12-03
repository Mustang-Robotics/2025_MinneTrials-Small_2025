package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class ShootCommand extends Command{
    Intake m_intake;
    double m_speed;
    public ShootCommand(Intake intake, double speed){
        m_intake = intake;
        m_speed = speed;

        addRequirements(m_intake);
    }

    @Override
    public void initialize(){
        m_intake.SetSpeed(m_speed);
    }
    
}