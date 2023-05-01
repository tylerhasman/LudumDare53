package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Level {

    private World world;
    private List<Vector2> pathPoints; //coords for pathing, including spawnPoint or goalPoint
    private String mapTexture; //level map
    private String invalidMap; //map for checking tower placements
    private List<List<String>> waveList; //list of list of enemies types (waves)

    private Pixmap pixmap;

    public Level(List<Vector2> _pathPoints, String _mapTexture, String _invalidMap, List<List<String>> _waveList) {
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

    public String getInvalidMap() {
        return invalidMap;
    }
    public List<List<String>> getWaveList() {
        return this.waveList;
    }

    public boolean isValidTowerPlacement(Vector2 cursorPosition, Tower tower) {
        //if pixel on invalidMap is blocked, return false;
        float towerRadius = tower.getRadius() / 4f;

        if(pixmap == null){
            TextureData invTex = AnimalGame.getTexture(invalidMap).getTextureData();
            if (!invTex.isPrepared()) {
                invTex.prepare();
            }
            pixmap = invTex.consumePixmap();
        }

        Color color = new Color();

        Vector2 tmp = new Vector2();

        for (float x = cursorPosition.x-towerRadius; x < cursorPosition.x+towerRadius; x++) {
            for (float y = cursorPosition.y-towerRadius; y < cursorPosition.y+towerRadius; y++) {

                tmp.set(x, y);

                if(tmp.dst2(cursorPosition) > tower.getRadius() * tower.getRadius()){
                    continue;
                }

                Color.rgba8888ToColor(color, pixmap.getPixel((int)x, (int)y));

                if (color.a >= 1.0) {
                    return false;
                }
            }
        }

        List<Entity> towers = world.getCollidingEntities(new Vector2(cursorPosition.x, Gdx.graphics.getHeight() - cursorPosition.y), towerRadius);

        return towers.stream().noneMatch(entity -> entity instanceof Tower);
    }

    public boolean isLevelDone() {
        //if enemy wave is empty && there are no enemies in the world, return true
        return ((this.waveList.size() == 0) && (world.getEntitiesOfClass(Enemy.class).size() == 0));
    }

}
