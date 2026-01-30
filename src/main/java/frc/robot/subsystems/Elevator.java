// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  private final double speed;
  private final TalonFX MainElevatorMotor;
  private final TalonFX FollowerElevatorMotor;
  private TalonFXConfiguration config;
  


  private final MotionMagicVoltage elevatorPosition = new MotionMagicVoltage(0);
  public Elevator() {
    speed = Constants.ElevatorConstants.speed;
    MainElevatorMotor= new TalonFX(Constants.ElevatorConstants.MainElevatorMotorID);
    FollowerElevatorMotor = new TalonFX(Constants.ElevatorConstants.FollowerElevatorMotorID);
    Slot0Configs slot0 = new Slot0Configs();
    slot0.kP = 0.8;      
    slot0.kI = 0.0;
    slot0.kD = 0.0;

    slot0.kG = 0.25;     
    slot0.kV = 0.12;     
    slot0.kA = 0.01;     
    config.Slot0 = slot0;

    MotionMagicConfigs mm = new MotionMagicConfigs();
    mm.MotionMagicCruiseVelocity = 30;  
    mm.MotionMagicAcceleration = 60;
    mm.MotionMagicJerk = 0;             
        
    config.MotionMagic = mm;
    //double rotations = MainElevatorMotor.getPosition().getValueAsDouble();

    MainElevatorMotor.getConfigurator().apply(config);
    FollowerElevatorMotor.getConfigurator().apply(config);
    setDefaultCommand(holdInPlace());
  }
  
  public Command moveUp() {
    
    return run(
      ()->{MainElevatorMotor.set(speed);
      FollowerElevatorMotor.set(speed);
    }).withName("Elevator Up");
  }
  public Command moveDown() {
    return run(
      ()->{MainElevatorMotor.set(-speed);
      FollowerElevatorMotor.set(-speed);
    }).withName("Elevator Down");
  }
  public Command stop(){
    

    return run(()->{
      MainElevatorMotor.stopMotor();
    FollowerElevatorMotor.stopMotor();
  }).withName("Elevator Stop");
  }
  public Command holdInPlace(){
    elevatorPosition.Position = MainElevatorMotor.getPosition().getValueAsDouble();
    return run(()->{
      MainElevatorMotor.setControl(elevatorPosition);
      FollowerElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator held in place");

  }
  public Command goToFirstLevel(){
    elevatorPosition.Position = Constants.ElevatorConstants.firstLevelHeight;
    return run(()->{
      MainElevatorMotor.setControl(elevatorPosition);
      FollowerElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to first level");
  }
  public Command goToSecondLevel(){
    elevatorPosition.Position = Constants.ElevatorConstants.secondLevelHeight;
    return run(()->{
      MainElevatorMotor.setControl(elevatorPosition);
      FollowerElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to second level");
  }
  public Command goToThirdLevel(){
    elevatorPosition.Position = Constants.ElevatorConstants.thirdLevelHeight;
    return run(()->{
      MainElevatorMotor.setControl(elevatorPosition);
      FollowerElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to third level");

  }
  public Command goToFourthLevel(){
    elevatorPosition.Position = Constants.ElevatorConstants.fourthLevelHeight;
    return run(()->{
      MainElevatorMotor.setControl(elevatorPosition);
      FollowerElevatorMotor.setControl(elevatorPosition);
    }).withName("Elevator to fourth level");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
