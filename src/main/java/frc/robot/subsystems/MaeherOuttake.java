// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
//import com.revrobotics.spark.SparkBase.PersistMode;
//import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MaeherOuttakeC;

public class MaeherOuttake extends SubsystemBase {
  private final SparkMax outakeMotor;
  private final SparkMaxConfig mainConfig;

  public MaeherOuttake() {
    outakeMotor = new SparkMax(MaeherOuttakeC.outTakeID, MotorType.kBrushless);

    mainConfig = new SparkMaxConfig();
    mainConfig
      .inverted(false)
      .smartCurrentLimit(MaeherOuttakeC.outakeSmartLimit)
      .secondaryCurrentLimit(MaeherOuttakeC.outtakeSecondLimit);

    
 //   outakeMotor.configure(mainConfig,  ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    //followMotor.configure(followConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  
  }

  public Command fastOutake(){
    return run(()-> {
    outakeMotor.set(MaeherOuttakeC.outakeFastSpeed);
    })
    .finallyDo(()->{
      outakeMotor.set(0);
    })
    .withName("Outake running");
  }
  public Command slowOutake(){
    return run(()-> {
    outakeMotor.set(MaeherOuttakeC.outakeSlowSpeed);
    })
    .finallyDo(()->{
      outakeMotor.set(0);
    })
    .withName("Slowing Outake");
  }

  public Command reverseOuttake(){
    return run(()-> {
    outakeMotor.set(-MaeherOuttakeC.outakeSlowSpeed);
    })
    .finallyDo(()->{
      outakeMotor.set(0);
    })
    .withName("Outake reverse");
  }

  public Command stopOuttake(){
    return runOnce(()-> {
    outakeMotor.set(0);
  })
    .withName("Outake Stopped");
  }
  
  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Outake Speed", outakeMotor.get());
  }
}
