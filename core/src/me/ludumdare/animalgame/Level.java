package me.ludumdare.animalgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Level {

    private World world;
    private List<Vector2> pathPoints; //coords for pathing, including spawnPoint or goalPoint
    private String mapTexture; //level map
    private String invalidMap; //map for checking tower placements
    private List<List<Enemy>> waveList; //list of list of enemies (waves)

    public Level(List<Vector2> _pathPoints, String _mapTexture, String _invalidMap, List<List<Enemy>> _waveList) {
        this.pathPoints = _pathPoints;
        this.mapTexture = _mapTexture;
        this.invalidMap = _invalidMap;
        this.waveList = _waveList;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public List<Vector2> getPathPoints() {
        return pathPoints;
    }

    public String getMapTexture() {
        return mapTexture;
    }

    private void spawnNextWave(List<List<Enemy>> _waveList) {
        //TODO: put this in an update function?
        if ((_waveList.size() > 0) && (_waveList != null)) {

        }
    }

    private void spawnWave(List<Enemy> wave) {
        //TODO
        for (Enemy enemy: wave) {

        }
    }

    public boolean isValidTowerPlacement(Vector2 cursorPosition, Tower tower) {
        //if pixel on invalidMap is blocked, return false;
        float towerRadius = tower.getRadius();
        TextureData invTex = AnimalGame.getTexture(invalidMap).getTextureData();
        if (!invTex.isPrepared()) {
            invTex.prepare();
        }
        Pixmap pixmap = invTex.consumePixmap();
        for (float x = cursorPosition.x-towerRadius; x < cursorPosition.x+towerRadius; x++) {
            for (float y = cursorPosition.y-towerRadius; y < cursorPosition.y+towerRadius; y++) {
                if (new Color(pixmap.getPixel((int)x, (int)y)).a > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLevelDone() {
        //if enemy wave is empty && there are no enemies in the world, return true
        return ((this.waveList.size() == 0) && (world.getEntitiesOfClass(Enemy.class).size() == 0));
    }

}
