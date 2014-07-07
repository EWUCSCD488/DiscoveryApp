package com.me.Bunny;

import Atlas_handlers.Assets;
import android.content.Context;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {

	private OrthographicCamera camera;
	private OrthographicCamera cameraGUI;
	
	private SpriteBatch batch;
	private WorldController worldController;

	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}

	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(true); // filp y axis
		cameraGUI.update();
	}

	private void renderWorld (SpriteBatch batch) {
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		worldController.level.render(batch);
		batch.end();
		}
	
	public void render() {
		renderWorld(batch);
		renderGUI(batch);
	}

	private void renderGuiScore(SpriteBatch batch){
		float x =70;
		float y = 40;
		batch.draw(Assets.instance.coin.coin, x, y, 50, 50, 100, 100, 0.35f, 0.35f, 0);
		// DRAW FONTS HERE
		Assets.instance.fonts.defaultBig.draw(batch, ""+worldController.score, x+75, y+37);
	}
	
	private void renderGUIExtraLive(SpriteBatch batch){
		float x = cameraGUI.viewportWidth - Constants.LIVES_START* 30;
		float y = 40;
		
		for(int i = 0 ; i < Constants.LIVES_START ; i++){
			if(worldController.getLives() <= i )
				batch.setColor(0.5f, 0.5f, 0.5f, 0.5f);
			batch.draw(Assets.instance.dinasour.cuteDinasour, x+ i * 50, y, 50, 50, 120, 100, 0.35f, -0.35f, 0);
			batch.setColor(1, 1,1,1);
			
		}
	}
	
	private void renderGUI(SpriteBatch batch){
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		
		// draw collected gold coins icon + text
		// (anchored to top left edge)
		renderGuiScore(batch);
		
		// draw extra lives icon + text (anchored to top right edge)
		renderGUIExtraLive(batch);
		
		batch.end();
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
		
		cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		cameraGUI.viewportWidth = Constants.VIEWPORT_GUI_HEIGHT / (float) height* (float)width;
		cameraGUI.position.sub(cameraGUI.viewportWidth/2, cameraGUI.viewportHeight/2, 0);
		cameraGUI.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
