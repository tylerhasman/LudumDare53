package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Projectile extends Entity{

    private final Vector2 velocity;
    private float hitboxRadius;
    private float damage;

    private String texture;

    private boolean removeOnDamage;

    private List<Enemy> inside;

    private float life;

    public Projectile(World world, String texture) {
        super(world);
        velocity = new Vector2();
        hitboxRadius = 0.1f;
        damage = 1;
        this.texture = texture;
        removeOnDamage = true;
        inside = new ArrayList<>();
        life = 10f;
    }

    public void setLife(float life) {
        this.life = life;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setVelocity(Vector2 velocity){
        this.velocity.set(velocity);
    }

    public void setRemoveOnDamage(boolean removeOnDamage) {
        this.removeOnDamage = removeOnDamage;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        life -= delta;
        if(life <= 0f){
            remove();
        }

        getPosition().add(velocity.x * delta, velocity.y * delta);

        inside.removeIf(enemy -> enemy.getPosition().dst2(getPosition()) > (getRadius() + hitboxRadius + enemy.getRadius()) * (getRadius() + hitboxRadius + enemy.getRadius()));

        List<Entity> nearbyEntities = getWorld().getCollidingEntities(getPosition(), getRadius() + hitboxRadius);

        for(Entity nearby : nearbyEntities){
            if(nearby instanceof Enemy){
                Enemy enemy = (Enemy) nearby;

                if(enemy.isDead()){
                    continue;
                }

                //Prevents things from the turtle shell from double hitting something until it leaves it
                if(inside.contains(enemy)){
                    continue;
                }
                enemy.damage(damage);

                if(removeOnDamage){
                    remove();
                }else{
                    inside.add(enemy);
                }

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
