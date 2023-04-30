package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Tower extends Entity {

    private float radius;

    private float attackRange;
    private float attackSpeed;
    private float attackTimer;

    public Tower(World world) {
        super(world);
        attackSpeed = 1f;
        radius = 1f;
        attackRange = 5f;
        attackTimer = 0f;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public float getRadius() {
        return radius;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public abstract void attack(Enemy enemy);

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
