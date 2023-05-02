package me.ludumdare.animalgame.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.ludumdare.animalgame.*;
import me.ludumdare.animalgame.projectiles.TurtleProjectile;

public class TurtleTower extends ProjectileTower {

    private static final TowerAppearance APPEARANCE =
            new TowerAppearance(new String[] {"turtle_idle_0", "turtle_idle_1"}, new String[] {"turtle_attack_0", "turtle_attack_1"});

    private boolean hidden;

    public TurtleTower(World world) {
        super(world, APPEARANCE, 200);
        setProjectileSpeed(400f);
        setAttackRange(600f);
        setAttackSpeed(5);
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public void update(float delta) {
        if(!hidden){
            super.update(delta);
        }
    }

    @Override
    public void preRender(SpriteBatch spriteBatch) {
        if(!hidden){
            super.preRender(spriteBatch);
        }
        Texture texture = AnimalGame.getTexture("turtle_nest");

        spriteBatch.draw(texture, getPosition().x - getRadius(), getPosition().y - getRadius(), getRadius() * 2, getRadius() * 2);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(!hidden){
            super.render(spriteBatch);
        }
    }

    @Override
    public Projectile createProjectile() {
        Projectile projectile = new TurtleProjectile(getWorld(), this);

        projectile.setDamage(1);
        projectile.setRadius(getRadius() * 0.8f);

        return projectile;
    }

}
