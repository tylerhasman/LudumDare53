package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

public abstract class ProjectileTower extends Tower {

    private float projectileSpeed;

    public ProjectileTower(World world, TowerAppearance appearance, int price) {
        super(world, appearance, price);
        projectileSpeed = 300f;
    }

    public void setProjectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public abstract Projectile createProjectile();

    @Override
    public void attack(Enemy enemy) {
        Vector2 direction = enemy.getPosition().cpy().sub(getPosition()).nor();

        Projectile projectile = createProjectile();

        projectile.getPosition().set(getPosition());

        projectile.setVelocity(direction.scl(projectileSpeed));

        getWorld().addEntity(projectile);
    }

}
