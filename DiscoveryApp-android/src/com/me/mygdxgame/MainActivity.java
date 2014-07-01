package com.me.mygdxgame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.me.Bunny.CannonBunnyMain;


public class MainActivity extends AndroidApplication {
	
	public static boolean drawDebugOutLine = true;
	public static boolean rebuiltAtlas = true;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       /* if(rebuiltAtlas){
        	Settings settings = new Settings();
        	settings.maxHeight = 1024;
        	settings.maxWidth = 1024;
        	settings.debug = drawDebugOutLine;
        	TexturePacker2.process(settings, "assets-raw/images", "../BunnyHop-android/assets/atlasimages","bunnyhop.pack");
        }*/
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        
        initialize(new CannonBunnyMain(), cfg);
    }
}