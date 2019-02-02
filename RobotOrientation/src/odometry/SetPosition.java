package odometry;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetPosition extends InstantCommand {
	private double x, y, yaw;
	private OdometryHandler handler;
	private Runnable resetSensors;
	/**
	 * 
	 * @param x the value to set the x co-ordinate to
	 * @param y the value to set the y co-ordinate to
	 * @param yaw the value to set the angle to
	 * @param resetSensors a {@link Runnable} that resets your robot's location
	 * @param handler the {@link OdometryHandler} that handles the robot
	 */
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
