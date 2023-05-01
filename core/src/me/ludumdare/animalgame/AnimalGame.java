package me.ludumdare.animalgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.HashMap;
import java.util.Map;

public class AnimalGame extends ApplicationAdapter {

	private static AnimalGame instance = null;

	private SpriteBatch batch;

	private World world;

	private Shop shop;

	private PlayerManager playerManager;

	private Map<String, Texture> textures;

	private OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World();
		textures = new HashMap<>();
		instance = this;

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);

		loadTextures();

		playerManager = new PlayerManager();
		shop  = new Shop(world);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.setToOrtho(false);

	}

	public static Texture getTexture(String textureName){
		return instance.textures.get(textureName);
	}

	public static AnimalGame getInstance() {
		return instance;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	@Override
	public void render () {
		world.update(Gdx.graphics.getDeltaTime());
		
		ScreenUtils.clear(0, 0, 0, 1);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
//		batch.draw(AnimalGame.getTexture("level1"), 0, 0);
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

	private void loadTextures(){

		textures.put("cat_walk_0", new Texture("images/cat walk 0.png"));
		textures.put("cat_walk_1", new Texture("images/cat walk 1.png"));

		textures.put("azazon_death", new Texture("images/azazon_death.png"));
		textures.put("azazon_walk_1", new Texture("images/azazon_walk1.png"));
		textures.put("azazon_walK_2", new Texture("images/azazon_walk2.png"));

		textures.put("mailman_death", new Texture("images/mailman_death.png"));
		textures.put("mailman_walk_1", new Texture("images/mailman_walk1.png"));
		textures.put("mailman_walk_2", new Texture("images/mailman_walk2.png"));

		textures.put("pigeon_death", new Texture("images/pigeon_death.png"));
		textures.put("pigeon_walk_1", new Texture("images/pigeon_walk1.png"));
		textures.put("pigeon_walk_2", new Texture("images/pigeon_walk2.png"));

		textures.put("santa_death", new Texture("images/santa_death.png"));
		textures.put("santa_walk_1", new Texture("images/santa_walk1.png"));
		textures.put("santa_walk_2", new Texture("images/santa_walk2.png"));

		textures.put("stork_death", new Texture("images/stork_death.png"));
		textures.put("stork_walk_1", new Texture("images/stork_walk1.png"));
		textures.put("stork_walk_2", new Texture("images/stork_walk2.png"));

		textures.put("dog_attack_0", new Texture("images/dog_attack1.png"));
		textures.put("dog_attack_1", new Texture("images/dog_attack2.png"));
		textures.put("dog_idle_0", new Texture("images/dog_idle1.png"));
		textures.put("dog_idle_1", new Texture("images/dog_idle2.png"));

		textures.put("tennis_ball", new Texture("images/dog_projectile.png"));

		textures.put("level1", new Texture("images/lvl1.png"));
		textures.put("level1_invalid", new Texture("images/lvl1invalid.png"));

		textures.put("level2", new Texture("images/lvl2.png"));
		textures.put("level2_invalid", new Texture("images/lvl2invalid.png"));


	}
}
