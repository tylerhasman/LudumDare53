package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Enemy extends Entity {

    private static final float DEATH_FADE_TIME = 1f;
    private static final float SHOW_DAMAGE_TIME = 0.35f;


    private int health;

    //Note: Do not modify this list's contents
    private final List<Vector2> path;
    private int currentPathIndex;

    private float speed;

    /**
     * The amount of damage to deal when this reaches the end of the path
     */
    private int damage;

    private int value;

    private EnemyAppearance enemyAppearance;
    private int currentFrame;
    private float frameTimer, frameSpeed;

    private float deathFade;

    private boolean deadEnemy;

    private float showDamageTimer;

    public Enemy(World world, int health, int value, EnemyAppearance enemyAppearance, List<Vector2> path) {
        super(world);
        this.health = health;
        this.path = path;
        this.value= value;
        currentPathIndex = 0;
        speed = 40;
        damage = 1;
        this.enemyAppearance = enemyAppearance;
        currentFrame = 0;
        frameSpeed = 0.5f;
        frameTimer = frameSpeed;
        deathFade = DEATH_FADE_TIME;
        deadEnemy = false;
        showDamageTimer = 0;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void damage(int amount){
        health = Math.max(health - amount, 0);
        showDamageTimer = SHOW_DAMAGE_TIME;
    }

    public boolean isDead(){
        return health <= 0;
    }

    private void onReachGoal(){
        AnimalGame.getInstance().getPlayerManager().damage(damage);
    }

    private void doPathing(float delta){
        if(currentPathIndex >= 0 && currentPathIndex < path.size()){
            Vector2 currentNode = path.get(currentPathIndex);

            float moveAmount = speed * delta;

            if(currentNode.dst2(getPosition()) <= moveAmount * moveAmount){
                getPosition().set(currentNode);
                currentPathIndex++;
                if(currentPathIndex == path.size()){
                    onReachGoal();
                    remove();
                }
            }else{
                Vector2 movement = currentNode.cpy().sub(getPosition()).nor().scl(moveAmount);

                getPosition().add(movement);
            }
        }
    }

    private void doAnimations(float delta){
        frameTimer -= delta;
        if (frameTimer <= 0){
            frameTimer = frameSpeed;

            currentFrame++;
            if(currentFrame >= enemyAppearance.walkTextures.length){
                currentFrame = 0;
            }
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(!isDead()){
            doPathing(delta);
            doAnimations(delta);
        } else{
            if (!deadEnemy) {
                deadEnemy = true;
                AnimalGame.getInstance().getPlayerManager().earnMoney(value);
                AnimalGame.getInstance().getPlayerManager().increaseScore(value);
            }
            deathFade -= delta;
            if(deathFade <= 0f){
                remove();
            }
        }

        showDamageTimer -= delta;
        if(showDamageTimer <= 0){
            showDamageTimer = 0f;
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        Texture texture;

        if(isDead()){
            texture = AnimalGame.getTexture(enemyAppearance.deathTexture);
        }else{
            texture = AnimalGame.getTexture(enemyAppearance.walkTextures[currentFrame]);
        }

        final float radius = getRadius();

        float damageTime = showDamageTimer / SHOW_DAMAGE_TIME;

        //Fade out the sprite before it dies
        spriteBatch.setColor(1, 1 - damageTime * 0.5f, 1 - damageTime * 0.5f, deathFade / DEATH_FADE_TIME);

        spriteBatch.draw(texture, getPosition().x - radius, getPosition().y - radius, radius * 2, radius * 2);

        spriteBatch.setColor(1, 1, 1, 1f);
    }
}
