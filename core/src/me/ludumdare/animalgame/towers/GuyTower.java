package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.World;

public class GuyTower extends ProjectileTower {

    public GuyTower(World world) {
        super(world, "Old Man", "gunguy_idle_0", 30);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "bullet");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
