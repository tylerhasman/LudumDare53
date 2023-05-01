package me.ludumdare.animalgame;

public class EnemyData {

    public final int health;

    public final int pointValue;

    public final EnemyAppearance appearance;

    public final int damage;

    public final float speed;

    public final String deathSpawn;

    public final int deathSpawnAmount;

    public EnemyData(int health, int pointValue, EnemyAppearance appearance, int damage, float speed, String deathSpawn, int deathSpawnAmount) {
        this.health = health;
        this.pointValue = pointValue;
        this.appearance = appearance;
        this.damage = damage;
        this.speed = speed;
        this.deathSpawn = deathSpawn;
        this.deathSpawnAmount = deathSpawnAmount;
    }

/*    public EnemyData() {
        health = json.optInt("Health", 1);
        pointValue = json.optInt("PointValue", 1);

        damage = json.optInt("Damage", 1);
        speed = json.optFloat("Speed", 50);

        JSONObject deathSpawnJson = json.optJSONObject("Spawn", new JSONObject());

        JSONObject appearanceJson = json.optJSONObject("Appearance", new JSONObject());

        JSONArray walkTexturesArray = appearanceJson.optJSONArray("WalkTextures");

        if(walkTexturesArray == null){
            walkTexturesArray = new JSONArray();
        }

        String[] walkTextures = new String[walkTexturesArray.length()];

        for(int i = 0; i < walkTexturesArray.length();i++){
            walkTextures[i] = walkTexturesArray.getString(i);
        }


        appearance = new EnemyAppearance(walkTextures, appearanceJson.optString("DeathTexture"));

        deathSpawn = deathSpawnJson.optString("enemy_type");
        deathSpawnAmount = deathSpawnJson.optInt("amount", 0);

    }*/
}
