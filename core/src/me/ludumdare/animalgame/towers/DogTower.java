package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.TowerAppearance;
import me.ludumdare.animalgame.World;

public class DogTower extends ProjectileTower {

    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"dog_idle_0", "dog_idle_1"}, new String[] {"dog_attack_0", "dog_attack_1"});

    public DogTower(World world) {
        super(world, "Dog", APPEARANCE, 15);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "tennis_ball");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
