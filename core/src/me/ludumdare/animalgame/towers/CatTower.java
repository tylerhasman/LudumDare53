package me.ludumdare.animalgame.towers;

import me.ludumdare.animalgame.Projectile;
import me.ludumdare.animalgame.ProjectileTower;
import me.ludumdare.animalgame.TowerAppearance;
import me.ludumdare.animalgame.World;

public class CatTower extends ProjectileTower {

    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"cat_idle_0", "cat_idle_1"}, new String[] {"cat_attack_0", "cat_attack_1"});

    public CatTower(World world) {
        super(world, "Cat", APPEARANCE, 20);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "mouse");

        projectile.setDamage(1);
        projectile.setRadius(16);

        return projectile;
    }

}
