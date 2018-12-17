package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

public class World {

	private Player player;
	TiledMap tiledMap;

	Animator animate = new Animator(0);
	Wander<Vector2> wander;
	Arrive<Vector2> arriveSB;
	Bot bot1, bot2, bot3;
	public World(int level) {
		if (level == 1) {
			tiledMap = new TmxMapLoader().load("map1.tmx");
			player = new Player((TiledMapTileLayer) tiledMap.getLayers().get(0));
			player.setPostion(1.3f * player.getCollisionLayer().getTileWidth(),
					3.5f * player.getCollisionLayer().getTileHeight());
			bot1 = new Bot(300,400,player,(TiledMapTileLayer) tiledMap.getLayers().get(0));
			bot2 = new Bot(800,100,player,(TiledMapTileLayer) tiledMap.getLayers().get(0));
			bot3 = new Bot(700,700,player,(TiledMapTileLayer) tiledMap.getLayers().get(0));
		}

		if (level == 2) {
			tiledMap = new TmxMapLoader().load("map2.tmx");
			player = new Player((TiledMapTileLayer) tiledMap.getLayers().get(0));
			player.setPostion(2.1f * player.getCollisionLayer().getTileWidth(),
					28 * player.getCollisionLayer().getTileHeight());
		}

	}

	public Player getPlayer() {
		return player;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public Animator getAnimator() {
		return animate;
	}

	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			animate = new Animator(0);
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.move(Player.DIRECTION_DOWN, delta);
			getAnimator().stateTime += Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			animate = new Animator(3);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.move(Player.DIRECTION_UP, delta);
			getAnimator().stateTime += Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			animate = new Animator(1);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.move(Player.DIRECTION_LEFT, delta);
			getAnimator().stateTime += Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			animate = new Animator(2);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.move(Player.DIRECTION_RIGHT, delta);
			getAnimator().stateTime += Gdx.graphics.getDeltaTime();
		}
		System.out.println(player.getX()+" "+player.getY());
		bot1.tick();
		bot2.tick();
		bot3.tick();
	}
}
