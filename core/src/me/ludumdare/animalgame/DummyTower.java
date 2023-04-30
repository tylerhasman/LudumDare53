package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This tower does nothing. It is just meant for testing.
 */
public class DummyTower extends Tower{

    public DummyTower(World world) {
        super(world, "Dummy", "cat_walk_0");
    }

    @Override
    public void attack(Enemy enemy) {

    }

}
