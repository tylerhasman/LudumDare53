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
        money = 20;
        score = 0;
        health = 100;

    }

    public void setMoney(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void setHealth(int money) {
        this.health = money;
    }
    public int getHealth() {
        return health;
    }

    public void render(SpriteBatch spriteBatch)  {
        BitmapFont font = new BitmapFont();
        GlyphLayout layout = new GlyphLayout(font, String.valueOf(money));
        float moneyTextWidth = layout.width;

        font.draw(spriteBatch, String.valueOf(health), 10, Gdx.graphics.getHeight());
        font.draw(spriteBatch, String.valueOf(money), Gdx.graphics.getWidth() - moneyTextWidth - 10, Gdx.graphics.getHeight());
    }

    public void damage(int amount){
        health -= amount;
    }

}
