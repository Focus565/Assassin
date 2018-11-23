package com.project.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen{
    final Assasin game;
    
    float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Music bg_sound = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
    Texture background = new Texture("bg.jpg");
    Texture logo = new Texture("logo.png");
    Texture start = new Texture("button1.png");
    Texture start_hover = new Texture("button2.png");
    int selected = 1;
    OrthographicCamera camera;

    public MainMenuScreen(final Assasin gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

    }

	@Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
        	if(selected < 2) {
        		selected++;
        	}
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
        	if(selected > 1) {
        		selected--;
        	}
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selected) {
                case 1:
                	game.setScreen(new StageSelected(game));
                    dispose();
                    break;
                case 2:
                	System.exit(0);
                    break;
            }
        }
        
        game.batch.draw(background,0,0,width, height);
        game.batch.draw(logo, width/4.3F, height/2F, width/2, height/3);
        
        if(selected != 1) {
        	game.batch.draw(start, width/2.67F, height/3, width/4, height/8);
        	} else {
        		game.batch.draw(start_hover, width/2.67F, height/3, width/4, height/8);
        	}
        if(selected != 2) {
        	game.batch.draw(start, width/2.67F, height/4.6F, width/4, height/8);
        	} else {
        		game.batch.draw(start_hover, width/2.67F, height/4.6F, width/4, height/8);
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

