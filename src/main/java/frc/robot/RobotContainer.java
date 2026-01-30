// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.AlgaeIntake;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Outtake;
import edu.wpi.first.wpilibj2.command.Command;
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
    private final Elevator elevator = new Elevator();

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
      private final AlgaeIntake intake = new AlgaeIntake();
      private final Outtake outtake = new Outtake();
      private final CommandXboxController controller = new CommandXboxController(Constants.algaeIntakeC.intakeID);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      // Outtake controls
      controller.leftTrigger().whileTrue(outtake.spinOuttake()).onFalse(outtake.stopOuttake());

      controller.leftTrigger().whileTrue(elevator.level1());
      controller.b().whileTrue(intake.runIntake()).onFalse(intake.stopIntake());
      controller.a().whileTrue(intake.slowIntake()).onFalse(intake.stopIntake());
      controller.x().onTrue(intake.stopIntake());
      controller.x().whileTrue(intake.outtake()).onFalse(intake.stopIntake());
      controller.y().whileTrue(elevator.moveUp()).onFalse(elevator.holdInPlace());
      controller.leftTrigger().whileTrue(elevator.moveUp()).onFalse(elevator.holdInPlace());
      controller.rightTrigger().whileTrue(elevator.moveDown()).onFalse(elevator.holdInPlace());
      controller.povUp().whileTrue(elevator.goToFirstLevel()).onFalse(elevator.holdInPlace());
      controller.povRight().whileTrue(elevator.goToSecondLevel()).onFalse(elevator.holdInPlace());
      controller.povLeft().whileTrue(elevator.goToThirdLevel()).onFalse(elevator.holdInPlace());
      controller.povDown().whileTrue(elevator.goToFourthLevel()).onFalse(elevator.holdInPlace());
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
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }
    
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
