package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

public class BlueAuto extends SequentialCommandGroup {
    public BlueAuto(Drive drive, Intake intake){
        addCommands(
            new AutoDrive(drive, () -> 0, () -> 0.5),
            new WaitCommand(3),
            new AutoDrive(drive, () -> 0.5, () -> 0)

        );
    }
}
