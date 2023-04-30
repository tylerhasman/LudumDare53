package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Tower extends Entity {

    private float radius;

    private float attackRange;
    private float attackSpeed;
    private float attackTimer;
    private int price;

    public Tower(World world) {
        super(world);
        attackSpeed = 1f;
        radius = 1f;
        attackRange = 5f;
        attackTimer = 0f;
        price = 10;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public float getRadius() {
        return radius;
    }

    public int getPrice() { return price; }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public abstract void attack(Enemy enemy);

    public abstract String getName();
    public abstract Texture getTexture();

    @Override
    public void update(float delta) {
        super.update(delta);

        attackTimer -= delta;

        if(attackTimer <= 0){
            attackTimer = 0;

            List<Entity> nearby = getWorld().getCollidingEntities(getPosition(), attackRange + radius);

            for(Entity entity : nearby){
                if(entity instanceof Enemy){
                    Enemy enemy = (Enemy) entity;
                    if(entity.getPosition().dst2(getPosition()) <= attackRange * attackRange){
                        attack(enemy);
                        attackTimer = attackSpeed;
                        break;
                    }
                }
            }
        }

    }
}
