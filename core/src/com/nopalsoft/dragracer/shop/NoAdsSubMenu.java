package com.nopalsoft.dragracer.shop;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.MainStreet;
import com.nopalsoft.dragracer.Settings;

public class NoAdsSubMenu {

    int priceNoAds = 20000;

    TextButton buttonNoAds;
    Label labelNoAds;

    Table container;
    MainStreet game;

    public NoAdsSubMenu(final MainStreet game, Table container) {
        this.game = game;
        this.container = container;
        container.clear();

        if (!Settings.didBuyNoAds)
            labelNoAds = new Label(priceNoAds + "", Assets.labelStyleSmall);

        buttonNoAds = new TextButton("Buy", Assets.styleTextButtonBuy);
        if (Settings.didBuyNoAds)
            buttonNoAds.setVisible(false);
        addPressEffect(buttonNoAds);
        buttonNoAds.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Settings.coinsTotal >= priceNoAds) {
                    Settings.coinsTotal -= priceNoAds;
                    Settings.didBuyNoAds = true;
                    labelNoAds.setVisible(false);
                    buttonNoAds.setVisible(false);
                    game.reqHandler.removeAds();
                }
            }
        });

        // Upgrade boost time
        container.add(new Image(Assets.horizontalSeparator)).expandX().fill().height(5);
        container.row();
        container.add(addCharacterTable(labelNoAds,
                        Assets.buttonNoAds,
                        buttonNoAds))
                .expandX()
                .fill();
        container.row();

    }

    private Table addCharacterTable(
            Label labelPrice,
            TextureRegionDrawable images,
            TextButton button
    ) {

        Image imageCoinFront = new Image(Assets.coinFront);
        Image imageCharacter = new Image(images);

        if (labelPrice == null)
            imageCoinFront.setVisible(false);

        Table tableTitleBar = new Table();
        tableTitleBar.add(new Label("No more ads", Assets.labelStyleSmall)).expandX()
                .left().padLeft(5);
        tableTitleBar.add(imageCoinFront).right();
        tableTitleBar.add(labelPrice).right().padRight(10);

        Table tableDescription = new Table();
        tableDescription.add(imageCharacter).left().pad(10).size(55, 45);
        Label labelDescription = new Label("Buy it and no more ads will appear in the app", Assets.labelStyleSmall);
        labelDescription.setWrap(true);
        labelDescription.setFontScale(.85f);
        tableDescription.add(labelDescription).expand().fill().padLeft(5);

        Table tableContent = new Table();
        tableContent.add(tableTitleBar).expandX().fill().colspan(2).padTop(8);
        tableContent.row().colspan(2);
        tableContent.add(tableDescription).expandX().fill();
        tableContent.row().colspan(2);

        tableContent.add(button).right().padRight(10).size(120, 45);

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
