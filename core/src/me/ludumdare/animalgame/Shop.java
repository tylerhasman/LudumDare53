package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import me.ludumdare.animalgame.towers.CatTower;
import me.ludumdare.animalgame.towers.DogTower;
import me.ludumdare.animalgame.towers.GuyTower;
import me.ludumdare.animalgame.towers.TurtleTower;

public class Shop {
    private World world;
    private Tower[][] towers = new Tower[2][2];
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
        towers[1][0] = new GuyTower(world);
        towers[1][1] = new TurtleTower(world);

    }

    public void buyTower(Tower tower) {
        int money = AnimalGame.getInstance().getPlayerManager().getMoney();
        if (money >= tower.getPrice()) {
            world.addEntity(tower);
            AnimalGame.getInstance().getPlayerManager().setMoney(money - tower.getPrice());
        } else {
            System.out.println("YOU are POOR");
        }
    }

    public Tower[][] getTowers() {
        return towers;
    }

    public void render(SpriteBatch spriteBatch) {
        int towersNumRows = towers[0].length;
        int towersNumCols = towers[1].length;

        BitmapFont font = new BitmapFont();
        final float width = 64, height = 64;
        Rectangle shopBounds = new Rectangle(Gdx.graphics.getWidth() - 2 * width, 0, towersNumCols * width, towersNumRows * height);
        for (int i = 0; i < towersNumRows; i++) {
            for (int j = 0; j < towersNumCols; j++) {
                Tower tower = towers[i][j];

                tower.setRadius(width);

                Rectangle towerBounds = new Rectangle(Gdx.graphics.getWidth() - (2 - i) * width, 0 + j * height, width, height);
                spriteBatch.draw(tower.getTexture(), Gdx.graphics.getWidth() - (2 - i) * width, 0 + j * height, width, height);
                font.draw(spriteBatch, String.valueOf(tower.getPrice()), Gdx.graphics.getWidth() - (2 - i) * width, 20 + j * height);


                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
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
                            } else {
                                Gdx.app.error("Shop", "Clicked a tower that IDK what the fuck it is");
                                return;
                            }
                            boughtTower.getPosition().set(mouseX + 32, mouseY + 32);
                            buyTower(boughtTower);
                            towerClicked = false;
                            currClickedTower = null;
                        }
                    }
                }

                if (towerClicked) {
                    spriteBatch.draw(currClickedTower.getTexture(), Gdx.input.getX() - 25, Gdx.graphics.getHeight() - Gdx.input.getY() - 25, 50, 50);
                }

                if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    towerClicked = false;
                    currClickedTower = null;
                }
            }
        }
        font.draw(spriteBatch, "SHOP", Gdx.graphics.getWidth() - ((towersNumRows + 1) * width) / 2, towersNumCols * height + font.getLineHeight());
//        spriteBatch.draw(AnimalGame.getTexture("cat_walk_0"), Gdx.graphics.getWidth() - 50,0,50,50);

    }
}