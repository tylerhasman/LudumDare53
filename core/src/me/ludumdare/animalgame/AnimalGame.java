package me.ludumdare.animalgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

public class AnimalGame extends ApplicationAdapter {

	private static AnimalGame instance = null;

	private SpriteBatch batch;

	private World world;

	private Shop shop;

	private PlayerManager playerManager;

	private Map<String, Texture> textures;

	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World();
		textures = new HashMap<>();
		instance = this;

		textures.put("cat_walk_0", new Texture("cat walk 0.png"));
		textures.put("cat_walk_1", new Texture("cat walk 1.png"));

		playerManager = new PlayerManager();
		shop  = new Shop(world, playerManager);
	}

	public static Texture getTexture(String textureName){
		return instance.textures.get(textureName);
	}

	public static AnimalGame getInstance() {
		return instance;
	}

	@Override
	public void render () {
		world.update(Gdx.graphics.getDeltaTime());
		
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		world.render(batch);
		shop.render(batch);
		playerManager.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textures.values().forEach(Texture::dispose);
		textures.clear();
	}
}
