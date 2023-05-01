package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerManager {

    private Shop shop;
    private int health;
    private int score;
    private int money;
    private int currentLevel;

    public PlayerManager() {
        money = 200;
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

    public void changeCurrentLevel(int level) {
        currentLevel = level;
        setMoney(0);
        setHealth(100);
    }

    public void render(SpriteBatch spriteBatch)  {
        BitmapFont font = new BitmapFont();
        GlyphLayout layout = new GlyphLayout(font, String.valueOf(money));
        float moneyTextWidth = layout.width;

        font.draw(spriteBatch, String.valueOf(health), 10, Gdx.graphics.getHeight());
        font.draw(spriteBatch, String.valueOf(money), Gdx.graphics.getWidth() - moneyTextWidth - 10, Gdx.graphics.getHeight());
        font.draw(spriteBatch, String.valueOf(score), Gdx.graphics.getWidth() / 2f , Gdx.graphics.getHeight());
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
