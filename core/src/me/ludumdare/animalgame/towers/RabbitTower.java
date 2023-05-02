package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.*;

public class RabbitTower extends ProjectileTower {

    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"rabbit_idle_0", "rabbit_idle_1"}, new String[] {"rabbit_attack_0", "rabbit_attack_1"});

    public RabbitTower(World world) {
        super(world, APPEARANCE, 50);
        setAttackSpeed(1f);
        setAttackRange(400f);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "carrot");

        projectile.setDamage(0.5f);
        projectile.setRadius(16);
        projectile.setRemoveOnDamage(false);

        return projectile;
    }

}
