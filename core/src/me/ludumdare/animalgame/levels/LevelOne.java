package me.ludumdare.animalgame.levels;

import com.badlogic.gdx.math.Vector2;
import me.ludumdare.animalgame.Enemy;
import me.ludumdare.animalgame.Level;
import me.ludumdare.animalgame.World;

import java.util.List;

public class LevelOne extends Level {

    public LevelOne(World _world, List<Vector2> _pathPoints, String _mapTexture, String _invalidMap, List<List<Enemy>> _waveList) {
        super(_world, _pathPoints, _mapTexture, _invalidMap, _waveList);
    }
}
