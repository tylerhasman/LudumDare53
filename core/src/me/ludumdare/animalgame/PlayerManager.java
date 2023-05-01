package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerManager {

    private int health;
    private int score;
    private int money;

    public PlayerManager() {
        money = 15;
        score = 0;
        health = 100;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

    public void render(SpriteBatch spriteBatch)  {
        BitmapFont font = new BitmapFont();
        GlyphLayout moneyLayout = new GlyphLayout(font, String.valueOf(money));
        float moneyTextWidth = moneyLayout.width;
        GlyphLayout scoreLayout = new GlyphLayout(font, String.valueOf(score));
        float scoreTextWidth = scoreLayout.width;

        spriteBatch.draw(AnimalGame.getTexture("heart"), 5, Gdx.graphics.getHeight() - 40, 40, 40);
        font.draw(spriteBatch, String.valueOf(health), 50, Gdx.graphics.getHeight() - 10);
        spriteBatch.draw(AnimalGame.getTexture("coin"), Gdx.graphics.getWidth() - 150, 10, 40, 40);
        font.draw(spriteBatch, String.valueOf(money), Gdx.graphics.getWidth() - moneyTextWidth - 80, 35);
        font.draw(spriteBatch, String.valueOf(score), Gdx.graphics.getWidth() - 205 - scoreTextWidth , Gdx.graphics.getHeight() - 10);

/*        boolean levelDone = AnimalGame.getInstance().getLevel().isLevelDone();
        if (levelDone) {
            AnimalGame.getInstance().switchLevels(AnimalGame.getInstance().getLevelIndex() + 1);
        }*/

        if (health <= 0) {
            AnimalGame.getInstance().switchLevels(0);
        }
    }

    public void damage(int amount){
        health -= amount;
    }

    public void earnMoney(int amount){
        money += amount;
    }

    public void increaseScore(int amount){
        score += amount;
    }

}
