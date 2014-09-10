package yiou.chen.utils;

import yiou.chen.gameObjects.Bond;
import yiou.chen.gameObjects.PlayerObject;
import yiou.chen.gameObjects.YUpdator;

public abstract class BondUpdator implements YUpdator {
	protected Bond mBond;

	public BondUpdator(Bond bond) {
		mBond = bond;
	}

	protected double getDis() {
		PlayerObject core = mBond.getCore();
		PlayerObject satellite = mBond.getSatellite();
		return MathUtils.getPlaneDistance(core.getPositionX(),
				core.getPositionY(), satellite.getPositionX(),
				satellite.getPositionY());
	}

	protected double getDisX() {
		PlayerObject core = mBond.getCore();
		PlayerObject satellite = mBond.getSatellite();
		return satellite.getPositionX() - core.getPositionX();
	}

	protected double getDisY() {
		PlayerObject core = mBond.getCore();
		PlayerObject satellite = mBond.getSatellite();
		return satellite.getPositionY() - core.getPositionY();
	}

	public abstract void update();

	public static class InfiniteSpringUpdator extends BondUpdator {

		public InfiniteSpringUpdator(Bond bond) {
			super(bond);
		}

		@Override
		public void update() {
			double dis = getDis();

			double leng = mBond.getLength();
			double dRatio = (dis - leng) / dis * mBond.getStrength();
			double diffX = getDisX() * dRatio;
			double diffY = getDisY() * dRatio;

			double translateCoreX = diffX * mBond.getSatelliteEffect();
			double translateCoreY = diffY * mBond.getSatelliteEffect();
			double translateSatelliteX = translateCoreX - diffX;
			double translateSatelliteY = translateCoreY - diffY;
			mBond.getCore().moveX(translateCoreX);
			mBond.getCore().moveY(translateCoreY);
			mBond.getSatellite().moveX(translateSatelliteX);
			mBond.getSatellite().moveY(translateSatelliteY);
		}

	}

	public static class SpringUpdator extends InfiniteSpringUpdator {

		public SpringUpdator(Bond bond) {
			super(bond);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void update() {
			double dis = getDis();
			double leng = mBond.getLength();
			if (dis > leng) {
				mBond.setLinkType(Bond.BROKEN);
				return;
			} else {
				super.update();
			}
		}
	}

	public static class InfiniteStringUpdator extends InfiniteSpringUpdator {

		public InfiniteStringUpdator(Bond bond) {
			super(bond);
		}

		@Override
		public void update() {
			double dis = getDis();
			if (dis > mBond.getLength()) {
				super.update();
			}
		}

	}
	public static class BrokenBondUpdator extends BondUpdator{

		
		public BrokenBondUpdator(Bond bond) {
			super(bond);
		}

		@Override
		public void update() {

		}
		
	}
}
