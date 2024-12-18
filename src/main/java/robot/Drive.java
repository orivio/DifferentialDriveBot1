package robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;
import java.util.List;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;


public class Drive extends SubsystemBase {
    private final CANSparkMax leftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
    private final CANSparkMax rightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);

    public Drive() {
        for (CANSparkMax spark : List.of(leftLeader, leftFollower, rightLeader, rightFollower)) {
            spark.restoreFactoryDefaults();
            spark.setIdleMode(IdleMode.kBrake);

        }
        rightFollower.follow(rightLeader);
        leftFollower.follow(leftLeader);

            leftLeader.setInverted(true);
      }

      private void drive(double leftSpeed, double rightSpeed) {
        leftLeader.set(leftSpeed);
        rightLeader.set(rightSpeed);
      }

      public Command drive(DoubleSupplier vLeft, DoubleSupplier vRight) {
        return run(() -> drive(vLeft.getAsDouble(), vRight.getAsDouble()));
      }



}
