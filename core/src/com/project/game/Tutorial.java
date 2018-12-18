package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Tutorial implements Screen{
	Assasin game;
	Texture tutorial1, tutorial2, blank;
	
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Image scene1 = new Image(tutorial1 = new Texture("tutorial1.jpg"));
    Image scene2 = new Image(tutorial2 = new Texture("tutorial2.jpg"));
    Image topLayer = new Image(new TextureRegion(blank=GdxTest.getTexture()));
    
    OrthographicCamera camera;
    Stage stage;
    Skin  skin = new Skin(Gdx.files.internal("uiskin.json"));
    
    TextButton buttonNext = new TextButton("Next", skin);
    
    public Tutorial(final Assasin gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
       
    }
    
	@Override
	public void show() {
		topLayer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        topLayer.setColor(Color.BLACK);
    	scene1.setSize(width, height);
    	scene2.setSize(width, height);
    	buttonNext.setWidth(width/12);
    	buttonNext.setHeight(height/12);
    	buttonNext.setPosition(width/1.11f,0);
		stage.addActor(scene2);
    	stage.addActor(scene1);
    	stage.addActor(topLayer);
    	topLayer.addAction(Actions.fadeOut(3));
    	
		Timer.schedule(new Task(){
		    public void run() {
		        topLayer.remove();
		        stage.addActor(buttonNext);
		    }
		}, 3);
		
		buttonNext.addListener(new ClickListener(){
    		int scene = 0;
    		public void clicked(InputEvent event, float x, float y) {
    			scene++;
    			buttonNext.remove();
    			if(scene == 1) {
    				stage.addActor(buttonNext);
    				scene1.addAction(Actions.fadeOut(3));
    			}
    			if(scene == 2) {
    				scene1.remove();
    				scene2.addAction(Actions.fadeOut(3));
       				Timer.schedule(new Task(){
    				    public void run() {
    				    	game.setScreen(new StageSelected(game));
    				        scene2.remove();
    				    }
    				}, 3);
    			}
       				super.clicked(event, x, y);
       	         	;
       	         }
       	         });
    			
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		stage.act();
        stage.draw();   
		
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
