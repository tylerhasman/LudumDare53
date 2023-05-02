package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import me.ludumdare.animalgame.towers.*;

import static me.ludumdare.animalgame.AnimalGame.getInstance;

public class Shop {
    private World world;
    private Tower[][] towers = new Tower[2][3];
    //    private List<Tower> towers = new ArrayList<Tower>;
    private BitmapFont font;
    private Tower currClickedTower;
    private boolean towerClicked;


    public Shop(World world) {
        this.world = world;
        font = new BitmapFont();
        currClickedTower = null;
        towerClicked = false;

//        towers.add(new DummyTower(world));
        towers[0][0] = new CatTower(world);
        towers[0][1] = new DogTower(world);
        towers[0][2] = new RabbitTower(world);
        towers[1][0] = new GuyTower(world);
        towers[1][1] = new TurtleTower(world);
    }

    public void buyTower(Tower tower) {
        int money = getInstance().getPlayerManager().getMoney();
        if (money >= tower.getPrice()) {
            world.addEntity(tower);
            getInstance().getPlayerManager().setMoney(money - tower.getPrice());
        } else {
            System.out.println("YOU are POOR");
        }
    }

    public Tower[][] getTowers() {
        return towers;
    }

    public void render(SpriteBatch spriteBatch) {
        int towersNumCols = towers.length;
        int towersNumRows = towers[0].length;

        BitmapFont font = new BitmapFont();
        final float width = 96, height = 96;
        Rectangle shopBounds = new Rectangle(Gdx.graphics.getWidth() - 200, 0, 200, 800);
        spriteBatch.draw(AnimalGame.getTexture("shop_tray"),Gdx.graphics.getWidth() - 200,0,200,800);
        spriteBatch.draw(AnimalGame.getTexture("brown_2"), Gdx.graphics.getWidth() - 200, 100, 200, Gdx.graphics.getHeight() - 245);
        for (int i = 0; i < towersNumCols; i++) {
            for (int j = 0; j < towersNumRows; j++) {
                if (i == 1 & j == 2) {
                    break; // no towers at towers[1][2]
                }
                Tower tower = towers[i][j];

                tower.setRadius(width);

                Rectangle towerBounds = new Rectangle(Gdx.graphics.getWidth() - (2 - i) * width + 5, Gdx.graphics.getHeight() - (j+1) * height - 150 - 5, width, height);

                if(AnimalGame.getInstance().getPlayerManager().getMoney() < tower.getPrice()){
                    font.setColor(Color.RED);
                    spriteBatch.setColor(0.8f, 0.2f, 0.2f, 1f);
                }

                spriteBatch.draw(AnimalGame.getTexture("brown_1"), Gdx.graphics.getWidth() - (2 - i) * width, Gdx.graphics.getHeight() - (j+1) * height - 150, width-4, height-4);
                spriteBatch.draw(tower.getTexture(), Gdx.graphics.getWidth() - (2 - i) * width, Gdx.graphics.getHeight() - (j+1) * height - 150, width, height);

                spriteBatch.setColor(1, 1, 1, 1);


                font.draw(spriteBatch, String.valueOf(tower.getPrice()), Gdx.graphics.getWidth() - (2 - i) * width + 5, 20 + Gdx.graphics.getHeight() - (j+1) * height - 150);
                font.setColor(Color.WHITE);

                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && AnimalGame.getInstance().getPlayerManager().getMoney() >= tower.getPrice()) {
                    int mouseX = Gdx.input.getX();
                    int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

                    if (towerBounds.contains(mouseX, mouseY)) {
                        towerClicked = true;
                        currClickedTower = tower;
                    } else if (!shopBounds.contains(mouseX, mouseY)) {
                        if (towerClicked) {
                            Tower boughtTower;
                            if (currClickedTower instanceof CatTower) {
                                boughtTower = new CatTower(world);
                            } else if (currClickedTower instanceof DogTower) {
                                boughtTower = new DogTower(world);
                            } else if (currClickedTower instanceof GuyTower) {
                                boughtTower = new GuyTower(world);
                            } else if (currClickedTower instanceof TurtleTower) {
                                boughtTower = new TurtleTower(world);
                            } else if (currClickedTower instanceof RabbitTower) {
                                boughtTower = new RabbitTower(world);
                            } else {
                                Gdx.app.error("Shop", "Clicked a tower that IDK what the fuck it is");
                                return;
                            }
                            boughtTower.getPosition().set(mouseX, mouseY);
                            boolean valid = AnimalGame.getInstance().getLevel().isValidTowerPlacement(new Vector2(mouseX, Gdx.input.getY()), boughtTower);
                            if (valid) {
                                buyTower(boughtTower);
                                towerClicked = false;
                                currClickedTower = null;
                            }
                        }
                    }
                }

                if (towerClicked) {

                    Texture invalidMap = AnimalGame.getTexture(AnimalGame.getInstance().getLevel().getInvalidMap());

                    spriteBatch.setColor(0.8f, 0.4f, 0.4f, 0.1f);

                    spriteBatch.draw(invalidMap, 0, 0);

                    spriteBatch.setColor(1, 1, 1, 1);

                    boolean valid = AnimalGame.getInstance().getLevel().isValidTowerPlacement(new Vector2(Gdx.input.getX(), Gdx.input.getY()), currClickedTower);
                    if (!valid) {
                        spriteBatch.setColor(1, 0.5f, 0.5f, 1);
                    }



                    spriteBatch.draw(currClickedTower.getTexture(), Gdx.input.getX() - width / 2, Gdx.graphics.getHeight() - Gdx.input.getY() - height / 2, width, height);
                    spriteBatch.setColor(1, 1, 1, 1);
                    float range = currClickedTower.getAttackRange();
                    spriteBatch.draw(AnimalGame.getTexture("range"), Gdx.input.getX() - range, Gdx.graphics.getHeight() - Gdx.input.getY() - range, range * 2, range * 2);
                }

                if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    towerClicked = false;
                    currClickedTower = null;
                }
            }
        }
//        spriteBatch.draw(AnimalGame.getTexture("cat_walk_0"), Gdx.graphics.getWidth() - 50,0,50,50);

    }
}