package yiou.chen.gameObjects;

import yiou.chen.utils.BondUpdator;
import yiou.chen.utils.MathUtils;

public class Bond implements YObject {

	public static final String INFINITE_SPRING = "infinite_spring";
	public static final String SPRING = "spring";
	public static final String INFINITE_STRING = "infinite_string";
	public static final String BROKEN = "broken";

	private boolean inConstruction = true;
	private double mLength;
	private double mMaxLength;
	private double mStrength;
	private PlayerObject mCore;
	private PlayerObject mSatellite;
	private double satelliteEffect;// how much the movement of satellite can
									// affect the core (based on relative
									// weight)
	private String mLinkType;
	private BondUpdator updator;

	public static Bond startBuilder(PlayerObject core, PlayerObject satellite) {
		return new Bond(core, satellite);
	}

	private Bond(PlayerObject core, PlayerObject satellite) {
		inConstruction = true;
		if (core == null)
			throw new IllegalArgumentException(
					"Core PlayerObject cannot be null");
		if (satellite == null)
			throw new IllegalArgumentException(
					"Satellite PlayerObject cannot be null");
		if (core == satellite)
			throw new IllegalArgumentException("Self bond is not allowed");
		// if both are FreeObject, no bond can be applied to them
		if (core instanceof FreeObject && satellite instanceof FreeObject)
			throw new IllegalArgumentException(
					"bond cannot be applied to two FreeObject");
		mCore = core;
		mSatellite = satellite;
		mLength = MathUtils.getPlaneDistance(core.getPositionX(),
				core.getPositionY(), satellite.getPositionX(),
				satellite.getPositionY());
		mStrength = 1;
		mMaxLength = 2 * mLength;
		mLinkType = INFINITE_SPRING;
	}

	/**
	 * Set the length of the bond, the max length is by default twice the
	 * length.
	 * 
	 * @param leng
	 * @return
	 */
	public Bond length(double leng) {
		if (!inConstruction)
			return null;
		mLength = leng;
		mMaxLength = 2 * leng;
		return this;
	}

	/**
	 * Set the length and max length of the bond, if the maxLength is smaller
	 * than length, throw IllegalArgumentException
	 * 
	 * @param leng
	 * @param maxLength
	 * @return
	 */
	public Bond length(double leng, double maxLength) {
		if (!inConstruction)
			return null;
		mLength = leng;
		if (maxLength < leng)
			throw new IllegalArgumentException();
		else
			mMaxLength = maxLength;
		return this;
	}

	/**
	 * Initialize the type of the bond. Default type is INFINITE_SPRING. Type
	 * can be change later. For example, a bond can be marked as BROKEN to
	 * temporarily disable it's function.
	 * 
	 * @param type
	 * @return
	 */
	public Bond type(String type) {
		if (!inConstruction)
			return null;
		mLinkType = type;
		return this;
	}

	/**
	 * Initialize the strength of the bond, range from 0 to 1, any value greater
	 * than 1 will be taken as 1, any number smaller than 0 will be seen as 0.
	 * Strength means how hard it is to change the length of the bond. If
	 * strength is 1, it won't be change by any amount of force. If strength is
	 * 0, the bond can be seen as not existing.
	 * 
	 * @param strength
	 * @return
	 */
	public Bond strength(double strength) {
		if (!inConstruction)
			return null;
		if (strength > 1)
			strength = 1;
		if (strength < 0)
			strength = 0;
		mStrength = strength;
		return this;
	}

	/**
	 * Final step of building the bond.
	 * 
	 * @return
	 */
	public Bond build() {
		// calculate satellite effect
		if (mCore instanceof FreeObject)
			satelliteEffect = 0;
		else if (mSatellite instanceof FreeObject)
			satelliteEffect = 1;
		else {
			double cWeight = mCore.getWeight();
			double sWeight = mSatellite.getWeight();
			double sum = cWeight + sWeight;
			if (sum <= 0)
				satelliteEffect = 0.5;
			else
				satelliteEffect = sWeight / sum;
		}
		// applying the updator based on the LinkType
		setUpdator();
		onCreate();
		inConstruction = false;
		return this;
	}

	/**
	 * Can be override to add custom modification during initialization
	 */
	protected void onCreate() {

	}

	private void setUpdator() {
		switch (mLinkType) {
		case INFINITE_SPRING:
			updator = new BondUpdator.InfiniteSpringUpdator(this);
		case SPRING:
			updator = new BondUpdator.SpringUpdator(this);
		case INFINITE_STRING:
			updator = new BondUpdator.InfiniteStringUpdator(this);
		case BROKEN:
			updator = new BondUpdator.BrokenBondUpdator(this);
		default:
			updator = new BondUpdator.BrokenBondUpdator(this);
		}
	}

	public void applyAngularVelocity(double angle) {
		double x = mSatellite.getPositionX() - mCore.getPositionX();
		double y = mSatellite.getPositionY() - mCore.getPositionY();
		double r = Math.sqrt(x * x + y * y);
		double initAngle = Math.atan2(y, x);
		double newAngle = angle + initAngle;
		double vX = r * Math.cos(newAngle) - x;
		double vY = r * Math.sin(newAngle) - y;
		mSatellite.setVX(mSatellite.getVX() + vX);
		mSatellite.setVY(mSatellite.getVY() + vY);
	}

	public double getSatelliteEffect() {
		return satelliteEffect;
	}

	/**
	 * Update the bond
	 */
	public void update() {
		updator.update();
	}

	public double getLength() {
		return mLength;
	}

	public void setLength(double length) {
		mLength = length;
	}

	public double getMaxLength() {
		return mMaxLength;
	}

	public void setMaxLength(double maxLength) {
		mMaxLength = maxLength;
	}

	public double getStrength() {
		return mStrength;
	}

	public void setStrength(double strength) {
		mStrength = strength;
	}

	public PlayerObject getCore() {
		return mCore;
	}

	public void setCore(PlayerObject core) {
		mCore = core;
	}

	public PlayerObject getSatellite() {
		return mSatellite;
	}

	public void setSatellite(PlayerObject satellite) {
		mSatellite = satellite;
	}

	public String getLinkType() {
		return mLinkType;
	}

	public void setLinkType(String linkType) {
		mLinkType = linkType;
		setUpdator();
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

}
