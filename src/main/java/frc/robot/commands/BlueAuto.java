package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public class BlueAuto extends SequentialCommandGroup {
    public BlueAuto(Drive drive, Intake intake){
        addCommands(
            new AutoDrive(drive, .5, 0).withTimeout(2.2),
            new AutoDrive(drive, 0,.6).withTimeout(.5),
            new AutoDrive(drive, 0,0).withTimeout(.5),
            new AutoDrive(drive, .5,.0).withTimeout(.7),
            new ParallelCommandGroup(new AutoDrive(drive, 0,.0),
                new ShootCommand(intake, .6))
        );
    }
}
