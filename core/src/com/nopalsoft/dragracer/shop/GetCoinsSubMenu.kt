package com.nopalsoft.dragracer.shop;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.MainStreet;
import com.nopalsoft.dragracer.Settings;

public class GetCoinsSubMenu {

    // Number of coins that player will receive if they like our page on facebook
    int coinsLikeFacebook = 250;

    TextButton buttonLikeFacebook;
    TextButton buttonBuy50MillionCoins;

    Table container;
    MainStreet game;

    public GetCoinsSubMenu(final MainStreet game, Table container) {
        this.game = game;
        this.container = container;
        container.clear();

        buttonLikeFacebook = new TextButton("Like us", Assets.styleTextButtonBuy);
        if (Settings.didLikeFacebook)
            buttonLikeFacebook = new TextButton("Visit Us",
                    Assets.styleTextButtonSelected);
        addPressEffect(buttonLikeFacebook);
        buttonLikeFacebook.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!Settings.didLikeFacebook) {

                    Settings.didLikeFacebook = true;
                    game.stage.addAction(Actions.sequence(Actions.delay(1),
                            Actions.run(new Runnable() {

                                @Override
                                public void run() {
                                    Settings.coinsTotal += coinsLikeFacebook;
                                    buttonLikeFacebook.setText("Visit us");
                                    buttonLikeFacebook
                                            .setStyle(Assets.styleTextButtonSelected);
                                }
                            })));
                }
                game.reqHandler.showFacebook();
            }
        });

        buttonBuy50MillionCoins = new TextButton("Buy", Assets.styleTextButtonBuy);
        addPressEffect(buttonBuy50MillionCoins);
        buttonBuy50MillionCoins.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.reqHandler.buy50millionCoins();
            }
        });

        // Facebook Like
        container.add(new Image(Assets.horizontalSeparator)).expandX().fill()
                .height(5);
        container.row();
        container
                .add(addCharacterTable(coinsLikeFacebook,
                        Assets.buttonFacebook, "Like us on facebook and get "
                                + coinsLikeFacebook + " coins",
                        buttonLikeFacebook)).expandX().fill();
        container.row();

        TextureRegionDrawable drawableCoinFront = new TextureRegionDrawable(Assets.coinFront);

        container
                .add(addCharacterTable(
                        50000,
                        drawableCoinFront,
                        "Coin super mega pack. Get this pack and you will be racing in cash",
                        buttonBuy50MillionCoins)).expandX().fill();
        container.row();
    }

    private Table addCharacterTable(int numberOfCoinsToGet,
                                    TextureRegionDrawable imagen, String descripcion, TextButton boton) {

        Image imageCoinFront = new Image(Assets.coinFront);
        Image imageCharacter = new Image(imagen);

        Table tableTitleBar = new Table();
        tableTitleBar
                .add(new Label("Get " + numberOfCoinsToGet, Assets.labelStyleSmall))
                .left().padLeft(5);
        tableTitleBar.add(imageCoinFront).left().expandX().padLeft(5);

        Table tableDescription = new Table();
        tableDescription.add(imageCharacter).left().pad(10).size(55, 55);
        Label labelDescription = new Label(descripcion, Assets.labelStyleSmall);
        labelDescription.setWrap(true);
        tableDescription.add(labelDescription).expand().fill().padLeft(5);

        Table tableContent = new Table();
        tableContent.add(tableTitleBar).expandX().fill().colspan(2).padTop(8);
        tableContent.row().colspan(2);
        tableContent.add(tableDescription).expandX().fill();
        tableContent.row().colspan(2);

        tableContent.add(boton).right().padRight(10).size(120, 45);

        tableContent.row().colspan(2);
        tableContent.add(new Image(Assets.horizontalSeparator)).expandX().fill()
                .height(5).padTop(15);

        return tableContent;

    }

    protected void addPressEffect(final Actor actor) {
        actor.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                actor.setPosition(actor.getX(), actor.getY() - 3);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                actor.setPosition(actor.getX(), actor.getY() + 3);
            }
        });
    }
}
