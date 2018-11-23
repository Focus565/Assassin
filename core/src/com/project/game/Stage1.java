package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class Stage1 implements Screen {
	final Assasin game;
	Music bg_sound = Gdx.audio.newMusic(Gdx.files.internal("stage.mp3"));
	WorldRenderer worldRenderer;
	World world;

	public Stage1(final Assasin game) {
		this.game = game;
		world = new World();
		worldRenderer = new WorldRenderer(game, world);
		
	}

	@Override
	public void render(float delta) {
		world.update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRenderer.render(delta);

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		bg_sound.setLooping(true);
		bg_sound.play();
		bg_sound.setVolume(0.5F);

	}

	@Override
	public void hide() {
		bg_sound.stop();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}