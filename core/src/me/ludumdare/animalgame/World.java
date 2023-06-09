package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class World {

    private List<Entity> entities, toAdd;
    private List<List<String>> enemyWaves;
    private List<String> toAddEnemies;

    //DEBUG

    private final float SPAWNTIMER = 0.8f;
    private final float WAVETIMER = 4.0f;
    private float spawnEnemyTimer;
    private float spawnWaveTimer;

    private int waveIndex;

    //END DEBUG

    public World(){
        entities = new ArrayList<>();
        toAdd = new ArrayList<>();
        enemyWaves = new ArrayList<>();
        toAddEnemies = new ArrayList<>();
        waveIndex = 0;
    }

    //not good to hardcode ths but whatever losers
    public float getWidth(){
        return 1280;
    }

    public float getHeight(){
        return 800;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public void setWaves(List<List<String>> _enemyWaves) {
        this.enemyWaves = _enemyWaves;
    }
    public void addEntity(Entity entity){
        toAdd.add(entity);
    }

    public List<Entity> getEntities() {
            return entities;
    }

    /**
     * Get a list of entities that are colliding with a circle.
     * The list will be sorted from nearest to furthest.
     * @param position the position of the circle
     * @param radius the radius of the circle
     */
    public List<Entity> getCollidingEntities(Vector2 position, float radius){
        List<Entity> colliding = new ArrayList<>();

        //This is slow but can be optimized later if needed
        for(Entity entity : entities){
            //We do all this squaring to avoid calling sqrt which is actually quite expensive!!
            if(entity.getPosition().dst2(position) <= (radius + entity.getRadius()) * (radius + entity.getRadius())){
                colliding.add(entity);
            }
        }

        colliding.sort((e1, e2) -> Float.compare(e1.getPosition().dst2(position), e2.getPosition().dst2(position)));

        return colliding;
    }

    public void update(float delta){
        //HERE
        entities.addAll(toAdd);
        toAdd.clear();

        for(Entity entity : entities){
            entity.update(delta);
        }

        entities.removeIf(Entity::isRemoved);
        entities.sort((e1, e2) -> {

            if(e1 instanceof Projectile && e2 instanceof Projectile){
                return 0;
            }

            if(e1 instanceof Projectile){
                return 1;
            }

            if(e2 instanceof Projectile){
                return -1;
            }

            return Float.compare(e2.getPosition().y, e1.getPosition().y);

        });
        //HERE DO NOT TOUCH NO EXCEPTIONS

        spawnWaves(delta);
        spawnEnemy(delta);
    }

    private void spawnEnemy(float delta) {

        boolean hasTower = false;

        for(Entity entity : entities){
            if(entity instanceof Tower){
                hasTower = true;
                break;
            }
        }

        if(hasTower){
            spawnEnemyTimer -= delta;
            if(spawnEnemyTimer <= 0){
                spawnEnemyTimer = SPAWNTIMER;
                if (!toAddEnemies.isEmpty()) {
                    List<Vector2> path = AnimalGame.getInstance().getLevel().getPathPoints();
                    Enemy enemy = AnimalGame.createEnemy(this, toAddEnemies.remove(0), path);
                    enemy.getPosition().set(path.get(0));
                    addEntity(enemy);
                }
            }
            AnimalGame.playMusic("level1");
        }else{
            AnimalGame.playMusic("waiting");
        }
    }

    private void spawnWaves(float delta) {

        if(toAddEnemies.size() == 0){
            //after the timer is up, put a new wave into the toAddEnemies list to queue to be spawned
            spawnWaveTimer -= delta;
            if (spawnWaveTimer <= 0) {
                spawnWaveTimer = WAVETIMER;
                if (waveIndex < enemyWaves.size()) {
                    toAddEnemies.addAll(enemyWaves.get(waveIndex));
                    waveIndex += 1;
                }
            }
        }

    }

/*    private void spawnEntity(float delta) {


        spawnEnemyTimer -= delta;
        if(spawnEnemyTimer <= 0){
            spawnEnemyTimer = SPAWNTIMER;

            List<Vector2> path = new ArrayList<>();
            path.add(new Vector2(Gdx.graphics.getWidth()-50,100));

            Enemy enemy = AnimalGame.createEnemy(this, "pigeon", path);
            enemy.getPosition().set(50, 50);
            addEntity(enemy);
        }

        // Wave - list of enemy types

    }*/

    public void render(SpriteBatch spriteBatch){
        for(Entity entity : entities){
            entity.preRender(spriteBatch);
        }
        for(Entity entity : entities){
            entity.render(spriteBatch);
        }
    }

}
