package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WorldRenderer implements Screen {
	final Assasin game;

	TiledMapRenderer tiledMapRenderer;
	OrthographicCamera camera;
	World world;
	ShapeRenderer shapeRenderer;
	public WorldRenderer(Assasin game, World world) {
		super();
		this.game = game;
		this.world =world;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap());
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		game.batch.begin();
		game.batch.draw(world.getPlayer().getPic(), world.getPlayer().getX(), world.getPlayer().getY(), world.getPlayer().getWidth(),world.getPlayer().getHeight());
		game.batch.draw(world.test.pic, world.test.position.x, world.test.position.y, world.getPlayer().getWidth(),world.getPlayer().getHeight());
		game.batch.end();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.circle(world.wanderSB.getWanderCenter().x, world.wanderSB.getWanderCenter().y, world.wanderSB.getWanderRadius());
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.circle(world.wanderSB.getInternalTargetPosition().x, world.wanderSB.getInternalTargetPosition().y, 4);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.line(world.test.getPosition(), world.target.getPosition());
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
