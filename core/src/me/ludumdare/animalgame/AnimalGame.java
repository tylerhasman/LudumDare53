package me.ludumdare.animalgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.*;

public class AnimalGame extends ApplicationAdapter {

	private static AnimalGame instance = null;

	private SpriteBatch batch;

	private World world;

	private Shop shop;

	private PlayerManager playerManager;

	private Level level;
	private int levelIndex;

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
		shop = new Shop(world);

		switchLevels(0);
		setSpawnWaves(this.level.getWaveList());

	}

	private void setSpawnWaves(List<List<Enemy>> _waveList) {
		this.world.setWaves(_waveList);
	}

	public void switchLevels(int levelNumber){
		world = new World();

		this.levelIndex = levelNumber;
		this.level = Levels.LEVELS[levelNumber];
		this.level.setWorld(world);

		shop = new Shop(world);

		playerManager.setMoney(15);
		playerManager.setHealth(100);
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

	public Level getLevel() {
		return level;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

	@Override
	public void render () {
		world.update(Gdx.graphics.getDeltaTime());
		
		ScreenUtils.clear(0, 0, 0, 1);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(getTexture(level.getMapTexture()), 0, 0, world.getWidth(), world.getHeight());

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

		textures.put("cat_attack_0", new Texture("images/cat_attack1.png"));
		textures.put("cat_attack_1", new Texture("images/cat_attack2.png"));
		textures.put("cat_idle_0", new Texture("images/cat_idle1.png"));
		textures.put("cat_idle_1", new Texture("images/cat_idle2.png"));

		textures.put("mouse", new Texture("images/cat_projectile.png"));

		textures.put("gunguy_attack_0", new Texture("images/gunguy_attack1.png"));
		textures.put("gunguy_attack_1", new Texture("images/gunguy_attack2.png"));
		textures.put("gunguy_idle_0", new Texture("images/gunguy_idle1.png"));
		textures.put("gunguy_idle_1", new Texture("images/gunguy_idle2.png"));

		textures.put("bullet", new Texture("images/gunguy_projectile.png"));

		textures.put("turtle_attack_0", new Texture("images/turtle_attack1.png"));
		textures.put("turtle_attack_1", new Texture("images/turtle_attack2.png"));
		textures.put("turtle_idle_0", new Texture("images/turtle_idle1.png"));
		textures.put("turtle_idle_1", new Texture("images/turtle_idle2.png"));
		textures.put("turtle_nest", new Texture("images/turtle_nest.png"));

		textures.put("shell", new Texture("images/turtle_projectile.png"));

		textures.put("rabbit_attack_0", new Texture("images/rabbit_attack1.png"));
		textures.put("rabbit_attack_1", new Texture("images/rabbit_attack2.png"));
		textures.put("rabbit_idle_0", new Texture("images/rabbit_idle1.png"));
		textures.put("rabbit_idle_1", new Texture("images/rabbit_idle2.png"));

		textures.put("carrot", new Texture("images/rabbit_projectile.png"));

		textures.put("level1", new Texture("images/lvl1.png"));
		textures.put("level1_invalid", new Texture("images/lvl1invalid.png"));

		textures.put("level2", new Texture("images/lvl2.png"));
		textures.put("level2_invalid", new Texture("images/lvl2invalid.png"));

		textures.put("level3", new Texture("images/lvl3.png"));
		textures.put("level3_invalid", new Texture("images/lvl3invalid.png"));

		textures.put("shop_tray", new Texture("images/Shop Tray.png"));
		textures.put("brown_1", new Texture("images/brown1.png"));
		textures.put("brown_2", new Texture("images/brown2.png"));

		textures.put("range", new Texture("images/range.png"));
		textures.put("heart", new Texture("images/heart0.png"));
		textures.put("coin", new Texture("images/coin.png"));

	}
}
