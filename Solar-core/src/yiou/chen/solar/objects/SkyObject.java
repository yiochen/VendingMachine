package yiou.chen.solar.objects;

import yiou.chen.gameObjects.PlayerObject;
import yiou.chen.solar.util.Constants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SkyObject {
	
	// The radius of the planet in pixels
	private static final int RADIUS = 100;
	private static final int INNER_RADIUS=80;

	// The radius of the actual displaying planet in relation to the dimension
	// of the Viewport
	private float mRadius;
	private PlayerObject mData;
	private Sprite mSprite;
	
	private Color mColor;

	private float timePassed;
	/**
	 * Create a circle shape Pixmap
	 * 
	 * @param color
	 * @return
	 */
	private Pixmap makePixmap() {
		Pixmap pixmap = new Pixmap(RADIUS * 2, RADIUS * 2, Format.RGBA8888);
		pixmap.setColor(mColor);
		pixmap.fillCircle(RADIUS, RADIUS, RADIUS);
		pixmap.fillCircle(RADIUS, RADIUS, INNER_RADIUS);
		return pixmap;
	}

	public float getRadius() {
		return mRadius;
	}

	public PlayerObject getData() {
		return mData;
	}

	public Sprite getSprite() {
		return mSprite;
	}

	public Color getColor() {
		return mColor;
	}

	public SkyObject(PlayerObject playerObject, float radius,Color color) {
		mData = playerObject;
		mColor = color;
		mSprite=new Sprite(new Texture(makePixmap()));
		mSprite.setSize(radius*2, radius*2);
		mSprite.setOrigin(radius, radius);
		mSprite.setPosition(0, 0);
		timePassed=0;
	}
	public void update(float deltaTime){
		timePassed+=deltaTime;
		while (timePassed>Constants.UPDATE_INTERVAL){
			timePassed-=Constants.UPDATE_INTERVAL;
			mData.update();
		}
		mSprite.setPosition((float)(mData.getPositionX()), (float)(mData.getPositionY()));		
	}

}
