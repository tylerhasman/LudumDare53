package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

public class Level {
    private Vector2 spawnPoint; //enemy spawn point
    private Vector2[] pathPoints; //coords for pathing, not including spawnPoint or goalPoint
    private Vector2 goalPoint; //house position
    private String mapTexture; //level map
    private String invalidMap; //map for checking tower placements
    private Enemy[][] waveArray; //array of array of enemies (waves)
    private void spawnNextWave(Enemy[][] waveArray) {
        //If listWave != empty, take spawnTimer and then spawnWave()
        //Removes that wave from list of wave
    }
    public boolean isValidTowerPlacement(Vector2 cursorPosition, Tower tower) {
        //If towers hitbox (centered on cursorPosition) is overlapping an invalid area, return false
        return true;
    }

}
