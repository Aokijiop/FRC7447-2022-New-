// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Dumper;
import frc.robot.Constants;

public class DumperMove extends CommandBase {
  /** Creates a new DumperRaiseLower. */
  Dumper m_dumper;
  Timer timer;
  private boolean finish;

  public DumperMove(Dumper d) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_dumper = d;
    addRequirements(m_dumper);
    timer = new Timer();
    m_dumper.isUp();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    System.out.println("isUp: " + m_dumper.armIsUp());
    finish = false;
    System.out.println("initialized move");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // moves dumper up
    if(!m_dumper.armIsUp()) {
        if (timer.get() >= 1.5f) {
          m_dumper.isUp();
          finish = true;
        }
        else {
          m_dumper.moveArm(Constants.dumperUpSpeed);
          System.out.println("Moving upppp");
        }
    }
    //moves dumper down 
    else if(m_dumper.armIsUp()) {
        if (timer.get() >= 1.5f) {
          m_dumper.isDown();
          finish = true;
        }
        else {
          m_dumper.moveArm(Constants.dumperDownSpeed);
          System.out.println("moving downnnnnn!!");
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_dumper.stopArm();
    timer.stop();
    System.out.println("Finished!!");
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}

