package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

	private OrthographicCamera camera;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Array<Rectangle> collisionRectangles;
	private Player player;
	private Cursor cursor;
	private SpriteBatch batch;
	private List<Bullet> Bullets;

	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Adjust this to your game resolution
		player = new Player();

		camera.position.set(player.Return_x(),player.Returnr_y(),0);


		tiledMap = new TmxMapLoader().load("CollisionTest2.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		collisionRectangles = new Array<>();
		for (RectangleMapObject rectObject : tiledMap.getLayers().get("Object Layer 1").getObjects().getByType(RectangleMapObject.class)) {
			collisionRectangles.add(rectObject.getRectangle());
		}
		cursor = new Cursor(Gdx.input.getX(),Gdx.input.getY());
		batch = new SpriteBatch();
		Bullets = new ArrayList<Bullet>();

		Gdx.input.setCursorCatched(true);

	}
	

	@Override
	public void render(float delta) {
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();


		if(Gdx.input.isTouched()){
			Bullet bullet = new Bullet(player.Return_x(), player.Returnr_y(), cursor.Cursor_x(), cursor.Cursor_y());
			Bullets.add(bullet);
		}

		// Handle player input for movement
		Vector2 movement = new Vector2();
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) movement.x -= 200 * delta;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) movement.x +=  200 *delta;
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) movement.y +=  200 *delta;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) movement.y -=  200 *delta;
		if(!player.move(movement,collisionRectangles)) {
			cursor.real_position(movement);
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) camera.position.x += movement.x *2;
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.position.x += movement.x *2;
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.position.y += movement.y *2;
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.position.y += movement.y *2;
		}

		// Move the player, checking for collisions
		player.move(movement, collisionRectangles);
		batch.setProjectionMatrix(camera.combined);


		// Render the player
		batch.begin();
		cursor.tracking(Gdx.input.getX(),Gdx.input.getY());
		cursor.render(batch);
		player.render(batch);
		int i = 0;
		while (i < Bullets.size()){
			Bullet bullet = Bullets.get(i);
			bullet.move(collisionRectangles);
			bullet.render(batch);
			if(bullet.move(collisionRectangles) == true){
				Bullets.remove(i);
			}
			else{
				i++;
			}
		}

		// You can draw your player's sprite or texture here
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		tiledMap.dispose();
		mapRenderer.dispose();
		batch.dispose();
	}
}
