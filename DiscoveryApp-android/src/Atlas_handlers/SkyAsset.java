package Atlas_handlers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.me.Bunny.Constants;

public class SkyAsset {
	public final AtlasRegion sky;
	
	public SkyAsset(TextureAtlas atlas){
		sky = atlas.findRegion(Constants.SKY);
	}
}
