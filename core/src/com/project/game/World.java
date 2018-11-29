package com.project.game;

import java.security.Guard;

import javax.swing.text.Position;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.BlendedSteering;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.ai.steer.behaviors.LookWhereYouAreGoing;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.ai.steer.behaviors.ReachOrientation;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.steer.limiters.NullLimiter;
import com.badlogic.gdx.ai.steer.utils.rays.CentralRayWithWhiskersConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.ParallelSideRayConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.RayConfigurationBase;
import com.badlogic.gdx.ai.steer.utils.rays.SingleRayConfiguration;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class World {

	private Player player;
	TiledMap tiledMap;
	SteeringAgent test;
	SteeringAgent target;
	Arrive<Vector2> a;
	Wander<Vector2> wanderSB;
	RayConfigurationBase<Vector2>[] rayConfigurations;
	RaycastObstacleAvoidance<Vector2> raycastObstacleAvoidanceSB;
	LookWhereYouAreGoing<Vector2> b;
	ShapeRenderer shapeRenderer;
	Face<Vector2> face;
	ReachOrientation<Vector2> reachOrientation;
	public World(int level) {
		if (level == 1) {
			tiledMap = new TmxMapLoader().load("map.tmx");
			player = new Player((TiledMapTileLayer) tiledMap.getLayers().get(0));
			player.setPostion(20 * player.getCollisionLayer().getTileWidth(),
					18 * player.getCollisionLayer().getTileHeight());
			test = new SteeringAgent(100, (TiledMapTileLayer) tiledMap.getLayers().get(0));
			test.position.add(200, 200);
			target = new SteeringAgent(100);
			target.position.set(player.getX(), player.getY());
			a = new Arrive<Vector2>(test, target) //
					.setTimeToTarget(0.1f) //
					.setArrivalTolerance(0.001f) //
					.setDecelerationRadius(80);
			this.wanderSB = new Wander<Vector2>(test) //
					.setFaceEnabled(true) //
					.setAlignTolerance(0.001f) // Used by Face
					.setDecelerationRadius(5) // Used by Face
					.setTimeToTarget(0.1f) // Used by Face
					.setWanderOffset(90) //
					.setWanderOrientation(10) //
					.setWanderRadius(40) //
					.setWanderRate(MathUtils.PI2 * 4);
			reachOrientation = new ReachOrientation<Vector2>(test,target)//
					.setTimeToTarget(0.1f) //
					.setAlignTolerance(0.001f) //
					.setDecelerationRadius(MathUtils.degreesToRadians * 120);
			face= new Face<Vector2>(test, target) //
					.setTimeToTarget(0.1f) //
					.setAlignTolerance(0.001f) //
					.setDecelerationRadius(MathUtils.degreesToRadians * 120);
			@SuppressWarnings("unchecked")
			RayConfigurationBase<Vector2>[] localRayConfigurations = new RayConfigurationBase[] {
					new CentralRayWithWhiskersConfiguration<Vector2>(test, 100, 40, 35 * MathUtils.degreesToRadians) };
			rayConfigurations = localRayConfigurations;
			RaycastCollisionDetector<Vector2> raycastCollisionDetector;
			test.setBehavior(reachOrientation);
		}

	}

	public Player getPlayer() {
		return player;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public void update(float delta) {
		GdxAI.getTimepiece().update(delta);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			player.move(Player.DIRECTION_DOWN, delta);
		if (Gdx.input.isKeyPressed(Keys.UP))
			player.move(Player.DIRECTION_UP, delta);
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			player.move(Player.DIRECTION_LEFT, delta);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			player.move(Player.DIRECTION_RIGHT, delta);
		target.position.set(player.getX(), player.getY());
		test.update(delta);
		System.out.println(  " " + test.getOrientation());
	}
}
