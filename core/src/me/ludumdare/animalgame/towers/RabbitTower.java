package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.TowerAppearance;
import me.ludumdare.animalgame.World;

public class RabbitTower extends ProjectileTower {


    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"rabbit_idle_0", "rabbit_idle_1"}, new String[] {"rabbit_attack_0", "rabbit_attack_1"});


    public RabbitTower(World world) {
        super(world, "Old Man", APPEARANCE, 30);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "carrot");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
