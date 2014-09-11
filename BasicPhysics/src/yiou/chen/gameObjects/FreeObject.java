package yiou.chen.gameObjects;

/**
 * This kind of Object has not weight nor inertia. It can be moved or given a
 * velocity, but the speed will not change unless manually setting it.
 * 
 * @author Yiou
 * 
 */
public class FreeObject extends PlayerObject {

	public static FreeObject startBuilder() {
		return new FreeObject();
	}

	private FreeObject() {
		mType = PlayerObject.FREE_OBJECT;
	}

	/**
	 * Initialize the position
	 * 
	 * @param x
	 * @param y
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	@Override
	public FreeObject pos(double x, double y) {

		return (FreeObject) super.pos(x, y);
	}

	/**
	 * Initialize the velocity
	 * 
	 * @param vx
	 * @param vy
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	@Override
	public FreeObject vel(double vx, double vy) {
		// TODO Auto-generated method stub
		return (FreeObject) super.vel(vx, vy);
	}

	

	/**
	 * Final step of assembling the PlayerObject.
	 * 
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	@Override
	public FreeObject build() {
		// TODO Auto-generated method stub
		return (FreeObject) super.build();
	}

	@Override
	public void moveX(double x) {
		setPositionX(getPositionX() + x);
	}

	@Override
	public void moveY(double y) {
		setPositionY(getPositionY() + y);
	}

	@Override
	public void moveToX(double x) {
		setPositionX(x);
	}

	@Override
	public void moveToY(double y) {
		setPositionY(y);
	}
}
