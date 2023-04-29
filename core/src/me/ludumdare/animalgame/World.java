package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class World {

    private List<Entity> entities, toAdd;

    public World(){
        entities = new ArrayList<>();
        toAdd = new ArrayList<>();
    }

    public void addEntity(Entity entity){
        toAdd.add(entity);
    }

    public void update(float delta){

        entities.addAll(toAdd);
        toAdd.clear();

        for(Entity entity : entities){
            entity.update(delta);
        }

        entities.removeIf(Entity::isRemoved);
    }

    public void render(SpriteBatch spriteBatch){
        for(Entity entity : entities){
            entity.render(spriteBatch);
        }
    }

}
