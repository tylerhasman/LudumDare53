package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Enemy extends Entity {

    private static final float DEATH_FADE_TIME = 1f;
    private static final float SHOW_DAMAGE_TIME = 0.35f;


    private float health;

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

    private String deathSpawn;
    private int deathSpawnAmount;

    public Enemy(World world, int health, int value, EnemyAppearance enemyAppearance, List<Vector2> path) {
        super(world);
        this.health = health;
        this.path = path;
        this.value = value;
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

    public void setDeathSpawn(String deathSpawn) {
        this.deathSpawn = deathSpawn;
    }

    public void setDeathSpawnAmount(int deathSpawnAmount) {
        this.deathSpawnAmount = deathSpawnAmount;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void damage(float amount){
        health = Math.max(health - amount, 0);
        showDamageTimer = SHOW_DAMAGE_TIME;
        AnimalGame.playSound("hit", 0.03f);
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

        if(AnimalGame.getInstance().getPlayerManager().getHealth() <= 0){
            return;
        }

        if(!isDead()){
            doPathing(delta);
            doAnimations(delta);
        } else{
            if (!deadEnemy) {
                deadEnemy = true;
                AnimalGame.getInstance().getPlayerManager().earnMoney(value);
                AnimalGame.getInstance().getPlayerManager().increaseScore(value);

                if(deathSpawn != null && !deathSpawn.isEmpty()){
                    for(int i = 0; i < deathSpawnAmount;i++){
                        Enemy child = AnimalGame.createEnemy(getWorld(), deathSpawn, path);
                        if (child != null) {
                            child.currentPathIndex = currentPathIndex;
                            child.getPosition().set(getPosition().cpy().add(MathUtils.random(-32f, 32f), MathUtils.random(-32f, 32f)));
                            getWorld().addEntity(child);
                        }
                    }
                }
                AnimalGame.playSound("death", 0.05f);

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
    public void preRender(SpriteBatch spriteBatch) {
        super.preRender(spriteBatch);

        Texture texture;

        if(isDead()){
            texture = AnimalGame.getTexture(enemyAppearance.deathTexture);
        }else{
            texture = AnimalGame.getTexture(enemyAppearance.walkTextures[currentFrame]);
        }

        if(texture != null){
            spriteBatch.setColor(0, 0, 0, 0.2f);
            spriteBatch.draw(texture, getPosition().x - getRadius(), getPosition().y - getRadius() * 0.85f, getRadius() * 2, 60);
            spriteBatch.setColor(1, 1, 1, 1);
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

        if(texture != null){
            final float radius = getRadius();

            float damageTime = showDamageTimer / SHOW_DAMAGE_TIME;

            //Fade out the sprite before it dies
            spriteBatch.setColor(1, 1 - damageTime * 0.5f, 1 - damageTime * 0.5f, deathFade / DEATH_FADE_TIME);

            spriteBatch.draw(texture, getPosition().x - radius, getPosition().y - radius, radius * 2, radius * 2);

            spriteBatch.setColor(1, 1, 1, 1f);
        }

    }
}
