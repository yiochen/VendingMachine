package yiou.chen.solar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Solar extends ApplicationAdapter {
	
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
	
	@Override
	public void create () { 
		worldController=new WorldController();
		worldRenderer=new WorldRenderer(worldController);
		paused=false;
	}

	@Override
	public void render () {
		if (!paused){
			worldController.update(Gdx.graphics.getDeltaTime());

		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRenderer.render();
	
	}
	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}
	@Override
	public void dispose() {
		worldRenderer.dispose ();
	}
	@Override
	public void pause() {
		paused=true;
	}
	@Override
	public void resume() {
		paused=false;
	}
}
