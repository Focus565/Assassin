package com.project.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Player {
	private float x;
	private float y;
	private float width = 64, height = 64;
	private Rectangle character;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	public static int speed = 200;
	private TiledMapTileLayer collisionLayer;
	
	public Player(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
		character = new Rectangle();
		character.x = x;
		character.y = y;
		character.width = width;
		character.height = height;

	}
	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setPostion(float x, float y) {
		setX(x);
		setY(y);
	}

	public void move(int dir, float delta) {
		
		float oldx = getX(), oldy = getY(), tiledWidth = collisionLayer.getTileWidth(),
				tiledHeight = collisionLayer.getTileHeight();
		boolean collisionX = false, collisionY = false;
		
		switch (dir) {
		case DIRECTION_UP:
			y += speed * delta;
			// top left
			collisionY = collisionLayer
					.getCell((int) ((getX()) / tiledWidth), (int) ((getY() + getHeight()) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
			// top middle
			if (!collisionY)
				collisionY = collisionLayer
						.getCell((int) ((getX() + getWidth() / 2) / tiledWidth),
								(int) ((getY() + getHeight()) / tiledHeight))
						.getTile().getProperties().containsKey("blocked");
			// top right
			if (!collisionY)
				collisionY = collisionLayer
						.getCell((int) ((getX() + getWidth()) / tiledWidth),
								(int) ((getY() + getHeight()) / tiledHeight))
						.getTile().getProperties().containsKey("blocked");
			break;
		case DIRECTION_DOWN:
			y -= speed * delta;
			// bottom left
			collisionY = collisionLayer.getCell((int) ((getX()) / tiledWidth), (int) ((getY()) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
			// bottom middle
			if (!collisionY)
				collisionY = collisionLayer
						.getCell((int) ((getX() + getWidth() / 2) / tiledWidth), (int) ((getY()) / tiledHeight))
						.getTile().getProperties().containsKey("blocked");
			// bottom right
			if (!collisionY)
				collisionY = collisionLayer
						.getCell((int) ((getX() + getWidth()) / tiledWidth), (int) ((getY()) / tiledHeight)).getTile()
						.getProperties().containsKey("blocked");
			break;
		case DIRECTION_RIGHT:
			x += speed * delta;
			// top right
			collisionX = collisionLayer
					.getCell((int) ((getX() + getWidth()) / tiledWidth), (int) ((getY() + getHeight()) / tiledHeight))
					.getTile().getProperties().containsKey("blocked");
			// middle right
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) ((getX() + getWidth()) / tiledWidth),
								(int) ((getY() + getHeight() / 2) / tiledHeight))
						.getTile().getProperties().containsKey("blocked");
			// bottom right
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) ((getX() + getWidth()) / tiledWidth), (int) ((getY()) / tiledHeight)).getTile()
						.getProperties().containsKey("blocked");

			break;
		case DIRECTION_LEFT:
			x -= speed * delta;
			// top left
			collisionX = collisionLayer
					.getCell((int) (getX() / tiledWidth), (int) ((getY() + getHeight()) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
			// middle left
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) (getX() / tiledWidth), (int) ((getY() + getHeight() / 2) / tiledHeight))
						.getTile().getProperties().containsKey("blocked");
			// bottom left
			if (!collisionX)
				collisionX = collisionLayer.getCell((int) (getX() / tiledWidth), (int) ((getY()) / tiledHeight))
						.getTile().getProperties().containsKey("blocked");
			break;
		}
		if (collisionX) {
			setX(oldx);
		}
		if (collisionY) {
			setY(oldy);
		}
	}

}
