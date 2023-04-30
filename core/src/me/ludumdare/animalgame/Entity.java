package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    private final World world;

    private boolean removed;

    private final Vector2 position;

    private float radius;

    public Entity(World world){
        this.world = world;
        removed = false;
        position = new Vector2();
        radius = 64;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public World getWorld() {
        return world;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void remove(){
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    /**
     * Update this entities logic
     * @param delta the time since the last frame
     */
    public void update(float delta){

    }

    /**
     * Render this entity
     * @param spriteBatch the spritebatch to use for rendering
     */
    public void render(SpriteBatch spriteBatch){

    }

}
