package com.project.game;

import java.awt.Rectangle;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Circle;

public class Bot extends Rectangle{

	public BotAnimator animate = new BotAnimator(0);
	private static int random = 0;
	private static int state = random;
	private int right = 0,left = 1,up = 2,down = 3;
	private int dir = -1;
	com.badlogic.gdx.math.Rectangle rectangle;
	Circle circle;
	public Random randomGen;
	public int speedE = 2;
	public int enemySize = 32;
	public int timeSizeEn = 0;
	Player player;
	TiledMapTileLayer level;
	ShapeRenderer shape;
	public Bot(int x ,int y,Player player, TiledMapTileLayer level){
		this.player = player;
		this.level = level;
		randomGen = new Random();
		setBounds(x,y,64,64);
		dir = randomGen.nextInt(4);
		rectangle = new com.badlogic.gdx.math.Rectangle();
		rectangle.set(x, y, 64, 64);
	}
	
	public void tick(){
		if(state == random){
			speedE = 2;
			checkMove();
//			time++;
//			if(time == targetTime) {
//				state = smart;
//				time = 0;
//			}			
			}
		}
		

	
	/**
	 * This void method check the random direction(right left up or down) that can move or not.
	 * If the bot can't move, It'll random the new direction.
	 */
	public void checkMove(){
		if(dir == right){
			animate = new BotAnimator(2);
			if(canMove(x+speedE+64,y)){
				x+=speedE;
				rectangle.set(x+64, y-(32)/2,32*4 , 32*4);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == left){
			animate = new BotAnimator(1);
			if(canMove(x-speedE,y)){
				x-=speedE;
				rectangle.set(x-32*4, y-32, 32*4,32*4);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == up){
			animate = new BotAnimator(3);
			if(canMove(x,y+64+speedE)){
				y+=speedE;
				rectangle.set(x-32, y+64, 32*4, 32*4);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == down){
			animate = new BotAnimator(0);
			if(canMove(x,y-speedE)){
				y-=speedE;
				rectangle.set(x-32, y-32*4, 32*4, 32*4);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else{
			dir = randomGen.nextInt(4);
		}
	}

	private boolean canMove(int nextX,int nextY){		
		if(level.getCell((int)(nextX/level.getTileWidth()), (int)(nextY/level.getTileHeight())).getTile().getProperties().containsKey("blocked")) {
			return false;
		}
		
		if(rectangle.contains(player.getX(), player.getY())) {
			System.out.println("overlap");
		}
		return true;
	}

	public boolean isFound(int nextX,int nextY) {
		return false;
	}

	
}