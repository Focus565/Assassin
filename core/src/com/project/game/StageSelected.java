 package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class StageSelected implements Screen{
    final Assasin game;

    OrthographicCamera camera;
    float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Music bg_sound = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
	Music choose_sound = Gdx.audio.newMusic(Gdx.files.internal("choose.mp3"));
	
    Texture background = new Texture("bg.jpg");
    Texture stage1 = new Texture("1.png");
    Texture stage1_hover = new Texture("hover1.png");
    Texture stage2 = new Texture("2.png");
    Texture stage2_hover = new Texture("hover2.png");
    Texture stage3 = new Texture("3.png");
    Texture stage3_hover = new Texture("hover3.png");
    
    int selected = 1;
    
    public StageSelected(final Assasin gam) {
        this.game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);


    }
    


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
        	if(selected < 3) {
        		selected++;
        	}
    	}
        else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
        	if(selected > 1) {
        		selected--;
        	}
    	}
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selected) {
                case 1:
                	game.setScreen(new GameScreen(game,1));
                	choose_sound.play();
                	choose_sound.setVolume(3.1F);
                    dispose();
                    break;
                case 2:
                	game.setScreen(new StageSelected(game));
                	choose_sound.play();
                	choose_sound.setVolume(3.1F);
                    dispose();
                    break;
                case 3:
                	game.setScreen(new StageSelected(game));
                	choose_sound.play();
                	choose_sound.setVolume(3.1F);
                    dispose();
                    break;
            }
            
        }
        
        game.batch.draw(background,0,0,width, height);
        game.font.draw(game.batch, "Choose your Level" ,width/5.67F, height/1.2F);
        
        if(selected != 1) {
        	game.batch.draw(stage1, width/7.1F, height/1.87F, width/4.67F, height/4);
        	} else {
        		game.batch.draw(stage1_hover, width/7.1F, height/1.87F, width/4.67F, height/4);
        	}
        if(selected != 2) {
        	game.batch.draw(stage2, width/2.67F, height/1.87F, width/4.67F, height/4);
        	} else {
        		game.batch.draw(stage2_hover, width/2.67F, height/1.87F, width/4.67F, height/4);
        	}
        if(selected != 3) {
        	game.batch.draw(stage3, width/1.64F, height/1.87F, width/4.67F, height/4);
        	} else {
        		game.batch.draw(stage3_hover, width/1.64F, height/1.87F, width/4.67F, height/4);
        	}
        
        
        game.batch.end();

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