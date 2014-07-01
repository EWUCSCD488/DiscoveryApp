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

	// ADD SPRITES
	public Sprite[] testSprites;
	public int selectedSprite;
	public LevelLoader level;
	public CameraHelper cameraHelper;
	
	public WorldController() {
		init();
	}

	public void init() {
		//initTestObjects();
		initLevel();
		cameraHelper = new CameraHelper();
		Gdx.input.setInputProcessor(new GestureDetector(this));
		
	}

	private void initLevel() {
		level = new LevelLoader(Constants.LEVEL_01);
	}

	/*private void initTestObjects() {
		

		// CREATE EMPTY POT SIZED PIXMAP WITH 8 BIT RGBA PIXEL DATA
		int width = 32;
		int height = 32;
		//Pixmap pixmap = createProceduralPixmap(width, height); // need to know
																// what this is
																// ?
		// CREATE NEW TEXTURE FROM PIXMAP DATA
		//Texture texture = new Texture(pixmap);
		
		ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
		
		regions.add(Assets.instance.cloud.cloud1);
		regions.add(Assets.instance.cloud.cloud2);
		regions.add(Assets.instance.cloud.cloud3);
		
		regions.add(Assets.instance.coin.coin);
		
		regions.add(Assets.instance.dinasour.cuteDinasour);
		
		regions.add(Assets.instance.ground.edge);
		regions.add(Assets.instance.ground.middle);
		
		regions.add(Assets.instance.mountain.moutainLeft);
		regions.add(Assets.instance.mountain.moutainRight);
		
		regions.add(Assets.instance.sky.sky);
		
		// CREATE NEW ARRAY FOR 5 SPRITES
				testSprites = new Sprite[regions.size()];
		
		// create new sprites using the just created texture
		//for (int i = 0; i < testSprites.length; i++) {
		for(TextureRegion element : regions){
			Sprite spr = new Sprite(element);
			spr.setSize(1, 1);
			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);

			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-2.0f, 2.0f);
			spr.setPosition(randomX, randomY);
			// Put new sprite into array
			 testSprites[regions.indexOf(element)] = spr;
			 //Log.d(TAG, "location at : " + testSprites[i].getX()+  " , "+  testSprites[i].getY());
		}
		// Set first sprite as selected one
		 selectedSprite = 2;

	}*/

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
		//handleDebugInput(deltaTime);
		//updateTestObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}

	private void moveSelectedSprite(float x, float y){
		testSprites[selectedSprite].translate(x, y);
	}

	private void updateTestObjects(float deltaTime) {
		// Get current rotation from selected sprite
		float rotation = testSprites[selectedSprite].getRotation();
		// Rotate sprite by 90 degrees per second
		rotation += 90 * deltaTime;
		// Wrap around at 360 degrees
		rotation %= 360;
		// Set new rotation value to selected sprite
		testSprites[selectedSprite].setRotation(rotation);
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
