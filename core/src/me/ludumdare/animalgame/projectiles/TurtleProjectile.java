package me.ludumdare.animalgame.projectiles;

import com.badlogic.gdx.Gdx;
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

    }
}
