package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.World;

public class DogTower extends ProjectileTower {

    public DogTower(World world) {
        super(world, "Dog", "dog_idle_0", 15);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "tennis_ball");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
