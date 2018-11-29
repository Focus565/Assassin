package com.project.game;

import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CollisonDetector implements RaycastCollisionDetector<Vector2> {
	TiledMapTileLayer collisionLayer;
	
	public CollisonDetector(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	@Override
	public boolean collides(Ray<Vector2> ray) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean findCollision(Collision<Vector2> outputCollision, Ray<Vector2> inputRay) {
		// TODO Auto-generated method stub
		boolean collide = false;
		if(inputRay.start.epsilonEquals(inputRay.end, MathUtils.FLOAT_ROUNDING_ERROR)) {
			
		}
		return collide;
	}
}
