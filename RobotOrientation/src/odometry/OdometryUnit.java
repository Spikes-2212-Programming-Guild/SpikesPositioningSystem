package odometry;

import java.util.function.Supplier;

public class OdometryUnit {
	/**
	 * the {@link Supplier} of the difference between the encoders of both
	 * sides' values
	 * 
	 * @see RelativeDataSupplier
	 */
	private RelativeDataSupplier leftDistanceSupplier;
	private RelativeDataSupplier rightDistanceSupplier;
	// the width of the robot
	private final double robotWidth;

	private Supplier<Double> yawSupplier;
	/**
	 *
	 * @param leftEncoder a {@link Supplier} that supplies the current position of the left side of the robot
	 * @param rightEncoder a {@link Supplier} that supplies the current position of the right side of the robot
	 * @param robotWidth the width of the robot
	 * @param yawSupplier a {@link Supplier} that supplies the current angle of the robot
	 */
	public OdometryUnit(Supplier<Double> leftEncoder, Supplier<Double> rightEncoder, double robotWidth,
			Supplier<Double> yawSupplier) {
		// creating the encoders' data handlers using the suppliers
		this.leftDistanceSupplier = new RelativeDataSupplier(leftEncoder);
		this.rightDistanceSupplier = new RelativeDataSupplier(rightEncoder);
		this.robotWidth = robotWidth;
		this.yawSupplier = yawSupplier;
	}
	
	/**
	 * 
	 * @return the distance the left side of the robot has passed
	 */
	public double getLeftDistance() {
		return leftDistanceSupplier.get();
	}
	
	/**
	 * 
	 * @return the distance the right side of the robot has passed
	 */
	public double getRightDistance() {
		return rightDistanceSupplier.get();
	}
	
	/**
	 * 
	 * @return the width of the robot
	 */
	public double getRobotWidth() {
		return robotWidth;
	}
	
	/**
	 * 
	 * @return the current angle of the robot
	 */
	public double getYaw() {
		return yawSupplier.get();
	}
}
