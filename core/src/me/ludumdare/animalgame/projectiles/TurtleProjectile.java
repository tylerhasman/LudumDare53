package me.ludumdare.animalgame.projectiles;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.World;
import me.ludumdare.animalgame.towers.TurtleTower;

public class TurtleProjectile extends Projectile {

    private float life;

    private TurtleTower turtleTower;

    public TurtleProjectile(World world, TurtleTower turtleTower) {
        super(world, "shell");

        life = 10f;

        this.turtleTower = turtleTower;
        turtleTower.setHidden(true);

        setRemoveOnDamage(false);
    }

    @Override
    public void remove() {
        super.remove();
        turtleTower.setHidden(false);
    }

    private void unstick(){

        if(getPosition().y < getRadius()){
            getPosition().y = getRadius() + 1;
        }

        if(getPosition().y > getWorld().getHeight() - getRadius()){
            getPosition().y = getWorld().getHeight() - getRadius() - 1;
        }



        if(getPosition().x < getRadius()){
            getPosition().x = getRadius() + 1;
        }

        if(getPosition().x > getWorld().getWidth() - getRadius()){
            getPosition().x = getWorld().getWidth() - getRadius() - 1;
        }

    }

    @Override
    public void update(float delta) {
        super.update(delta);

        life -= delta;

        if(life <= 0f){
            remove();
        }

        if(getPosition().y < getRadius() || getPosition().y > getWorld().getHeight() - getRadius()){
            getVelocity().y *= -1;
        }

        if(getPosition().x < getRadius() || getPosition().x > getWorld().getWidth() - getRadius()){
            getVelocity().x *= -1;
        }

        unstick();
    }
}
