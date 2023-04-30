package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Tower extends Entity {

    private float attackRange;
    private float attackSpeed;
    private float attackTimer;
    private int price;

    private final String towerName;
    private final String towerTexture;

    public Tower(World world, String towerName, String towerTexture) {
        super(world);
        attackSpeed = 1f;
        attackRange = 5f;
        attackTimer = 0f;
        price = 10;
        this.towerName = towerName;
        this.towerTexture = towerTexture;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public int getPrice() { return price; }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public abstract void attack(Enemy enemy);

    public String getName(){
        return towerName;
    }

    public Texture getTexture(){
        return AnimalGame.getTexture(towerTexture);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        Texture texture = getTexture();

        spriteBatch.draw(texture, getPosition().x - getRadius(), getPosition().y - getRadius(), getRadius(), getRadius());
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        attackTimer -= delta;

        if(attackTimer <= 0){
            attackTimer = 0;

            List<Entity> nearby = getWorld().getCollidingEntities(getPosition(), attackRange + getRadius());

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
