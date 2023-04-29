package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    private final World world;

    private boolean removed;

    public Entity(World world){
        this.world = world;
        removed = false;
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
