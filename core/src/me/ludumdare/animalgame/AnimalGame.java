package me.ludumdare.animalgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class AnimalGame extends ApplicationAdapter {

	private SpriteBatch batch;

	private World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World();
	}

	@Override
	public void render () {

		world.update(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		world.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
