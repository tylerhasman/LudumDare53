package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Levels {

    public static Level LEVEL_ONE;
    public static Level LEVEL_TWO;
    public static Level LEVEL_THREE;

    static {
        List<Vector2> level1Points = Arrays.asList(new Vector2(6, 800 - 260), new Vector2(369, 800 - 427), new Vector2(557, 800 - 181), new Vector2(946, 800 - 240), new Vector2(883, 800 - 764), new Vector2(1280, 800 - 675));

        LEVEL_ONE = new Level(level1Points, "level1", "level1_invalid", new ArrayList<>());
    }

    public static Level[] LEVELS = new Level[] {
      LEVEL_ONE,
      LEVEL_TWO,
      LEVEL_THREE
    };

}
