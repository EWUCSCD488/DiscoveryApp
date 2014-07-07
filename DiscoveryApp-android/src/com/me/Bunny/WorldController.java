package com.me.Bunny;

import java.util.ArrayList;

import Atlas_handlers.Assets;
import Level.LevelLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class WorldController implements GestureListener{
	private static final String TAG = WorldController.class.getName();

	public LevelLoader level;
	public CameraHelper cameraHelper;
	public int lives;
	public int score;
	
	public WorldController() {
		init();
	}

	public void init() {
		//initTestObjects();
		initLevel();
		cameraHelper = new CameraHelper();
		Gdx.input.setInputProcessor(new GestureDetector(this));
		lives = Constants.LIVES_START;
		
	}
	
	public int getLives(){
		return lives;
	}

	private void initLevel() {
		level = new LevelLoader(Constants.LEVEL_01);
		score = 0;
	}

	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		// Fill square with red color at 50% opacity
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		// Draw a yellow-colored X shape on square
		pixmap.setColor(1, 1, 0, 1);

		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		// Draw a cyan-colored border around square
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}

	public void update(float deltaTime) {
		cameraHelper.update(deltaTime);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		//Log.d(TAG, "current  at : "+ testSprites[selectedSprite].getX() +  " , "+ testSprites[selectedSprite].getY());
		//moveSelectedSprite(  0  , 5* Gdx.graphics.getDeltaTime() );
		//Log.d(TAG, "tap at : "+ x +  " , "+ y);
		
		/*selectedSprite = (selectedSprite+1)% testSprites.length;
		if(cameraHelper.hasTarget()){
			cameraHelper.setTarget(testSprites[selectedSprite]);
		}
		cameraHelper.setTarget(cameraHelper.hasTarget() ? null :
			testSprites[selectedSprite]);*/
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		//moveSelectedSprite(  0  , -5* Gdx.graphics.getDeltaTime() );
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
