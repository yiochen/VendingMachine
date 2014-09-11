package yiou.chen.solar;

import yiou.chen.gameObjects.ForceObject;
import yiou.chen.gameObjects.FreeObject;
import yiou.chen.gameObjects.PlayerObject;
import yiou.chen.solar.objects.SkyObject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class WorldController {

	private Array<SkyObject> planets;
	private SkyObject mSun;
	
	public SkyObject nextSpawn;
	
	
	public WorldController(){
		init();
	}
	private void init(){
		//Create sun;
		FreeObject sunData=FreeObject.startBuilder().pos(0, 0).build();
		
		mSun=new SkyObject(sunData, 10, new Color(0.5f, 0, 0, 1));
		
	
		
		planets=new Array<SkyObject>();
		
	}
	private void spawn(SkyObject core) {
		nextSpawn=null;
		float r=0;
		float deducting=0;
		if (core!=mSun){
			
		}

	}
	public void update(float deltaTime){
		mSun.update(deltaTime);
		
	}
	public Array<SkyObject> getPlanets() {
		return planets;
	}
	public SkyObject getSun() {
		return mSun;
	}
	public SkyObject getNextSpawn() {
		return nextSpawn;
	}
}
