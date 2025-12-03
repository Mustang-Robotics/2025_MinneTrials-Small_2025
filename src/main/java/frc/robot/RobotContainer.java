// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.BlueAuto;
import frc.robot.commands.RedAuto;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private SendableChooser<Command> m_auto = new SendableChooser<>();
  private final Drive m_drive = new Drive();
  private final Intake m_intake = new Intake();
  PIDController m_PID = new PIDController(0.1, 0, 0);

  private final double speedVariable = 0.85;
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    m_auto.setDefaultOption("Blue Auto",new BlueAuto(m_drive, m_intake, m_drive.m_gyro, m_PID).withTimeout(7).andThen(new ShootCommand(m_intake, 0)));
    m_auto.addOption("Red Auto",new RedAuto(m_drive, m_intake, m_drive.m_gyro, m_PID).withTimeout(7).andThen(new ShootCommand(m_intake, 0)));
    SmartDashboard.putData("Auto Chooser", m_auto);
    configureBindings();
    
  }
  
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition)
        //.onTrue(new ExampleCommand(m_exampleSubsystem));

    
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_driverController.leftTrigger().whileTrue(new StartEndCommand(() -> m_intake.SetSpeed(0.4), () -> m_intake.SetSpeed(0), m_intake));
    m_driverController.rightTrigger().whileTrue(new StartEndCommand(() -> m_intake.SetSpeed(0.6), () -> m_intake.SetSpeed(0), m_intake));
    m_drive.setDefaultCommand(
        m_drive.arcadeDriveCommand(
            () -> m_driverController.getLeftY()*speedVariable, () -> -m_driverController.getRightX()*speedVariable));
    m_driverController.leftBumper().whileTrue(new StartEndCommand(() -> m_intake.SetSpeed(-0.4), () -> m_intake.SetSpeed(0), m_intake));

    
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_auto.getSelected();
  }
  
}

