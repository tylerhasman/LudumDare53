package me.ludumdare.animalgame;

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

    public void render(SpriteBatch spriteBatch)  {

    }

    public void damage(int amount){
        health -= amount;
    }

}
