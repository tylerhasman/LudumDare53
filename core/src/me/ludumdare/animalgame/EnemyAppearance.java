package me.ludumdare.animalgame;

public class EnemyAppearance {

    public final String[] walkTextures;

    public final String deathTexture;

    public EnemyAppearance(String[] walkTextures, String deathTexture) {
        this.walkTextures = walkTextures;
        this.deathTexture = deathTexture;
    }
}
