package Atlas_handlers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.me.Bunny.Constants;

public class SpecialCoinAsset {
public final AtlasRegion Specialcoin;
	
	public SpecialCoinAsset(TextureAtlas atlas){
		Specialcoin = atlas.findRegion(Constants.COIN_NAME);
	}
}
