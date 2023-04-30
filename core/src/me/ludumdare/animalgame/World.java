package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
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

    public <T extends Entity> List<T> getEntitiesOfClass(Class<? extends T> entityClass){
        List<T> found = new ArrayList<>();

        for(Entity entity : entities){
            if(entityClass.isInstance(entity)){
                found.add((T) entity);
            }
        }

        return found;
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
