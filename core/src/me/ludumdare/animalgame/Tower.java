package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public abstract class Tower extends Entity {

    private static final int ANIMATION_STATE_IDLE = 0, ANIMATION_STATE_ATTACKING = 1;

    private float attackRange;
    private float attackSpeed;
    private float attackTimer;
    private int price;

    private final TowerAppearance appearance;

    private int animationState;
    private int currentFrame;
    private float frameTimer;
    private float frameSpeed;

    public Tower(World world, TowerAppearance appearance, int price) {
        super(world);
        attackSpeed = 1f;
        attackRange = 200f;
        attackTimer = 0f;
        this.price  = price;
        this.appearance = appearance;
        changeAnimationState(ANIMATION_STATE_IDLE, 0.5f);
    }

    private void changeAnimationState(int newState, float frameSpeed){
        if(newState != currentFrame){
            animationState = newState;
            currentFrame = 0;
            frameTimer = frameSpeed;
            this.frameSpeed = frameSpeed;
        }
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public int getPrice() { return price; }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public abstract void attack(Enemy enemy);

    private String[] getCurrentAnimationFrames(){
        String[] animArray = null;

        if(animationState == ANIMATION_STATE_IDLE){
            animArray = appearance.idleAnimations;
        }else if(animationState == ANIMATION_STATE_ATTACKING){
            animArray = appearance.attackingAnimations;
        }

        return animArray;
    }

    public Texture getTexture(){
        Texture texture = null;

        String[] animArray = getCurrentAnimationFrames();

        if(animArray != null){
            String frame = animArray[currentFrame % animArray.length];

            texture = AnimalGame.getTexture(frame);
        }

        return texture;
    }

    @Override
    public void preRender(SpriteBatch spriteBatch) {
        super.preRender(spriteBatch);

        Texture texture = getTexture();

        if(texture != null){
            spriteBatch.setColor(0, 0, 0, 0.2f);
            spriteBatch.draw(texture, getPosition().x - getRadius(), getPosition().y - getRadius() * 0.85f, getRadius() * 2, 60);
            spriteBatch.setColor(1, 1, 1, 1);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        Texture texture = getTexture();

        if(texture != null){
            spriteBatch.draw(texture, getPosition().x - getRadius(), getPosition().y - getRadius() , getRadius() * 2, getRadius() * 2);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(AnimalGame.getInstance().getPlayerManager().getHealth() <= 0){
            return;
        }

        attackTimer -= delta;

        if(attackTimer <= 0){
            attackTimer = 0;

            List<Entity> nearby = getWorld().getCollidingEntities(getPosition(), attackRange + getRadius());

            for(Entity entity : nearby){
                if(entity instanceof Enemy){
                    Enemy enemy = (Enemy) entity;
                    if(enemy.isDead()){
                        continue;
                    }
                    if(entity.getPosition().dst2(getPosition()) <= attackRange * attackRange){
                        changeAnimationState(ANIMATION_STATE_ATTACKING, attackSpeed / 4f);
                        attack(enemy);
                        AnimalGame.playSound("shoot", 0.15f);
                        attackTimer = attackSpeed;
                        break;
                    }
                }
            }
        }

        frameTimer -= delta;
        if(frameTimer <= 0){
            frameTimer = frameSpeed;

            currentFrame++;

            if(currentFrame >= getCurrentAnimationFrames().length){
                changeAnimationState(ANIMATION_STATE_IDLE, 0.5f);
            }
        }

    }
}
