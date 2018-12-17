package com.project.game;

import java.awt.Rectangle;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Circle;

public class Bot extends Rectangle{
	
	public BotAnimator animate = new BotAnimator(0);
	private static int random = 0,smart = 1,find_path = 2;
	private static int state = random;
	private int right = 0,left = 1,up = 2,down = 3;
	private int dir = -1;
	private String lastDir = "";
	com.badlogic.gdx.math.Rectangle rectangle;
	private int time = 0;
	private int targetTime = 60*1;
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
		
		else if(state == smart){ //follow the player
			speedE = 3;
			boolean move = false;
			 if(x < player.getX()) {
				 if(canMove(x+speedE+64,y)) {
					 x+=speedE;
					 move = true;
					 lastDir = "right";
					 rectangle.set(x+64, y-(32)/2,32*5 , 32*3);

				 }
			 }
			 if(x > player.getX()) { 
				 if(canMove(x-speedE, y)) { 
					 x-=speedE;
					 move = true;
					 lastDir = "left";
					rectangle.set(x-32*5, y-32, 32*5,32*3 );

				 }
			 }
			 if(y < player.getY()) {
				 if(canMove(x,y+64+speedE)) { 
					 y+=speedE;
					 move = true;
					 lastDir = "down";
					 rectangle.set(x-16, y+64, 32*3, 32*5);

				 }
			 }
			 if(y > player.getY()) { 
				 if(canMove(x, y-speedE)) { 
					 y-=speedE;
					 move = true;
					 lastDir = "up";
						rectangle.set(x-16, y-32*5, 32*3, 32*5);


				 }
			 }
			if(x == player.getX() && y == player.getY()) move = true;
			if(!move) {
				state = find_path;
			}
			time++;
			if(time == 60*7) {
				state = random;
				time = 0;
			}
		}
		else if(state == find_path) {
			findPath();
		}
		
		

		if(timeSizeEn == 60*4) {
			enemySize = 32;
			timeSizeEn = 0;
		}
		timeSizeEn++;
	}
	
	/**
	 * This void method will find the new direction when bot can't move through the wall to the player
	 */
	public void findPath() {
		if(lastDir.equals("right")) {
			if(y < player.getY()) {
				if(canMove(x,y+64+speedE)) {
					y+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x,y-speedE)) {
					y-=speedE;
					state = smart;
				}
			}
			if(canMove(x+speedE, y)) x+=speedE;
		}
		
		else if(lastDir.equals("left")) {
			if(y < player.getY()) {
				if(canMove(x,y+64+speedE)) {
					y+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x, y-speedE)) {
					y-=speedE;
					state = smart;
				}
			}
			if(canMove(x-speedE, y)) x-=speedE;
		}
		
		else if(lastDir.equals("up")) {
			if(x < player.getX()) {
				if(canMove(x+speedE+64,y)) {
					x+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x-speedE, y)) {
					x-=speedE;
					state = smart;
				}
			}
			if(canMove(x, y-speedE)) y-=speedE;
		}
		
		else if(lastDir.equals("down")) {
			if(x < player.getX()) {
				if(canMove(x+speedE+64,y)) {
					x+=speedE;
					state = smart;
				}
			}else {
				if(canMove(x-speedE,y)) {
					x-=speedE;
					state = smart;
				}
				
			}
			if(canMove(x, y+speedE)) y+=speedE;
		}
	
		time++;
		if(time == 60*7) {
			state = random;
			time = 0;
		}
	}
	
	/**
	 * This void method check the random direction(right left up or down) that can move or not.
	 * If the bot can't move, It'll random the new direction.
	 */
	public void checkMove(){
		if(dir == right){
			if(canMove(x+speedE+64,y)){
				x+=speedE;
				rectangle.set(x+64, y-(32)/2,32*5 , 32*3);
				animate = new BotAnimator(2);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == left){
			if(canMove(x-speedE,y)){
				x-=speedE;
				rectangle.set(x-32*5, y-32, 32*5,32*3 );
				animate = new BotAnimator(1);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == up){
			if(canMove(x,y+64+speedE)){
				y+=speedE;
				rectangle.set(x-16, y+64, 32*3, 32*5);
				animate = new BotAnimator(3);
				animate.stateTime += Gdx.graphics.getDeltaTime();
			}
			else{
				dir = randomGen.nextInt(4);
			}
		}
		else if(dir == down){
			if(canMove(x,y-speedE)){
				y-=speedE;
				rectangle.set(x-16, y-32*5, 32*3, 32*5);
				animate = new BotAnimator(0);
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
			state = smart;
		}
		return true;
	}
	public boolean isFound(int nextX,int nextY) {
		return false;
	}

	
}