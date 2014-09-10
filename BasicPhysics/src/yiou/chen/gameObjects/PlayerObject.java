package yiou.chen.gameObjects;

/**
 * This class keeps track of the information related to the mass center of a
 * physics object. It's an abstract representation of a object when we only
 * consider it a point with mass in 2D plane. The dimension of it is not what
 * the visual object will be shown on the screen if use also implement a visual
 * representation. It's only for calculating the mass conveniently when relative
 * density is provided. Rotation is not implemented.
 * 
 * @author Yiou
 * 
 */
public abstract class PlayerObject implements YObject {
	
	public static final String FORCE_OBJECT="forceObject";
	public static final String FREE_OBJECT="freeObject";
	
	protected boolean inConstruction = true;
	protected String mType;
	
	

	protected double mPositionX;
	protected double mPositionY;
	protected double previousX;
	protected double previousY;
	protected double mWeight;
	

	

	private double tempVX;
	private double tempVY;

	

	protected PlayerObject() {
		inConstruction = true;
		mPositionX = 0;
		mPositionY = 0;
		tempVX = 0;
		tempVY = 0;
		mWeight = 0;

	}

	// /////----builder functions
	/**
	 * Initialize the position
	 * 
	 * @param x
	 * @param y
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	protected PlayerObject pos(double x, double y) {
		if (!inConstruction)
			return null;
		mPositionX = x;
		mPositionY = y;
		return this;
	}

	/**
	 * Initialize the velocity
	 * 
	 * @param vx
	 * @param vy
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	protected PlayerObject vel(double vx, double vy) {
		if (!inConstruction)
			return null;
		tempVX = vx;
		tempVY = vy;
		return this;
	}


	/**
	 * Override this method to initialize the weight
	 * 
	 * @param weight
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	protected PlayerObject weight(double weight) {
		if (!inConstruction)
			return null;
		return this;
	}

	/**
	 * Final step of assembling the PlayerObject.
	 * 
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	protected PlayerObject build() {
		if (!inConstruction)
			return null;
		previousX = mPositionX - tempVX;
		previousY = mPositionY - tempVY;
		onCreated();
		inConstruction = false;
		return this;
	}

	// /////

	/**
	 * this can be overridden by user to provide extra customization. /p The
	 * PlayerObject is still under construction, so builder functions such as
	 * pos, vel, dimension, etc could be used
	 */
	protected void onCreated() {
	}

	public void update() {
		double vX = getVX();
		previousX = mPositionX;
		mPositionX += vX;

		double vY = getVY();
		previousY = mPositionY;
		mPositionY += vY;
	}

	public void dispose() {

	}

	public String getType() {
		return mType;
	}

	public void setType(String type) {
		mType = type;
	}
	
	public double getPositionX() {
		return mPositionX;
	}

	public void setPositionX(double positionX) {
		double vX = getVX();
		mPositionX = positionX;
		previousX = mPositionX - vX;

	}

	public double getPositionY() {
		return mPositionY;
	}

	public void setPositionY(double positionY) {
		double vY = getVY();
		mPositionY = positionY;
		previousY = mPositionY - vY;
	}

	public double getVX() {
		return (mPositionX - previousX);
	}

	public void setVX(double vX) {
		previousX = mPositionX - vX;
	}

	public double getVY() {
		return (mPositionY - previousY);
	}

	public void setVY(double vY) {
		previousY = mPositionY - vY;
	}

	
	/**
	 * Override to return weight
	 * @return
	 */
	protected double getWeight() {
		return Double.NaN;
	}

	/**
	 * Override to set weight
	 * @param weight
	 */
	protected void setWeight(double weight) {
	}

	public void moveX(double x) {
		mPositionX += x;
	}

	public void moveY(double y) {
		mPositionY += y;
	}

	public void moveToX(double x) {
		mPositionX = x;
	}

	public void moveToY(double y) {
		mPositionY = y;
	}
}
