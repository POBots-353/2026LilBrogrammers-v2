// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
//import com.revrobotics.spark.SparkBase.PersistMode;
//import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.intakeC;

public class MaeherIntake extends SubsystemBase {
  private final SparkMax intakeMotor;
  private final SparkMax followMotor;
  private final SparkMaxConfig mainConfig;
  private final SparkMaxConfig followConfig;

  public MaeherIntake() {
    intakeMotor = new SparkMax(intakeC.mainIntakeID, MotorType.kBrushless);
    followMotor = new SparkMax(intakeC.followIntakeID, MotorType.kBrushless);

    mainConfig = new SparkMaxConfig();
    mainConfig
      .inverted(false)
      .smartCurrentLimit(intakeC.smartLimitIntake)
      .secondaryCurrentLimit(intakeC.secondLimitIntake);

    followConfig = new SparkMaxConfig();
    followConfig
      .inverted(true)// intake up/down wheel diagram, from actual code
      .smartCurrentLimit(intakeC.smartLimitIntake) //If a motor gets above x amount of amps it will slow
      .secondaryCurrentLimit(intakeC.secondLimitIntake); //If a motor gets above even more amps it will hard stop for a couple of millisecond
      
   // intakeMotor.configure(mainConfig,  ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    //followMotor.configure(followConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  
  }

  public Command fastIntake(){
    return run(()-> {
    intakeMotor.set(intakeC.intakeSpeed);
    followMotor.set(intakeC.intakeSpeed);
    })
    .finallyDo(()->{
      intakeMotor.set(0);
      followMotor.set(0);
    })
    .withName("Intake running");
  }
  public Command slowIntake(){
    return run(()-> {
    intakeMotor.set(intakeC.slowIntakeSpeed);
    followMotor.set(intakeC.slowIntakeSpeed);
    })
    .finallyDo(()->{
      intakeMotor.set(0);
      followMotor.set(0);
    })
    .withName("Slowing Intake");
  }

  public Command reverseIntake(){
    return run(()-> {
    intakeMotor.set(-intakeC.slowIntakeSpeed);
    followMotor.set(-intakeC.slowIntakeSpeed);
    })
    .finallyDo(()->{
      intakeMotor.set(0);
      followMotor.set(0);
    })
    .withName("Intake reverse");
  }

  public Command stopIntake(){
    return runOnce(()-> {
    intakeMotor.set(0);
    followMotor.set(0);
  })
   // .until(null)
    .withName("Intake running");
  }
  
  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Indexer/Speed", intakeMotor.get());
  }
}
