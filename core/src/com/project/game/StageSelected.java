 package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StageSelected implements Screen{
    final Assasin game;

    OrthographicCamera camera;
    Texture bg, myTexture1, myTexture2, myTexture3, blank;
    Stage stage;
    
    private TextureRegion myTextureRegion1, myTextureRegion2, myTextureRegion3;
    private TextureRegionDrawable myTexRegionDrawable1, myTexRegionDrawable2, myTexRegionDrawable3;
    private ImageButton button1, button2, button3;
    Skin  skin = new Skin(Gdx.files.internal("uiskin.json"));
    
    float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Music bg_sound = Gdx.audio.newMusic(Gdx.files.internal("select.mp3"));
	Music choose_sound = Gdx.audio.newMusic(Gdx.files.internal("choose.mp3"));
	
    Image background = new Image(bg = new Texture("bg.jpg")); 
    Image choose = new Image(bg = new Texture("choose.PNG"));  
    
    
    public StageSelected(final Assasin gam) {
        this.game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        
        stage = new Stage();
        
        myTexture1 = new Texture(Gdx.files.internal("1.png"));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        button1 = new ImageButton(myTexRegionDrawable1);
        
        myTexture2 = new Texture(Gdx.files.internal("2.png"));
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        button2 = new ImageButton(myTexRegionDrawable2);
        
        myTexture3 = new Texture(Gdx.files.internal("3.png"));
        myTextureRegion3 = new TextureRegion(myTexture3);
        myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
        button3 = new ImageButton(myTexRegionDrawable3);
        
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw(); 
        
        
        }
    
    public void actions() {
        button1.addListener(new ClickListener(){
        	
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		button1.setSize(width/3, height/3);
        		choose.setPosition(width/6.1F, height/2.17F);
        		button2.setPosition(width/2.13F, height/3.87F);
        		button3.setPosition(width/1.44F, height/3.87F);
             }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	button1.setSize(width/4.67f, height/4);
            	choose.setPosition(width/6.1F, height/2.5F);
            	button2.setPosition(width/2.67F, height/3.87F);
            	button3.setPosition(width/1.64F, height/3.87F);
             }
  
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		game.setScreen(new GameScreen(game,1));
                dispose();
        	}
        	});
        
        button2.addListener(new ClickListener(){
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		button2.setSize(width/3, height/3);
        		choose.setPosition(width/6.1F, height/2.17F);
        		button2.setPosition(width/2.77F, height/3.87F);
        		button3.setPosition(width/1.44F, height/3.87F);
             }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	choose.setPosition(width/6.1F, height/2.5F);
            	button2.setSize(width/4.67f, height/4);
        		button2.setPosition(width/2.67F, height/3.87F);
        		button3.setPosition(width/1.64F, height/3.87F);
             }
  
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		game.setScreen(new GameScreen(game,2));
            	choose_sound.play();
            	choose_sound.setVolume(3.1F);
        		dispose();
        	}
        	});
        
        button3.addListener(new ClickListener(){
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		button3.setSize(width/3, height/3);
        		button1.setPosition(width/6.97F, height/3.87F);
        		button2.setPosition(width/2.57F, height/3.87F);
             }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	button3.setSize(width/4.67f, height/4);
        		button1.setPosition(width/7.1F, height/3.87F);
        		button2.setPosition(width/2.67F, height/3.87F);
             }
  
        	public void clicked(InputEvent event, float x, float y) {
        		super.clicked(event, x, y);
        		dispose();
        		game.setScreen(new MainMenuScreen(game));
        	}
        	});
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        bg_sound.setLooping(true);
        bg_sound.play();
        bg_sound.setVolume(0.01F);
               
        background.setSize(width, height);
        choose.setSize(width/3, height/3);
        stage.addActor(background);
        button1.setSize(width/4.67f, height/4);
        button1.setPosition(width/7.1F, height/3.87F);
        button2.setSize(width/4.67f, height/4);
        button2.setPosition(width/2.67F, height/3.87F);
        button3.setSize(width/4.67f, height/4);
        button3.setPosition(width/1.64F, height/3.87F);
        choose.setPosition(width/6.1F, height/2.5F);
        stage.addActor(choose);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        
        actions();
                
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