package com.project.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AnimationDemo extends ApplicationAdapter {
	SpriteBatch batch;
    Sprite banana;
    TextureAtlas textureAtlas;
    

    @Override
    public void create () {
        batch = new SpriteBatch();
        
        textureAtlas = new TextureAtlas("assets.txt");
        banana = textureAtlas.createSprite("banana");
        
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        banana.draw(batch);
        batch.end();
    }
    
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }
}