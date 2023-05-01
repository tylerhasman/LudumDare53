package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Level {
    private List<Vector2> pathPoints; //coords for pathing, including spawnPoint or goalPoint
    private String mapTexture; //level map
    private String invalidMap; //map for checking tower placements
    private List<List<Enemy>> waveList; //array of array of enemies (waves)
    private final float spawnTimer = 3.0F; //number of seconds to wait to spawn wave after spawning previous wave
    public Level(List<Vector2> _pathPoints, String _mapTexture, String _invalidMap, List<List<Enemy>> _waveList) {
        this.pathPoints = _pathPoints;
        this.mapTexture = _mapTexture;
        this.invalidMap = _invalidMap;
        this.waveList = _waveList;
    }
    private void spawnNextWave(List<List<Enemy>> _waveList) {
        if (_waveList.size() > 0) {
            //wait for spawnTimer
            spawnWave(_waveList.remove(0));
        }
    }
    private void spawnWave(List<Enemy> wave) {
        //spawns wave at pathPoints[0]
    }
    public boolean isValidTowerPlacement(Vector2 cursorPosition, Tower tower) {
        //If towers hitbox (centered on cursorPosition) is overlapping an invalid area, return false
        return true;
    }
    public boolean isLevelDone() {
        //if enemy wave is empty && there are no enemies in the world, return true
        return ((this.waveList.size() == 0) && true);
    }

}
