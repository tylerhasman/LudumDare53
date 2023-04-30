package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This tower does nothing. It is just meant for testing.
 */
public class DummyTower extends Tower{
    public DummyTower(World world) {
        super(world);
    }

    @Override
    public void attack(Enemy enemy) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        Texture texture = AnimalGame.getTexture("cat_walk_0");

        spriteBatch.draw(texture, getPosition().x - getRadius(), getPosition().y - getRadius(), getRadius() * 2, getRadius() * 2);
    }
}
