package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Projectile extends Entity{

    private final Vector2 velocity;
    private float hitboxRadius;
    private int damage;

    private String texture;

    public Projectile(World world, String texture) {
        super(world);
        velocity = new Vector2();
        hitboxRadius = 0.1f;
        damage = 1;
        this.texture = texture;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setVelocity(Vector2 velocity){
        this.velocity.set(velocity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        getPosition().add(velocity.x * delta, velocity.y * delta);

        List<Entity> nearbyEntities = getWorld().getCollidingEntities(getPosition(), getRadius() + hitboxRadius);

        for(Entity nearby : nearbyEntities){
            if(nearby instanceof Enemy){
                Enemy enemy = (Enemy) nearby;

                enemy.damage(damage);

                remove();

                break;
            }
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        Texture texture = AnimalGame.getTexture(this.texture);

        final float radius = getRadius();

        spriteBatch.draw(texture, getPosition().x - radius, getPosition().y - radius, radius * 2, radius * 2);
    }
}
