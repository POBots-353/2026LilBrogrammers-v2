// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  private final TalonFX MainElevatorMotor;
  private final TalonFX FollowerElevatorMotor;

  private final MotionMagicVoltage elevatorPosition = new MotionMagicVoltage(0);
  public Elevator() {
    MainElevatorMotor = new TalonFX(Constants.ElevatorConstants.MainElevatorMotorID);
    FollowerElevatorMotor = new TalonFX(Constants.ElevatorConstants.FollowerElevatorMotorID);
    //double rotations = MainElevatorMotor.getPosition().getValueAsDouble();
    final TalonFXConfiguration config;
    Slot0Configs slot0 = new Slot0Configs()
        .withKP(0.8)
        .withKI(0.0)
        .withKD(0.0)
        .withKG(0.25)
        .withKV(0.12)
        .withKA(0.01);

      MotionMagicConfigs mm = new MotionMagicConfigs() 
        .withMotionMagicCruiseVelocity(8)
        .withMotionMagicAcceleration(16)
        .withMotionMagicJerk(0);

      config = new TalonFXConfiguration();
      config.Slot0 = slot0;
      config.MotionMagic = mm;
    
  
  MainElevatorMotor.getConfigurator().apply(config);
   FollowerElevatorMotor.setControl(new Follower(MainElevatorMotor.getDeviceID(),MotorAlignmentValue.Aligned));

}    
  public Command moveUp() {
    
    return run(
      ()->{MainElevatorMotor.set(Constants.ElevatorConstants.speed);
    }).withName("Elevator Up");
  }
  public Command moveDown() {
    return run(
      ()->{MainElevatorMotor.set(Constants.ElevatorConstants.speed);
    }).withName("Elevator Down");
  }
  public Command stop(){
    

    return run(()->{
      MainElevatorMotor.stopMotor();
  }).withName("Elevator Stop");
  }
  public Command holdInPlace(){
    return run(()->{
      elevatorPosition.Position = MainElevatorMotor.getPosition().getValueAsDouble();

      MainElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator held in place");

  }
  public Command goToFirstLevel(){
    return run(()->{
      elevatorPosition.Position = Constants.ElevatorConstants.firstLevelHeight;

      MainElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to first level");
  }
  public Command goToSecondLevel(){
    return run(()->{
      

      MainElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to second level");
  }
  public Command goToThirdLevel(){
    return run(()->{
      elevatorPosition.Position = Constants.ElevatorConstants.thirdLevelHeight;

      MainElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to third level");

  }
  public Command goToFourthLevel(){
    return run(()->{
      elevatorPosition.Position = Constants.ElevatorConstants.fourthLevelHeight;

      MainElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to fourth level");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

