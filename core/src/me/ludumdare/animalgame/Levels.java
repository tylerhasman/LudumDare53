package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Levels {

    public static Level LEVEL_ONE, LEVEL_TWO, LEVEL_THREE;
    public static int Y = 800;

    static {
        // LEVEL 1 //////////////////////////////////////////////////////////////////////////////
        List<Vector2> level1Points = Arrays.asList
                (new Vector2(6, Y - 260), new Vector2(390, Y - 430),
                        new Vector2(560, Y - 190), new Vector2(780, Y-160),
                        new Vector2(975, Y - 270), new Vector2(855, Y - 710),
                        new Vector2(1000, Y-755), new Vector2(1280, Y - 610));

        List<List<Enemy>> level1Enemies = Arrays.asList();

        LEVEL_ONE = new Level(level1Points, "level1", "level1_invalid", level1Enemies);

        // LEVEL 2 //////////////////////////////////////////////////////////////////////////////
        List<Vector2> level2Points = Arrays.asList
                (new Vector2(300, Y), new Vector2(300, Y-75),
                        new Vector2(675, Y-75), new Vector2(675, Y-270),
                        new Vector2(175, Y-270), new Vector2(175, Y-650),
                        new Vector2(900, Y-650), new Vector2(900, Y-270),
                        new Vector2(1150, Y-270), new Vector2(1150, 0));

        List<List<Enemy>> level2Enemies = Arrays.asList();

        LEVEL_TWO = new Level(level2Points, "level2", "level2_invalid", level2Enemies);

        // LEVEL 3 //////////////////////////////////////////////////////////////////////////////
        List<Vector2> level3Points = Arrays.asList
                (new Vector2(0, Y-275), new Vector2(70, Y-275),
                        new Vector2(70, Y-470), new Vector2(600, Y-470),
                        new Vector2(600, Y-90), new Vector2(280, Y-90),
                        new Vector2(280, Y-720), new Vector2(1030,Y-720),
                        new Vector2(1075, Y-400), new Vector2(915, Y-200),
                        new Vector2(1180, Y));

        List<List<Enemy>> level3Enemies = Arrays.asList();

        LEVEL_THREE = new Level(level3Points, "level3", "level3_invalid", level3Enemies);
    }

    public static Level[] LEVELS = new Level[] {
      LEVEL_ONE,
      LEVEL_TWO,
      LEVEL_THREE
    };

}
