package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WorldRenderer implements Screen {
	final Assasin game;

	TiledMapRenderer tiledMapRenderer;
	OrthographicCamera camera;
	World world;
	ShapeRenderer shape; 
	public WorldRenderer(Assasin game, World world) {
		super();
		this.game = game;
		this.world = world;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getTiledMap());
		shape = new ShapeRenderer();
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
		game.batch.draw(world.getAnimator().getFrame(), world.getPlayer().getX(), world.getPlayer().getY(), world.getPlayer().getWidth(),world.getPlayer().getHeight());
		game.batch.draw(world.bot1.animate.getFrame(), world.bot1.x, world.bot1.y, 64,64);
		game.batch.draw(world.bot2.animate.getFrame(), world.bot2.x, world.bot2.y, 64,64);
		game.batch.draw(world.bot3.animate.getFrame(), world.bot3.x, world.bot3.y, 64,64);
		game.batch.draw(world.bot4.animate.getFrame(), world.bot4.x, world.bot4.y, 64,64);
		game.batch.draw(world.target.animate.getFrame(), world.target.x, world.target.y, 64,64);
		game.batch.end();
		shape.begin(ShapeType.Line);
		shape.setColor(0, 1, 0, 1);
		shape.rect(world.bot1.rectangle.x, world.bot1.rectangle.y, world.bot1.rectangle.width, world.bot1.rectangle.height);
		shape.rect(world.bot2.rectangle.x, world.bot2.rectangle.y, world.bot2.rectangle.width, world.bot2.rectangle.height);
		shape.rect(world.bot3.rectangle.x, world.bot3.rectangle.y, world.bot3.rectangle.width, world.bot3.rectangle.height);
		shape.rect(world.bot4.rectangle.x, world.bot4.rectangle.y, world.bot4.rectangle.width, world.bot4.rectangle.height);
		shape.end();
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
