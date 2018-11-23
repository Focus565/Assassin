package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class World {

	private Player player;
	TiledMap tiledMap;
	public World() {
		tiledMap = new TmxMapLoader().load("map.tmx");
		player = new Player((TiledMapTileLayer) tiledMap.getLayers().get(0));
		player.setPostion(20*player.getCollisionLayer().getTileWidth(), 18*player.getCollisionLayer().getTileHeight());
	}

	public Player getPlayer() {
		return player;
	}
	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			player.move(Player.DIRECTION_DOWN, delta);
		if (Gdx.input.isKeyPressed(Keys.UP))
			player.move(Player.DIRECTION_UP, delta);
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			player.move(Player.DIRECTION_LEFT, delta);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
 			player.move(Player.DIRECTION_RIGHT, delta);
	}
}
