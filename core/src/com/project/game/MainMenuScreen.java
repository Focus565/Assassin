package com.project.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MainMenuScreen implements Screen{
    final Assasin game;
    Texture bg, fade, lg, st, st_hover, blank;
    ExtendViewport viewport;
    
    float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Music bg_sound = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
    Image background = new Image(bg = new Texture("bg.jpg"));
    Image scene1 = new Image(lg = new Texture("scene1.jpg"));
    Image scene2 = new Image(lg = new Texture("scene2.jpg"));
    Image scene3 = new Image(lg = new Texture("scene3.jpg"));
    Image scene5 = new Image(lg = new Texture("scene5.jpg"));
    Image topLayer = new Image(new TextureRegion(blank=GdxTest.getTexture()));
    
    OrthographicCamera camera;
    Stage stage;
    Skin  skin = new Skin(Gdx.files.internal("uiskin.json"));
    
    TextButton buttonStart = new TextButton("New Game", skin);
    TextButton buttonCon = new TextButton("Continue", skin);
    TextButton buttonNext = new TextButton("Next", skin);

    public MainMenuScreen(final Assasin gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
       
    }
    
    public void scenes() {
        topLayer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        topLayer.setColor(Color.BLACK);
    	scene1.setSize(width, height);
    	scene2.setSize(width, height);
    	scene3.setSize(width, height);
    	scene5.setSize(width, height);
    	buttonNext.setWidth(width/12);
    	buttonNext.setHeight(height/12);
    	buttonNext.setPosition(width/1.11f,0);
		stage.addActor(scene5);
		stage.addActor(scene3);
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
    				stage.addActor(buttonNext);
    				scene1.remove();
    				scene2.addAction(Actions.fadeOut(3));
    			}
    			if(scene == 3) {
    				stage.addActor(buttonNext);
    				scene2.remove();
    				scene3.addAction(Actions.fadeOut(3));
    			}
    			if(scene == 4) {
    				scene3.remove();
    				scene5.addAction(Actions.fadeOut(3));
       				Timer.schedule(new Task(){
    				    public void run() {
    				        scene5.remove();
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
    }

    @Override
    public void show() {
    	bg_sound.setLooping(true);
        bg_sound.play();
        bg_sound.setVolume(0.05F);
        
        stage = new Stage();
        
        background.setSize(width, height);
        
        buttonStart.setWidth(width/4);
        buttonStart.setHeight(height/10);
        buttonStart.setPosition(width/2.67F, height/3);
        buttonCon.setWidth(width/4);
        buttonCon.setHeight(height/10);
        buttonCon.setPosition(width/2.67F, height/5f);  
        
        stage.addActor(background);
        stage.addActor(buttonStart);
        stage.addActor(buttonCon);
        
        scenes();
                
                        
        buttonStart.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		dispose();
        		game.setScreen(new Tutorial(game));
        	}
        	});
        
        Gdx.input.setInputProcessor(stage);

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
        stage.dispose();
    }
}

