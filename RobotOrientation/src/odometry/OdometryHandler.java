package odometry;

import java.awt.geom.Point2D;

/**
 *<ul><i><a><h1><li><strong><b>The calculate method should be called repeatedly, otherwise your robot's position
 * 	would not be recalculated, and therefore will be substantially inaccurate.
 * </b></strong></li></h1></a></i></ul>
 * 
 * a class which uses encoders to find the progress of a robot between discrete
 * times
 * 
 * @author noam mantin
 *
 */
public class OdometryHandler {

	private OdometryUnit odometryUnit;
	private RelativeDataSupplier yawDiff;
	private double x, y, baseYaw;

	/**
	 * creates a new {@link OdometryHandler} object, with given parameters
	 * @param odometryUnit the {@link OdometryUnit} containing your robot's data
	 * @param x the starting x coordinate of your robot
	 * @param y the starting y coordinate of your robot
	 * @param baseYaw the angle between the x axis and your robot's starting position
	 */
	public OdometryHandler(OdometryUnit odometryUnit, double x, double y, double baseYaw) {
		this.odometryUnit = odometryUnit;
		this.x = x;
		this.y = y;
		this.baseYaw = baseYaw;
		yawDiff = new RelativeDataSupplier(odometryUnit::getYaw);
	}

	/**
	 * <ul><i><a><h1><li><strong><b>This method should be called repeatedly, otherwise your robot's position
	 * 	would not be recalculated, and therefore will be substantially inaccurate.
	 * </b></strong></li></h1></a></i></ul>
	 * 
	 * Calculates and returns the displacement of the robot since the last
	 * check.</br>
	 * 
	 * for more information about this process, see pages 2910-2911 in the main
	 * article from the README file
	 * 
	 * @return the position rate vector of the robot (ΔX and ΔY), from the
	 *         last call to this method, in the navigation frame.
	 * 
	 */
	public Point2D calculate() {
		// getting the yaw angle of the robot in the end of the movement
		double yaw = Math.toRadians(odometryUnit.getYaw() + baseYaw);
		/*
		 * The robot's displacement's norm, i.e the length of the straight line
		 * starting from the previous center location of the robot and ending in
		 * the current center location. Signified in the article by the letter
		 * Δλ
		 */
		double centerDistance;

		/*
		 * Getting the distance each side of the robot has passed. Signified in
		 * the article by the letters a(l) and a(r)
		 */
		double rightDistance = odometryUnit.getRightDistance();
		double leftDistance = odometryUnit.getLeftDistance();

		/*
		 * The yaw difference .Signified by Δϕ in the article. (equation 22)
		 */
		double yawDifference = yawDiff.get();
		/*
		 * calculating the length of the arch the middle of the robot has
		 * passed. Signified in the article as ak (equation 21)
		 */
		double archDistance = (rightDistance + leftDistance) / 2;

		if (yawDifference == 0) {
			// the robot moved in a straight line
			centerDistance = Math.abs(rightDistance);
		} else {
			/*
			 * calculating the rotation radius of the robot's movement.
			 * Signified by r in the article. (equation 23).
			 */
			double rotationRadius = archDistance / yawDifference;
			/*
			 * using the cosine law to find the robot's displacement's norm
			 * (Δλ). (equation 24)
			 */
			centerDistance = MathUtils.cosineLaw(rotationRadius, rotationRadius, yawDifference);
		}

		// finding the argument of the displacement vector.
		double arg = yaw - 1 / 2.0 * yawDifference;

		// if turned to the opposite diretion
		if (archDistance < 0) {
			arg += Math.PI;
		}
		/*
		 * Building the cartesian displacement vector by an argument (arg) and a
		 * norm (Δλ).
		 * 
		 * Now, as we have the argument and the norm of the displacement vector
		 * (or in other words, the polar form of the vector), we can convert it
		 * to the Cartesian form (represented by its X and Y components). Doing
		 * so gives us the robot's position in the navigation system (equation
		 * 26)
		 */
		Point2D point = MathUtils.convertPolarToCartesian(centerDistance, -arg);
		x += point.getX();
		y += point.getY();
		return point;
	}
	
	/**
	 * 
	 * @return the robot's current x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * 
	 * @return the robot's current y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * 
	 * @param x the value to set the robot's x coordinate to
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @param y the value to set the robot's y coordinate to
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @param baseYaw the value to set the robot's angle to
	 */
	public void setBaseYaw(double baseYaw) {
		this.baseYaw = baseYaw;
	}
	
	/**
	 * 
	 * @return the robot's current angle
	 */
	public double getYaw() {
		return odometryUnit.getYaw() + baseYaw;
	}
	 /**
	  * 
	  * @param x the value to set the robot's x coordinate to
	  * @param y the value to set the robot's y coordinate to
	  * @param yaw the value to set the robot's angle to
	  */
	public void setPosition(double x, double y, double yaw) {
		setX(x);
		setY(y);
		setBaseYaw(yaw);
	}
}
