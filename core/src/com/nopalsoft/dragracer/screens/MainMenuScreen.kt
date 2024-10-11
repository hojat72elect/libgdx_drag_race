package com.nopalsoft.dragracer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.MainStreet;
import com.nopalsoft.dragracer.Settings;
import com.nopalsoft.dragracer.game.GameScreen;
import com.nopalsoft.dragracer.shop.ShopScreen;

public class MainMenuScreen extends Screens {

    Image imageTitle;

    Label labelShopScreen;
    Label labelPlay;
    Label labelLeaderboard;
    Label labelRate;

    Button buttonMusic;

    public MainMenuScreen(final MainStreet game) {
        super(game);

        imageTitle = new Image(Assets.title);
        imageTitle.setPosition(SCREEN_WIDTH / 2f - imageTitle.getWidth() / 2f, 520);
        imageTitle.getColor().a = 0;
        imageTitle.addAction(Actions.sequence(Actions.fadeIn(.5f),
                Actions.run(new Runnable() {

                    @Override
                    public void run() {
                        stage.addActor(labelPlay);
                        stage.addActor(labelRate);
                        stage.addActor(labelLeaderboard);
                        stage.addActor(labelShopScreen);
                        stage.addActor(buttonMusic);
                    }
                })));

        labelPlay = new Label("Play", Assets.labelStyleLarge);
        labelPlay.setPosition(500, 440);
        labelPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(GameScreen.class, game);
            }
        });

        labelRate = new Label("Rate", Assets.labelStyleLarge);
        labelRate.setPosition(500, 340);
        labelRate.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.reqHandler.showRater();
            }
        });

        labelShopScreen = new Label("Shop screen", Assets.labelStyleLarge);
        labelShopScreen.setPosition(500, 240);
        labelShopScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(ShopScreen.class, game);
            }
        });

        labelLeaderboard = new Label("Leaderboard", Assets.labelStyleLarge);
        labelLeaderboard.setPosition(500, 140);
        labelLeaderboard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.gameServiceHandler.isSignedIn())
                    game.gameServiceHandler.getLeaderboard();
                else
                    game.gameServiceHandler.signIn();
            }
        });

        buttonMusic = new Button(Assets.styleButtonMusic);
        buttonMusic.setPosition(5, 5);
        buttonMusic.setChecked(!Settings.isMusicOn);
        Gdx.app.log("Musica", Settings.isMusicOn + "");
        buttonMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.isMusicOn = !Settings.isMusicOn;
                buttonMusic.setChecked(!Settings.isMusicOn);
                if (Settings.isMusicOn)
                    Assets.music.play();
                else
                    Assets.music.stop();
                super.clicked(event, x, y);
            }
        });

        entranceAction(labelPlay, labelPlay.getY(), .25f);
        entranceAction(labelRate, labelRate.getY(), .5f);
        entranceAction(labelShopScreen, labelShopScreen.getY(), .75f);
        entranceAction(labelLeaderboard, labelLeaderboard.getY(), 1f);

        setAnimationChangeColor(labelShopScreen);
        setAnimationChangeColor(labelRate);
        setAnimationChangeColor(labelLeaderboard);
        setAnimationChangeColor(labelPlay);

        stage.addActor(imageTitle);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        batcher.begin();
        batcher.draw(Assets.street, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT * 2);
        batcher.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE || keycode == Keys.BACK)
            Gdx.app.exit();
        return true;

    }

}
