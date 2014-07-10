package Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import drawingHandler.AbstractGameObject;
import drawingHandler.Clouds;
import drawingHandler.Coins;
import drawingHandler.Dinasour;
import drawingHandler.Leather;
import drawingHandler.Mountains;
import drawingHandler.Rocks;
import drawingHandler.SpecialCoin;

public class LevelLoader {
	public static final String TAG = LevelLoader.class.getName();

	public enum BLOCK_TYPE {
		EMPTY(0, 0, 0), // black
		ROCK(0, 255, 0), // green
		PLAYER_SPAWMPOINT(255, 255, 255), // white
		ITEM_GOLD_COIN(255, 255, 0), // yellow
		ITEM_SPECIAL_COIN(255, 0, 255); // purple

		private int color;

		private BLOCK_TYPE(int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}

		public boolean sameColor(int color) {
			return this.color == color;
		}

		public int getColor() {
			return color;
		}

	}// enum

	public Array<Rocks> rocks;
	public Mountains mountains;
	public Clouds clouds;
	public Dinasour dinasour;
	public Array<Coins> coins;
	public Array<SpecialCoin> specialCoin;

	public LevelLoader(String filename) {
		init(filename);
	}

	public void init(String filename){
		
		dinasour = null;
		coins = new Array<Coins>();
		specialCoin = new Array<SpecialCoin>();
		
		rocks = new Array<Rocks>();
		// load image file that represenets the level data
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		//scan pixels from top left to bottom right
		int lastPixel = -1;
		for(int pixelY = 0 ; pixelY < pixmap.getHeight() ; pixelY++){
			for(int pixelX = 0 ; pixelX < pixmap.getWidth() ; pixelX++){
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				// height grows from bottom to top
				float baseHeight = pixmap.getHeight() - pixelY;
				//get color of current pixel as 32 bit RGBA value
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				//find matching color value to identify block type at (x,y)
				// point and create the correcsponding game object if there is a match
				
				//emptyspace
				if(BLOCK_TYPE.EMPTY.sameColor(currentPixel)){
					//do nothing
				}
				else if (BLOCK_TYPE.ROCK.sameColor(currentPixel)){
					if(lastPixel != currentPixel){
						obj = new Rocks();
						float heightIncreaseFactor = 0.25f;
						offsetHeight = -2.5f;
						obj.position.set(pixelX, baseHeight*obj.dimension.y
														* heightIncreaseFactor
														+offsetHeight);
						rocks.add((Rocks)obj);
					}else{
						rocks.get(rocks.size - 1).increaseLenth(1);
					}
				} else if(BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel)){			// gold coin
					obj = new Coins();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y
					+ offsetHeight);
					coins.add((Coins)obj);
				}	else if (BLOCK_TYPE.PLAYER_SPAWMPOINT.sameColor(currentPixel)){				// dinasour
							obj = new Dinasour();
							offsetHeight = -3.0f;
							obj.position.set(pixelX	, baseHeight * obj.dimension.y + offsetHeight);
							dinasour = (Dinasour) obj;
					
				}else if (BLOCK_TYPE.ITEM_SPECIAL_COIN.sameColor(currentPixel))	{		// special coin
					obj = new SpecialCoin();
					offsetHeight = -1.5f;
					obj.position.set(pixelX,baseHeight * obj.dimension.y
					+ offsetHeight);
					specialCoin.add((SpecialCoin)obj);
				}else {
					int r = 0xff & (currentPixel >>> 24); //red color channel
					int g = 0xff & (currentPixel >>> 16); //green color channel
					int b = 0xff & (currentPixel >>> 8); //blue color channel
					int a = 0xff & currentPixel; //alpha channel
					Gdx.app.error(TAG, "Unknown object at x<" + pixelX
					+ "> y<" + pixelY
					+ ">: r<" + r
					+ "> g<" + g
					+ "> b<" + b
					+ "> a<" + a + ">");
					}
				
				lastPixel = currentPixel;
			} // second for loop
			
		}// first for loop
		
		// decoration
		clouds = new Clouds(pixmap.getWidth());
		clouds.position.set(0, 2);
		mountains = new Mountains(pixmap.getWidth());
		mountains.position.set(-1, -1);
				
		// free memory
		pixmap.dispose();
		Gdx.app.debug(TAG, "level '" + filename + "' loaded");
		
	}

	public void render(SpriteBatch batch) {
		// Draw Mountains
		mountains.render(batch);
		// Draw Rocks
		for (Rocks rock : rocks)
			rock.render(batch);
		
		// Draw gold coins
		for (Coins coin : coins)
			coin.render(batch);
		
		// Draw special Coins
		for (SpecialCoin coin : specialCoin)
			coin.render(batch);
		
		// Draw Player character
		dinasour.render(batch);
		
		// Draw Clouds
		clouds.render(batch);
	}
	
	public void update (float deltaTime) {
		
		for(Rocks rock : rocks)
		rock.update(deltaTime);
		for(Coins goldCoin : coins)
		goldCoin.update(deltaTime);
		for(SpecialCoin feather : specialCoin)
		feather.update(deltaTime);
		clouds.update(deltaTime);
		dinasour.update(deltaTime);
		}
	
}
