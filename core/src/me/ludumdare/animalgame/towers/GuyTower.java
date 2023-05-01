package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.TowerAppearance;
import me.ludumdare.animalgame.World;

public class GuyTower extends ProjectileTower {


    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"gunguy_idle_0", "gunguy_idle_1"}, new String[] {"gunguy_attack_0", "gunguy_attack_1"});


    public GuyTower(World world) {
        super(world, "Old Man", APPEARANCE, 30);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "bullet");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
