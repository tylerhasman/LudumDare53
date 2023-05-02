package me.ludumdare.animalgame.towers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import me.ludumdare.animalgame.*;

public class CatTower extends ProjectileTower {

    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"cat_idle_0", "cat_idle_1"}, new String[] {"cat_attack_0", "cat_attack_1"});

    public CatTower(World world) {
        super(world, APPEARANCE, 20);
        setAttackRange(200f);
        setAttackSpeed(1f);
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new Projectile(getWorld(), "mouse");

        projectile.setDamage(1);
        projectile.setRadius(16);
        projectile.setLife(0.6f);

        return projectile;
    }

    @Override
    public void attack(Enemy enemy) {

        float numSlices = 8;

        for(float f = 0; f < MathUtils.PI2;f += MathUtils.PI2 / numSlices){
            Vector2 direction = new Vector2(1, 0).rotateRad(f).nor();

            Projectile projectile = createProjectile();

            projectile.getPosition().set(getPosition());

            projectile.setVelocity(direction.scl(getProjectileSpeed()));

            getWorld().addEntity(projectile);
        }
    }
}
