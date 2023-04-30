package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.World;

public class GuyTower extends ProjectileTower {

    public GuyTower(World world) {
        super(world, "Old Man", "dog_idle_0");
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "tennis_ball");

        projectile.setDamage(1);
        projectile.setRadius(0.25f);

        return projectile;
    }

}
