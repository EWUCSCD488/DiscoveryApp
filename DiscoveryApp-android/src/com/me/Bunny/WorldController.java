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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import drawingHandler.Coins;
import drawingHandler.Dinasour;
import drawingHandler.Dinasour.JUMP_STATE;
import drawingHandler.Rocks;
import drawingHandler.SpecialCoin;

public class WorldController implements GestureListener {
	private static final String TAG = WorldController.class.getName();

	public LevelLoader level;
	public CameraHelper cameraHelper;
	public int lives;
	public int score;

	private Rectangle r1 = new Rectangle();
	private Rectangle r2 = new Rectangle();

	public WorldController() {
		init();
	}

	public void init() {
		// initTestObjects();
		initLevel();
		cameraHelper = new CameraHelper();
		Gdx.input.setInputProcessor(new GestureDetector(this));
		lives = Constants.LIVES_START;

	}

	private void onCollisionHeadWithRock(Rocks rock) {
		Dinasour dinasour = level.dinasour;
		float heightDifference = Math.abs(dinasour.position.y
				- (rock.position.y + rock.bounds.height));
		if (heightDifference > 0.25f) {
			boolean hitLeftEdge = dinasour.position.x > (rock.position.x + rock.bounds.width / 2.0f);
			if (hitLeftEdge) {
				dinasour.position.x = rock.position.x + rock.bounds.width;
			} else {
				dinasour.position.x = rock.position.x - dinasour.bounds.width;
			}
			return;
		}
		switch (dinasour.jumpState) {
		case GROUNDED:
			break;
		case FALLING:
		case JUMP_FALLING:
			dinasour.position.y = rock.position.y + dinasour.bounds.height
					+ dinasour.origin.y;
			dinasour.jumpState = JUMP_STATE.GROUNDED;
			break;
		case JUMP_RISING:
			dinasour.position.y = rock.position.y + dinasour.bounds.height
					+ dinasour.origin.y;
			break;
		}
	}

	private void onCollisionWithCoin(Coins coin) {
		coin.collected = true;
		score += coin.getScore();
		Gdx.app.log(TAG, "Gold coin collected");
	}

	private void onCollisionWithSpecialCoin(SpecialCoin specialcoin) {
		specialcoin.collected = true;
		score += specialcoin.getScore();
		level.dinasour.setSpecialCoinPowerUp(true);
		Gdx.app.log(TAG, "Feather collected");
	}

	private void testCollisions() {
		r1.set(level.dinasour.position.x, level.dinasour.position.y,
				level.dinasour.bounds.width, level.dinasour.bounds.height);
		// Test collision: Bunny Head <-> Rocks
		for (Rocks rock : level.rocks) {
			r2.set(rock.position.x, rock.position.y, rock.bounds.width,
					rock.bounds.height);
			if (!r1.overlaps(r2))
				continue;
			onCollisionHeadWithRock(rock);
			// IMPORTANT: must do all collisions for valid
			// edge testing on rocks.
		}
		// Test collision: Bunny Head <-> Gold Coins
		for (Coins goldcoin : level.coins) {
			if (goldcoin.collected)
				continue;
			r2.set(goldcoin.position.x, goldcoin.position.y,
					goldcoin.bounds.width, goldcoin.bounds.height);
			if (!r1.overlaps(r2))
				continue;
			onCollisionWithCoin(goldcoin);
			break;
		}
		// Test collision: Bunny Head <-> Feathers
		for (SpecialCoin feather : level.specialCoin) {
			if (feather.collected)
				continue;
			r2.set(feather.position.x, feather.position.y,
					feather.bounds.width, feather.bounds.height);
			if (!r1.overlaps(r2))
				continue;
			onCollisionBunnyWithFeather(feather);
			break;
		}
	}

	private void onCollisionBunnyWithFeather(SpecialCoin feather) {
		// TODO Auto-generated method stub

	}

	public int getLives() {
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
		level.update(deltaTime);
		testCollisions();
		cameraHelper.update(deltaTime);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// Log.d(TAG, "current  at : "+ testSprites[selectedSprite].getX() +
		// " , "+ testSprites[selectedSprite].getY());
		// moveSelectedSprite( 0 , 5* Gdx.graphics.getDeltaTime() );
		// Log.d(TAG, "tap at : "+ x + " , "+ y);

		/*
		 * selectedSprite = (selectedSprite+1)% testSprites.length;
		 * if(cameraHelper.hasTarget()){
		 * cameraHelper.setTarget(testSprites[selectedSprite]); }
		 * cameraHelper.setTarget(cameraHelper.hasTarget() ? null :
		 * testSprites[selectedSprite]);
		 */
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
		// moveSelectedSprite( 0 , -5* Gdx.graphics.getDeltaTime() );
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
