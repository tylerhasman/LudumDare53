package me.ludumdare.animalgame;

/**
 * This tower does nothing. It is just meant for testing.
 */
public class DummyTower extends Tower{

    public DummyTower(World world) {
        super(world, new TowerAppearance(new String[] {"mailman_walk_1"}, new String[] {"mailman_walk_1"}), 15);
    }

    @Override
    public void attack(Enemy enemy) {

    }

}
