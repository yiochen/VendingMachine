package yiou.chen.gameObjects;

public class ForceObject extends PlayerObject {

	
	
	public static ForceObject startBuilder() {
		return new ForceObject();
	}

	public ForceObject() {
		mType=PlayerObject.FORCE_OBJECT;
		inConstruction=true;
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
	public ForceObject pos(double x, double y) {
		
		return (ForceObject) super.pos(x, y);
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
	public ForceObject vel(double vx, double vy) {
		// TODO Auto-generated method stub
		return (ForceObject) super.vel(vx, vy);
	}
	
	/**
	 * Initialize the weight
	 * 
	 * @param weight
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	public ForceObject weight(double weight) {
		if (!inConstruction)
			return null;
		
		mWeight = weight;
		return this;
	}
	/**
	 * Final step of assembling the PlayerObject.
	 * 
	 * @return if the object is under construction, return the object; /p
	 *         otherwise return null, field won't be changed.
	 */
	@Override
	public ForceObject build() {
		// TODO Auto-generated method stub
		return (ForceObject)super.build();
	}
	@Override
	public double getWeight() {
		return mWeight;
	}

	@Override
	public void setWeight(double weight) {
		mWeight=weight;
	}
}
