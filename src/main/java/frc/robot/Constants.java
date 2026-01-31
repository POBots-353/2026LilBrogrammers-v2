// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class ElevatorConstants {
    public static final int MainElevatorMotorID = 21;
    public static final int FollowerElevatorMotorID = 22;
    public static final double speed = 0.5;
    
    public static final double firstLevelHeight = 5.0;
    public static final double secondLevelHeight = 10.0;
    public static final double thirdLevelHeight = 15.0;
    public static final double fourthLevelHeight = 20.0;
  }
  public static class algaeIntakeC{
    public static final int intakeID = 1;
    public static final int laserID = 2;

    public static final int angle = 45;
    public static final int maxSpeed = 1;
    public static final double slowSpeed = .5;
    public static final double outTakeSpeed = -.5;
    public static final Slot0Configs config = new Slot0Configs()
    .withKA(5)
    .withKG(0)
    .withKS(.05)
    .withKV(.05)
    .withKP(5)
    .withKI(.001)
    .withKD(0);
    public static final MotionMagicConfigs motion = new MotionMagicConfigs()
  .withMotionMagicAcceleration(30)
  .withMotionMagicCruiseVelocity(30);
  }
  public static class OuttakeConstants {
    public static final int outtakeMotorID = 18;
    public static final double outtakeSpeed = 0.3;
  }

  public static class MaeherElevatorC{
     
    public static final int elevatorMotorID = 21;
    public static final int followerMotorID = 22;
    public static final int debounceID = 23;
    public static final int limitSwitchID = 24;

    public static final double firstPosition  = 5.0;
    public static final double secondPosition = 10.0;
    public static final double thirdPosition  = 15.0;
    public static final double fourthPosition = 25.0;

    public static final double upSpeed = .01;
    public static final double downSpeed = -.01;
    public static final Slot0Configs config = new Slot0Configs()
    .withKA(5)
    .withKG(.4)
    .withKS(.05)
    .withKV(.05)
    .withKP(5)
    .withKI(.001)
    .withKD(0);
    public static final MotionMagicConfigs motion = new MotionMagicConfigs()
  .withMotionMagicAcceleration(3)
  .withMotionMagicCruiseVelocity(3);
  }

    public static class intakeC{
    public static final int mainIntakeID = 16;
    public static final int followIntakeID = 40;
    
    public static final double intakeSpeed = .5;
    public static final double slowIntakeSpeed = .2;

    public static final int smartLimitIntake = 30;
    public static final int secondLimitIntake = 60;
  }

  public static class MaeherOuttakeC{
    public static final int outTakeID = 18;

    public static final double outakeSlowSpeed = .1;
    public static final double outakeFastSpeed = .4;

    public static final int outakeSmartLimit = 25;
    public static final int outtakeSecondLimit = 55;

  }
}
