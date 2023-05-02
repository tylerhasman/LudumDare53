package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.*;

public class GuyTower extends ProjectileTower {


    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"gunguy_idle_0", "gunguy_idle_1"}, new String[] {"gunguy_attack_0", "gunguy_attack_1"});


    public GuyTower(World world) {
        super(world, APPEARANCE, 100);
        setAttackSpeed(0.05f);
        setAttackRange(650f);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "bullet");

        projectile.setDamage(0.2f);
        projectile.setRadius(16);

        return projectile;
    }

}
