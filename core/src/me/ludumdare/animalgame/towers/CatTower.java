package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.World;

public class CatTower extends ProjectileTower {

    public CatTower(World world) {
        super(world, "Cat", "cat_idle_0", 20);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "mouse");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
