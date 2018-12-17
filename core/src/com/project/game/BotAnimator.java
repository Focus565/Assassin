package com.project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BotAnimator{

	private static final int FRAME_COLS = 3, FRAME_ROWS = 4;
	Animation<TextureRegion> walkAnimation;
	Texture walkSheet;

	float stateTime = 0f;
	
	TextureRegion currentFrame;


	public BotAnimator(int direct) {

		walkSheet = new Texture(Gdx.files.internal("packed/actor3.png"));

		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[direct][j];
			}
		}

		walkAnimation = new Animation<TextureRegion>(0.2f, walkFrames);
	}
	
	public TextureRegion getFrame() {
		currentFrame = walkAnimation.getKeyFrame(stateTime);
		return currentFrame;
	}
		
}