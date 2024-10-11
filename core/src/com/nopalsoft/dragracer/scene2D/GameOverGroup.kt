package com.nopalsoft.dragracer.scene2D;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.Settings;
import com.nopalsoft.dragracer.screens.Screens;

public class GameOverGroup extends Group {

    Screens screen;

    public GameOverGroup(final Screens screen, int distancia, int coins) {
        this.screen = screen;
        setSize(420, 350);
        setPosition(Screens.SCREEN_WIDTH / 2f - getWidth() / 2f, 900);
        addAction(Actions.moveTo(getX(), 390, 1, Interpolation.bounceOut));
        Image background = new Image(Assets.scoresBackground);
        background.setSize(getWidth(), getHeight());
        addActor(background);

        Label labelScore = new Label("Distance\n" + distancia + "m", Assets.labelStyleLarge);
        labelScore.setAlignment(Align.center);
        labelScore.setFontScale(1.3f);
        labelScore.setPosition(getWidth() / 2f - labelScore.getWidth() / 2f, 210);
        addActor(labelScore);

        Table bestScoreTable = new Table();
        bestScoreTable.setSize(getWidth(), 110);
        bestScoreTable.setY(80);
        bestScoreTable.padLeft(15).padRight(15);


        Label labelBestScore = new Label("Best distance", Assets.labelStyleLarge);
        labelBestScore.setAlignment(Align.left);
        labelBestScore.setFontScale(.75f);

        Label labelNumBestScore = new Label(Settings.bestScore + "m", Assets.labelStyleLarge);
        labelNumBestScore.setAlignment(Align.right);
        labelNumBestScore.setFontScale(.75f);

        Label labelCoins = new Label("Coins", Assets.labelStyleLarge);
        labelCoins.setAlignment(Align.left);
        labelCoins.setFontScale(.75f);

        Label labelNumBestCoins = new Label(coins + "", Assets.labelStyleLarge);
        labelNumBestCoins.setAlignment(Align.right);
        labelNumBestCoins.setFontScale(.75f);

        bestScoreTable.add(labelBestScore).left();
        bestScoreTable.add(labelNumBestScore).right().expand();
        bestScoreTable.row();
        bestScoreTable.add(labelCoins).left();
        bestScoreTable.add(labelNumBestCoins).right().expand();

        // Facebook + Twitter
        Button buttonShareFacebook, buttonShareTwitter;

        buttonShareTwitter = new Button(new TextureRegionDrawable(Assets.buttonTwitter));
        buttonShareTwitter.setSize(50, 50);
        buttonShareTwitter.setPosition(155, 20);
        screen.addPressEffect(buttonShareTwitter);
        buttonShareTwitter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.reqHandler
                        .shareOnTwitter("My best distance playing Drag Racing V6 is "
                                + Settings.bestScore + "m, can you beat me?");
            }
        });

        buttonShareFacebook = new Button(new TextureRegionDrawable(Assets.buttonFacebook));
        buttonShareFacebook.setSize(50, 50);
        buttonShareFacebook.setPosition(225, 20);
        screen.addPressEffect(buttonShareFacebook);
        buttonShareFacebook.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.game.reqHandler
                        .shareOnFacebook("My best distance playing Drag Racing V6 is "
                                + Settings.bestScore + "m, can you beat me?");
            }
        });

        addActor(bestScoreTable);
        addActor(buttonShareTwitter);
        addActor(buttonShareFacebook);


    }
}
