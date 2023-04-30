package me.ludumdare.animalgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import java.util.ArrayList;
import java.util.List;


public class Shop implements InputProcessor {
    private World world;
    private List<Tower> towers = new ArrayList<Tower>();

    private PlayerManager playerManager;

    private Stage stage;
    private Vector2 mousePosition;
    private BitmapFont font;
    private Tower currClickedTower;
    private boolean towerClicked;


    public Shop(World world, PlayerManager playerManager) {
        this.world = world;
        this.playerManager = playerManager;
        font = new BitmapFont();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        mousePosition = new Vector2();
        stage.getRoot().setX(100);
        stage.getRoot().setY(100);

        currClickedTower = null;
        towerClicked = false;

        towers.add(new DummyTower(world));
        towers.add(new DummyTower(world));


    }

    public void buyTower(Tower tower) {
        int money = playerManager.getMoney();
        if (money >= tower.getPrice()) {
            world.addEntity(tower);
            playerManager.setMoney(money - tower.getPrice());
        } else {
            System.out.println("YOU are POOR");
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void render(SpriteBatch spriteBatch) {
        Skin skin = new Skin();
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.font = new BitmapFont();

        skin.add("default", labelStyle);
        skin.add("default", textFieldStyle);
        Table table = new Table();

        Image blankImage = new Image();
        table.add(blankImage).width(50).height(20).pad(10);
        Label towerName = new Label("TOWER", skin);
        table.add(towerName).pad(5);
        Label towerPrice = new Label("PRICE", skin);
        table.add(towerPrice).pad(5);
        table.row();


        for (int i = 0; i < towers.size(); i++) {
            final int towerIndex = i;

            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = new TextureRegionDrawable(new TextureRegion(towers.get(i).getTexture()));
            ImageButton towerImage = new ImageButton(style);

            towerImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Do something when the first item is clicked
                    towerClicked = true;
                    currClickedTower = towers.get(towerIndex);
                }
            });

            table.add(towerImage).width(50).height(50).pad(10);


            Label towerNameLabel = new Label(towers.get(i).getName(), skin);
            table.add(towerNameLabel).pad(5);
            Label towerPriceLabel = new Label(Integer.toString(towers.get(i).getPrice()), skin);
            table.add(towerPriceLabel).pad(5);
            table.row();
        }

        stage.addActor(table);
        stage.draw();

        if (towerClicked) {
            mousePosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            spriteBatch.draw(currClickedTower.getTexture(), mousePosition.x - 25, mousePosition.y - 25, 50, 50);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                buyTower(currClickedTower);
                towerClicked = false;
                currClickedTower = null;
            }
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            towerClicked = false;
            currClickedTower = null;
        }

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Update the mouse position
        mousePosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}