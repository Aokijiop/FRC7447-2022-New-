// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveToDistance extends CommandBase {
  DriveTrain m_driveTrain;
  double driveSetpoint;
  double angleSetpoint;
  private boolean finish;
  /** Creates a new DriveToDistance. */


  public DriveToDistance(DriveTrain dt, double d) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = dt;
    driveSetpoint = d;
    angleSetpoint = 0;
    addRequirements(m_driveTrain);
  }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.resetDistance();
    finish = false;
    m_driveTrain.setDriveSetpoint(driveSetpoint);
    m_driveTrain.setTurnSetpoint(angleSetpoint);
    System.out.println("Drive to Distance initialized, set point set to " + driveSetpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.updateMovementMeasurement();
    m_driveTrain.updateTurnMeasurement();
    m_driveTrain.driveToDistance();
    System.out.println("It do be executing tho no cap");

    if (m_driveTrain.atDistanceSetpoint()) {
      finish = true;
      System.out.println("Setpoint Reached");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Command ending");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}