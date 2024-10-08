package com.nopalsoft.dragracer.shop;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.MainStreet;
import com.nopalsoft.dragracer.Settings;
import com.nopalsoft.dragracer.screens.MainMenuScreen;
import com.nopalsoft.dragracer.screens.Screens;

public class ShopScreen extends Screens {

    Button buttonCharacters, buttonPowerUps, buttonCoins, buttonNoAds, buttonBack;

    Label labelCoin;

    ScrollPane scrollPane;
    Table container;

    public ShopScreen(final MainStreet game) {
        super(game);

        Label labelShop = new Label("Shop", Assets.labelStyleLarge);
        labelShop.setSize(135, 50);
        labelShop.setPosition(3, 747);

        Image imageCoin = new Image(Assets.coinFront);

        labelCoin = new Label("0", Assets.labelStyleLarge);
        labelCoin.setFontScale(.8f);

        Table tableScores = new Table();
        tableScores.setWidth(SCREEN_WIDTH);
        tableScores.setPosition(0, SCREEN_HEIGHT - labelCoin.getHeight() / 2);
        tableScores.padLeft(5).padRight(5);

        tableScores.add(labelCoin).right().expand().padRight(5);
        tableScores.add(imageCoin).right();

        Image imageHorizontalSeparator = new Image(Assets.horizontalSeparator);
        imageHorizontalSeparator.setSize(SCREEN_WIDTH, 5);
        imageHorizontalSeparator.setColor(Color.LIGHT_GRAY);
        imageHorizontalSeparator.setPosition(0, 740);

        Image imageVerticalSeparator = new Image(Assets.verticalSeparator);
        imageVerticalSeparator.setSize(5, 745);
        imageVerticalSeparator.setColor(Color.LIGHT_GRAY);
        imageVerticalSeparator.setPosition(90, 0);

        initializeButtons();

        container = new Table();

        scrollPane = new ScrollPane(container, Assets.styleScrollPane);
        scrollPane.setSize(SCREEN_WIDTH - 95, (SCREEN_HEIGHT - 62));
        scrollPane.setPosition(95, 0);

        stage.addActor(tableScores);
        stage.addActor(labelShop);
        stage.addActor(imageVerticalSeparator);
        stage.addActor(imageHorizontalSeparator);
        stage.addActor(buttonCharacters);

        stage.addActor(buttonCoins);
        stage.addActor(buttonNoAds);
        stage.addActor(buttonBack);
        stage.addActor(scrollPane);

        new CharactersSubMenu(game, container);

    }

    private void initializeButtons() {
        buttonCharacters = new Button(new TextureRegionDrawable(Assets.carTornado));
        buttonCharacters.setSize(45, 65);
        buttonCharacters.setPosition(23, 660);
        addPressEffect(buttonCharacters);
        buttonCharacters.addListener(new ClickListener() {
            public void clicked(
                    InputEvent event, float x,
                    float y) {
                new CharactersSubMenu(game, container);
            }

        });

        buttonPowerUps = new Button(new TextureRegionDrawable(Assets.carTornado));
        buttonPowerUps.setSize(55, 55);
        buttonPowerUps.setPosition(17, 570);
        addPressEffect(buttonPowerUps);

        buttonCoins = new Button(new TextureRegionDrawable(Assets.coinFront));
        buttonCoins.setSize(55, 55);
        buttonCoins.setPosition(17, 480);
        addPressEffect(buttonCoins);
        buttonCoins.addListener(new ClickListener() {
            public void clicked(
                    InputEvent event, float x,
                    float y) {
                new GetCoinsSubMenu(game, container);
            }

        });

        buttonNoAds = new Button(new TextureRegionDrawable(Assets.buttonNoAds));
        buttonNoAds.setSize(55, 55);
        buttonNoAds.setPosition(17, 390);
        addPressEffect(buttonNoAds);
        buttonNoAds.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                new NoAdsSubMenu(game, container);
            }
        });

        buttonBack = new Button(new TextureRegionDrawable(Assets.buttonBack));
        buttonBack.setSize(55, 55);
        buttonBack.setPosition(17, 10);
        addPressEffect(buttonBack);
        buttonBack.addListener(new ClickListener() {
            public void clicked(
                    InputEvent event, float x,
                    float y) {
                changeScreenWithFadeOut(MainMenuScreen.class, game);
            }

        });

    }

    @Override
    public void draw(float delta) {

    }

    @Override
    public void update(float delta) {
        labelCoin.setText(Settings.coinsTotal + "");
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
            changeScreenWithFadeOut(MainMenuScreen.class, game);
            return true;
        }
        return false;
    }

}
