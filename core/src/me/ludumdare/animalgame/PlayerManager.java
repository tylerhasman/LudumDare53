package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.Gdx.graphics;

public class PlayerManager {

    private int health;
    private int score;
    private int money;

    private float deadTimer = 3;

    private float nextLevelTimer = 3;

    private boolean isLevelDone = false;

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
        GlyphLayout scoreLayout = new GlyphLayout(font, ("Score: " + score));
        float scoreTextWidth = scoreLayout.width;

        spriteBatch.draw(AnimalGame.getTexture("heart"), 5, graphics.getHeight() - 40, 40, 40);
        font.draw(spriteBatch, String.valueOf(health), 50, graphics.getHeight() - 10);
        spriteBatch.draw(AnimalGame.getTexture("coin"), graphics.getWidth() - 150, 10, 40, 40);
        font.draw(spriteBatch, String.valueOf(money), graphics.getWidth() - moneyTextWidth - 80, 35);
        font.draw(spriteBatch, ("Score: " + score), graphics.getWidth() - 205 - scoreTextWidth , graphics.getHeight() - 10);

        isLevelDone = AnimalGame.getInstance().getLevel().isLevelDone();
        if (isLevelDone & AnimalGame.getInstance().getLevelIndex() < 2) {
            font.getData().setScale(4);
            font.draw(spriteBatch, "NEXT LEVEL", graphics.getWidth() / 2f - 200, (graphics.getHeight() / 2f) + 70);
            font.getData().setScale(1/4f);
            if (nextLevelTimer >= 0) {
                float delta = Gdx.graphics.getDeltaTime();
                nextLevelTimer -= delta;
            } else {
                nextLevelTimer = 3;
                isLevelDone = false;
                AnimalGame.getInstance().switchLevels(AnimalGame.getInstance().getLevelIndex() + 1);
            }
        } else if (isLevelDone & AnimalGame.getInstance().getLevelIndex() == 2) {
            isLevelDone = false;
            spriteBatch.flush();
            ScreenUtils.clear(0, 0, 0, 1);
            spriteBatch.draw(AnimalGame.getTexture("sign"), 0, 0, graphics.getWidth(), graphics.getHeight());
            font.draw(spriteBatch, "Created by: Bella, Nicholas, Tyler", graphics.getWidth() / 2f - 50, 20);
            font.getData().setScale(4);
            font.draw(spriteBatch, ("YOUR SCORE: " + String.valueOf(score)), graphics.getWidth() / 2f - 200, graphics.getHeight() / 2f + 110);
        }

        if (health <= 0) {
            score = 0;
            font.getData().setScale(4);
            font.draw(spriteBatch, "YOU DIED", graphics.getWidth() / 2f - 200, (graphics.getHeight() / 2f) + 70);
            font.getData().setScale(1/4f);
            if (deadTimer >= 0) {
                float delta = Gdx.graphics.getDeltaTime();
                deadTimer -= delta;
            } else {
                deadTimer = 3;
                AnimalGame.getInstance().switchLevels(AnimalGame.getInstance().getLevelIndex());
            }
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
