package odometry;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetPosition extends InstantCommand {
	private double x, y, yaw;
	private OdometryHandler handler;
	private Runnable resetSensors;

	public SetPosition(double x, double y, double yaw, Runnable resetSensors, OdometryHandler handler) {
		this.x = x;
		this.y = y;
		this.yaw = yaw;
		this.resetSensors = resetSensors;
		this.handler = handler;
	}

	@Override
	protected synchronized void initialize() {
		resetSensors.run();
		handler.setPosition(x, y, yaw);
	}
}
