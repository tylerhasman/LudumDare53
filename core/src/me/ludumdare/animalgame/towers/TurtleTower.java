package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.World;

public class TurtleTower extends ProjectileTower {

    public TurtleTower(World world) {
        super(world, "Turtle", "dog_idle_0", 50);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "tennis_ball");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
