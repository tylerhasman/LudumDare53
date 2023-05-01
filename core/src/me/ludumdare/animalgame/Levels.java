package me.ludumdare.animalgame;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Levels {

    public static Level LEVEL_ONE, LEVEL_TWO, LEVEL_THREE;
    public static int Y = 800;

    static {
        String mailman = "mail_man";
        String azazon = "azazon";
        String stork = "stork";
        String pigeon = "pigeon";
        String santa = "santa";
        // LEVEL 1 //////////////////////////////////////////////////////////////////////////////
        List<Vector2> level1Points = Arrays.asList
                (new Vector2(6, Y - 260), new Vector2(390, Y - 430),
                        new Vector2(560, Y - 190), new Vector2(780, Y-160),
                        new Vector2(975, Y - 270), new Vector2(855, Y - 710),
                        new Vector2(1000, Y-755), new Vector2(1280, Y - 610));

        List<String> oneWave1 = new ArrayList<>();
        oneWave1.add(mailman);
        oneWave1.add(mailman);
        oneWave1.add(mailman);
        oneWave1.add(mailman);
        oneWave1.add(mailman);
        List<String> oneWave2 = new ArrayList<>();
        oneWave2.add(mailman);
        oneWave2.add(azazon);
        oneWave2.add(azazon);
        oneWave2.add(mailman);
        oneWave2.add(azazon);
        List<String> oneWave3 = new ArrayList<>();
        oneWave3.add(mailman);
        oneWave3.add(mailman);
        oneWave3.add(azazon);
        oneWave3.add(mailman);
        oneWave3.add(azazon);
        List<String> oneWave4 = new ArrayList<>();
        oneWave4.add(mailman);
        oneWave4.add(mailman);
        oneWave4.add(azazon);
        oneWave4.add(azazon);
        oneWave4.add(azazon);
        List<String> oneWave5 = new ArrayList<>();
        oneWave5.add(mailman);
        oneWave5.add(mailman);
        oneWave5.add(azazon);
        oneWave5.add(mailman);
        oneWave5.add(azazon);
        List<String> oneWave6 = new ArrayList<>();
        oneWave6.add(mailman);
        oneWave6.add(azazon);
        oneWave6.add(mailman);
        oneWave6.add(mailman);
        oneWave6.add(azazon);
        List<String> oneWave7 = new ArrayList<>();
        oneWave7.add(azazon);
        oneWave7.add(mailman);
        oneWave7.add(azazon);
        oneWave7.add(mailman);
        oneWave7.add(azazon);
        List<String> oneWave8 = new ArrayList<>();
        oneWave8.add(azazon);
        oneWave8.add(azazon);
        oneWave8.add(azazon);
        oneWave8.add(azazon);
        oneWave8.add(azazon);
        List<List<String>> level1Enemies = new ArrayList<>();
        level1Enemies.add(oneWave1);
        level1Enemies.add(oneWave2);
        level1Enemies.add(oneWave3);
        level1Enemies.add(oneWave4);
        level1Enemies.add(oneWave5);
        level1Enemies.add(oneWave6);
        level1Enemies.add(oneWave7);
        level1Enemies.add(oneWave8);

        LEVEL_ONE = new Level(level1Points, "level1", "level1_invalid", level1Enemies);

        // LEVEL 2 //////////////////////////////////////////////////////////////////////////////
        List<Vector2> level2Points = Arrays.asList
                (new Vector2(300, Y), new Vector2(300, Y-75),
                        new Vector2(675, Y-75), new Vector2(675, Y-270),
                        new Vector2(175, Y-270), new Vector2(175, Y-650),
                        new Vector2(900, Y-650), new Vector2(900, Y-270),
                        new Vector2(1150, Y-270), new Vector2(1150, 0));

        List<String> twoWave2 = new ArrayList<>();
        twoWave2.add(azazon);
        twoWave2.add(mailman);
        twoWave2.add(pigeon);
        twoWave2.add(pigeon);
        twoWave2.add(azazon);
        List<String> twoWave4 = new ArrayList<>();
        twoWave4.add(pigeon);
        twoWave4.add(santa);
        twoWave4.add(pigeon);
        twoWave4.add(azazon);
        twoWave4.add(azazon);
        List<String> twoWave7 = new ArrayList<>();
        twoWave7.add(pigeon);
        twoWave7.add(stork);
        twoWave7.add(pigeon);
        twoWave7.add(azazon);
        twoWave7.add(stork);
        List<String> twoWave8 = new ArrayList<>();
        twoWave8.add(pigeon);
        twoWave8.add(pigeon);
        twoWave8.add(pigeon);
        twoWave8.add(pigeon);
        twoWave8.add(pigeon);
        List<String> twoWave9 = new ArrayList<>();
        twoWave8.add(stork);
        twoWave8.add(stork);
        twoWave8.add(stork);
        twoWave8.add(stork);
        twoWave8.add(stork);
        List<List<String>> level2Enemies = new ArrayList<>();
        level2Enemies.add(oneWave1); //reuse waves from level 1
        level2Enemies.add(twoWave2);
        level2Enemies.add(oneWave7); //reusing waves
        level2Enemies.add(twoWave4);
        level2Enemies.add(oneWave8); //reusing wave
        level2Enemies.add(twoWave2); //reusing wave from level 2
        level2Enemies.add(twoWave7);
        level2Enemies.add(twoWave8);
        level2Enemies.add(twoWave9);

        LEVEL_TWO = new Level(level2Points, "level2", "level2_invalid", level2Enemies);

        // LEVEL 3 //////////////////////////////////////////////////////////////////////////////
        List<Vector2> level3Points = Arrays.asList
                (new Vector2(0, Y-275), new Vector2(70, Y-275),
                        new Vector2(70, Y-470), new Vector2(600, Y-470),
                        new Vector2(600, Y-90), new Vector2(280, Y-90),
                        new Vector2(280, Y-720), new Vector2(1030,Y-720),
                        new Vector2(1075, Y-400), new Vector2(915, Y-200),
                        new Vector2(1180, Y));

        List<String> threeWave4 = new ArrayList<>();
        List<String> threeWave5 = new ArrayList<>();
        List<String> threeWave6 = new ArrayList<>();
        List<String> threeWave7 = new ArrayList<>();
        List<String> threeWave8 = new ArrayList<>();
        List<String> threeWave9 = new ArrayList<>();
        List<String> threeWave10 = new ArrayList<>();
        threeWave10.add(santa);
        threeWave10.add(santa);
        threeWave10.add(santa);
        threeWave10.add(santa);
        threeWave10.add(santa);
        List<List<String>> level3Enemies = new ArrayList<>();
        level3Enemies.add(oneWave7); //reusing waves from level 1
        level3Enemies.add(twoWave2); //reusing waves
        level3Enemies.add(twoWave7); //reusing waves
        level3Enemies.add(threeWave4);
        level3Enemies.add(threeWave5);
        level3Enemies.add(threeWave6);
        level3Enemies.add(threeWave7);
        level3Enemies.add(threeWave8);
        level3Enemies.add(threeWave9);
        level3Enemies.add(threeWave10);

        LEVEL_THREE = new Level(level3Points, "level3", "level3_invalid", level3Enemies);
    }

    public static Level[] LEVELS = new Level[] {
      LEVEL_ONE,
      LEVEL_TWO,
      LEVEL_THREE
    };

}
