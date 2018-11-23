package com.project.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Map {
	TiledMap tiledmap;
	
	public Map() {
		tiledmap = new TmxMapLoader().load("map.tmx");
		
	}
}
