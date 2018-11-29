package com.project.game;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SteeringAgent implements Steerable<Vector2> {

	private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(
			new Vector2());

	Vector2 position = new Vector2();
	float orientation;
	Vector2 linearVelocity;
	float angularVelocity;
	boolean independentFacing;
	SteeringBehavior<Vector2> steeringBehavior;
	float maxLinearSpeed = 100;
	float maxLinearAcceleration = 200;
	float maxAngularSpeed = 5;
	float maxAngularAcceleration = 10;
	float boundingRadius;
	boolean tagged;
	private float width = 32, height = 32;
	TiledMapTileLayer collisionLayer;
	float oldx, oldy;
	public Texture pic = new Texture("human.png");

	public SteeringAgent(float boudingRadius, TiledMapTileLayer collision) {
		// TODO Auto-generated constructor stub
		this.collisionLayer = collision;
		this.position = new Vector2();
		this.linearVelocity = new Vector2();
		this.boundingRadius = boudingRadius;
		this.tagged = true;
	}

	public SteeringAgent(float boudingRadius) {
		// TODO Auto-generated constructor stub
		this.position = new Vector2();
		this.linearVelocity = new Vector2();
		this.boundingRadius = boudingRadius;
		this.tagged = false;
	}

	public void setBehavior(SteeringBehavior<Vector2> behavior) {
		this.steeringBehavior = behavior;
	}

	public SteeringBehavior<Vector2> getBehavior() {
		return steeringBehavior;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public static float calculateOrientationFromLinearVelocity(Steerable<Vector2> character) {
		// If we haven't got any velocity, then we can do nothing.
		if (character.getLinearVelocity().isZero(character.getZeroLinearSpeedThreshold()))
			return character.getOrientation();

		return character.vectorToAngle(character.getLinearVelocity());
	}

	public void update(float delta) {
		if (steeringBehavior != null) {
			// Calculate steering acceleration
			collision();
			steeringBehavior.calculateSteering(steeringOutput);
			applySteering(steeringOutput, delta);
		}
		oldx = getPosition().x;
		oldy = getPosition().y;
	}

	private void applySteering(SteeringAcceleration<Vector2> steering, float time) {
		// Update position and linear velocity. Velocity is trimmed to maximum speed
		this.position.mulAdd(linearVelocity, time);
		this.linearVelocity.mulAdd(steering.linear, time).limit(this.getMaxLinearSpeed());

		// Update orientation and angular velocity
		if (independentFacing) {
			this.orientation += angularVelocity * time;
			this.angularVelocity += steering.angular * time;
		} else {
			// For non-independent facing we have to align orientation to linear velocity
			float newOrientation = calculateOrientationFromLinearVelocity(this);
			if (newOrientation != this.orientation) {
				this.angularVelocity = (newOrientation - this.orientation) * time;
				this.orientation = newOrientation;
			}
		}
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return (float) Math.atan2(-vector.x, vector.y);
	}

	// Actual implementation depends on your coordinate system.
	// Here we assume the y-axis is pointing upwards.
	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x = -(float) Math.sin(angle);
		outVector.y = (float) Math.cos(angle);
		return outVector;
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return orientation* MathUtils.degreesToRadians;
	}

	@Override
	public void setOrientation(float orientation) {
		// TODO Auto-generated method stub
		this.orientation = orientation* MathUtils.degreesToRadians;

	}

	@Override
	public Location<Vector2> newLocation() {
		// TODO Auto-generated method stub
		return new Scene2dLocation();
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0.001f;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public float getMaxLinearSpeed() {
		// TODO Auto-generated method stub
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		// TODO Auto-generated method stub
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		// TODO Auto-generated method stub
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		// TODO Auto-generated method stub
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		// TODO Auto-generated method stub
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		// TODO Auto-generated method stub
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		// TODO Auto-generated method stub
		this.maxAngularAcceleration = maxAngularAcceleration;

	}

	@Override
	public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return linearVelocity;
	}

	@Override
	public float getAngularVelocity() {
		// TODO Auto-generated method stub
		return angularVelocity;
	}

	@Override
	public float getBoundingRadius() {
		// TODO Auto-generated method stub
		return boundingRadius;
	}

	@Override
	public boolean isTagged() {
		// TODO Auto-generated method stub
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		// TODO Auto-generated method stub
		this.tagged = tagged;
	}

	public boolean collision() {
		
		float tiledWidth = collisionLayer.getTileWidth(), tiledHeight = collisionLayer.getTileHeight();
		boolean collisionX = false, collisionY = false, ans=false;
		// top left
		collisionY = collisionLayer
				.getCell((int) ((getPosition().x) / tiledWidth), (int) ((getPosition().y + getHeight()) / tiledHeight))
				.getTile().getProperties().containsKey("blocked");
		// top middle
		if (!collisionY)
			collisionY = collisionLayer
					.getCell((int) ((getPosition().x + getWidth() / 2) / tiledWidth),
							(int) ((getPosition().y + getHeight()) / tiledHeight))
					.getTile().getProperties().containsKey("blocked");
		// top right
		if (!collisionY)
			collisionY = collisionLayer
					.getCell((int) ((getPosition().x + getWidth()) / tiledWidth),
							(int) ((getPosition().y + getHeight()) / tiledHeight))
					.getTile().getProperties().containsKey("blocked");
		// bottom left
		if (!collisionY)
			collisionY = collisionLayer.getCell((int) ((getPosition().x) / tiledWidth), (int) ((getPosition().y) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
		// bottom middle
		if (!collisionY)
			collisionY = collisionLayer
					.getCell((int) ((getPosition().x + getWidth() / 2) / tiledWidth), (int) ((getPosition().y) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
		// bottom right
		if (!collisionY)
			collisionY = collisionLayer
					.getCell((int) ((getPosition().x + getWidth()) / tiledWidth), (int) ((getPosition().y) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
		// top right
		collisionX = collisionLayer
				.getCell((int) ((getPosition().x + getWidth()) / tiledWidth),
						(int) ((getPosition().y + getHeight()) / tiledHeight))
				.getTile().getProperties().containsKey("blocked");
		// middle right
		if (!collisionX)
			collisionX = collisionLayer
					.getCell((int) ((getPosition().x + getWidth()) / tiledWidth),
							(int) ((getPosition().y + getHeight() / 2) / tiledHeight))
					.getTile().getProperties().containsKey("blocked");
		// bottom right
		if (!collisionX)
			collisionX = collisionLayer.getCell((int) ((getPosition().x + getWidth()) / tiledWidth),
					(int) ((getPosition().y) / tiledHeight)).getTile().getProperties().containsKey("blocked");
		if (!collisionX)
			// top left
			collisionX = collisionLayer
					.getCell((int) (getPosition().x / tiledWidth), (int) ((getPosition().y + getHeight()) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
		// middle left
		if (!collisionX)
			collisionX = collisionLayer
					.getCell((int) (getPosition().x / tiledWidth), (int) ((getPosition().y + getHeight() / 2) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
		// bottom left
		if (!collisionX)
			collisionX = collisionLayer.getCell((int) (getPosition().x / tiledWidth), (int) ((getPosition().y) / tiledHeight)).getTile()
					.getProperties().containsKey("blocked");
		if (collisionX) {
			position.set(oldx , getPosition().y);
			System.out.println(oldx);
			ans = true;
		}
		if (collisionY) {
			position.set(getPosition().x, oldy);
			System.out.println("test y");
			ans=true;
		}
		return ans;
	}
}
