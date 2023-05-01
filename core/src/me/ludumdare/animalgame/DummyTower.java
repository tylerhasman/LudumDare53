package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This tower does nothing. It is just meant for testing.
 */
public class DummyTower extends Tower{

    public DummyTower(World world) {
        super(world, "Dummy", "mailman_walk_1", 15);
    }

    @Override
    public void attack(Enemy enemy) {

    }

}
