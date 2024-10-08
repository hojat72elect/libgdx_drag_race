package com.nopalsoft.dragracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {

    public static BitmapFont fontLarge;
    public static BitmapFont fontSmall;

    public static Animation<TextureRegion> newExplosion;

    public static NinePatchDrawable pixelBlack;
    public static TextureRegionDrawable scoresBackground;

    public static NinePatchDrawable horizontalSeparator;
    public static NinePatchDrawable verticalSeparator;

    public static AtlasRegion street;
    public static AtlasRegion coin;
    public static AtlasRegion coinFront;

    public static TextureRegionDrawable title;
    public static TextureRegionDrawable buttonBack;
    public static TextureRegionDrawable buttonNoAds;
    public static TextureRegionDrawable buttonFacebook;
    public static TextureRegionDrawable buttonTwitter;
    public static TextureRegionDrawable upgradeOn;
    public static TextureRegionDrawable upgradeOff;
    public static TextureRegionDrawable swipeHand;
    public static TextureRegionDrawable swipeHandDown;
    public static TextureRegionDrawable swipeArrows;

    // All cars we have in this game.
    public static AtlasRegion carDevil;
    public static AtlasRegion carBanshee;
    public static AtlasRegion carTourism;
    public static AtlasRegion carBullet;
    public static AtlasRegion carTornado;
    public static AtlasRegion carAudiS5;
    public static AtlasRegion carBmwX6;
    public static AtlasRegion carChevroletCrossfire;
    public static AtlasRegion carCitroenC4;
    public static AtlasRegion carDodgeCharger;
    public static AtlasRegion carFiat500Lounge;
    public static AtlasRegion carHondaCRV;
    public static AtlasRegion carMazda6;
    public static AtlasRegion carMazdaRX8;
    public static AtlasRegion carSeatIbiza;
    public static AtlasRegion carVolkswagenScirocco;

    // All kinds of styles used in this game
    public static LabelStyle labelStyleLarge;
    public static LabelStyle labelStyleSmall;
    public static ScrollPaneStyle styleScrollPane;
    public static TextButtonStyle styleTextButtonBuy;
    public static TextButtonStyle styleTextButtonPurchased;
    public static TextButtonStyle styleTextButtonSelected;
    public static ButtonStyle styleButtonMusic;

    public static AtlasRegion barMarkedRed;
    public static AtlasRegion barMarkedGreen;

    // Sounds and music
    public static Sound soundTurn1;
    public static Sound soundTurn2;
    public static Sound soundCrash;
    public static Music music;

    static TextureAtlas atlas;

    private static void loadFonts() {
        fontLarge = new BitmapFont(
                Gdx.files.internal("data/font.fnt"),
                atlas.findRegion("font")
        );

        fontSmall = new BitmapFont(
                Gdx.files.internal("data/fontChico.fnt"),
                atlas.findRegion("fontChico")
        );

    }

    private static void loadStyles() {
        labelStyleLarge = new LabelStyle(fontLarge, Color.WHITE);
        labelStyleSmall = new LabelStyle(fontSmall, Color.WHITE);

        horizontalSeparator = new NinePatchDrawable(
                new NinePatch(
                        atlas.findRegion("Shop/separadorHorizontal"),
                        0,
                        1,
                        0,
                        0
                )
        );
        verticalSeparator = new NinePatchDrawable(
                new NinePatch(
                        atlas.findRegion("Shop/separadorVertical"),
                        0,
                        1,
                        0,
                        0
                )
        );

        // Button Buy
        TextureRegionDrawable buttonBuy = new TextureRegionDrawable(atlas.findRegion("Shop/btBuy"));
        styleTextButtonBuy = new TextButtonStyle(buttonBuy, null, null, fontSmall);

        // Button Purchase
        TextureRegionDrawable buttonPurchase = new TextureRegionDrawable(atlas.findRegion("Shop/btPurchased"));
        styleTextButtonPurchased = new TextButtonStyle(buttonPurchase, null, null, fontSmall);

        // Button Selected
        TextureRegionDrawable buttonSelected = new TextureRegionDrawable(atlas.findRegion("Shop/btSelected"));
        styleTextButtonSelected = new TextButtonStyle(buttonSelected, null, null, fontSmall);

        styleScrollPane = new ScrollPaneStyle(null, null, null, null, verticalSeparator);

        // Buttons for turning music on or off
        TextureRegionDrawable buttonMusicOn = new TextureRegionDrawable(atlas.findRegion("MenuPrincipal/btMusica"));
        TextureRegionDrawable buttonMusicOff = new TextureRegionDrawable(atlas.findRegion("MenuPrincipal/btSinMusica"));
        styleButtonMusic = new ButtonStyle(buttonMusicOn, null, buttonMusicOff);
    }

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("data/atlasMap.txt"));
        loadFonts();
        loadStyles();

        title = new TextureRegionDrawable(atlas.findRegion("titulo2"));

        pixelBlack = new NinePatchDrawable(new NinePatch(atlas.findRegion("pixelNegro"), 1, 1, 0, 0));
        scoresBackground = new TextureRegionDrawable(atlas.findRegion("fondoPuntuaciones"));

        coin = atlas.findRegion("coin");
        coinFront = atlas.findRegion("coinFrente");

        barMarkedRed = atlas.findRegion("barraMarcadorRojo");
        barMarkedGreen = atlas.findRegion("barraMarcadorVerde");

        street = atlas.findRegion("calle");
        carDevil = atlas.findRegion("Carros/diablo");
        carBanshee = atlas.findRegion("Carros/banshee");
        carTourism = atlas.findRegion("Carros/turismo");
        carBullet = atlas.findRegion("Carros/bullet");
        carTornado = atlas.findRegion("Carros/tornado");
        carAudiS5 = atlas.findRegion("Carros/Audi S5");
        carBmwX6 = atlas.findRegion("Carros/BMW X6");
        carChevroletCrossfire = atlas.findRegion("Carros/Chevrolet Crossfire");
        carCitroenC4 = atlas.findRegion("Carros/Citroen C4");
        carDodgeCharger = atlas.findRegion("Carros/Dodge Charger");
        carFiat500Lounge = atlas.findRegion("Carros/Fiat 500 Lounge");
        carHondaCRV = atlas.findRegion("Carros/Honda CRV");
        carMazda6 = atlas.findRegion("Carros/Mazda 6");
        carMazdaRX8 = atlas.findRegion("Carros/Mazda RX8");
        carSeatIbiza = atlas.findRegion("Carros/Seat Ibiza");
        carVolkswagenScirocco = atlas.findRegion("Carros/Volkswagen Scirocco");

        // The explosion animation
        AtlasRegion newExplosion1 = atlas.findRegion("Animaciones/newExplosion1");
        AtlasRegion newExplosion2 = atlas.findRegion("Animaciones/newExplosion2");
        AtlasRegion newExplosion3 = atlas.findRegion("Animaciones/newExplosion3");
        AtlasRegion newExplosion4 = atlas.findRegion("Animaciones/newExplosion4");
        AtlasRegion newExplosion5 = atlas.findRegion("Animaciones/newExplosion5");
        AtlasRegion newExplosion6 = atlas.findRegion("Animaciones/newExplosion6");
        AtlasRegion newExplosion7 = atlas.findRegion("Animaciones/newExplosion7");
        AtlasRegion newExplosion8 = atlas.findRegion("Animaciones/newExplosion8");
        AtlasRegion newExplosion9 = atlas.findRegion("Animaciones/newExplosion9");
        AtlasRegion newExplosion10 = atlas.findRegion("Animaciones/newExplosion10");
        AtlasRegion newExplosion11 = atlas.findRegion("Animaciones/newExplosion11");
        AtlasRegion newExplosion12 = atlas.findRegion("Animaciones/newExplosion12");
        AtlasRegion newExplosion13 = atlas.findRegion("Animaciones/newExplosion13");
        AtlasRegion newExplosion14 = atlas.findRegion("Animaciones/newExplosion14");
        AtlasRegion newExplosion15 = atlas.findRegion("Animaciones/newExplosion15");
        AtlasRegion newExplosion16 = atlas.findRegion("Animaciones/newExplosion16");
        AtlasRegion newExplosion17 = atlas.findRegion("Animaciones/newExplosion17");
        AtlasRegion newExplosion18 = atlas.findRegion("Animaciones/newExplosion18");
        AtlasRegion newExplosion19 = atlas.findRegion("Animaciones/newExplosion19");

        newExplosion = new Animation(0.05f, newExplosion1, newExplosion2, newExplosion3,
                newExplosion4, newExplosion5, newExplosion6, newExplosion7, newExplosion8, newExplosion9,
                newExplosion10, newExplosion11, newExplosion12, newExplosion13, newExplosion14,
                newExplosion15, newExplosion16, newExplosion17, newExplosion18, newExplosion19);

        // All the buttons
        buttonBack = new TextureRegionDrawable(atlas.findRegion("Shop/btAtras2"));
        buttonNoAds = new TextureRegionDrawable(atlas.findRegion("Shop/btNoAds"));
        upgradeOff = new TextureRegionDrawable(atlas.findRegion("Shop/upgradeOff"));
        upgradeOn = new TextureRegionDrawable(atlas.findRegion("Shop/upgradeOn"));
        buttonFacebook = new TextureRegionDrawable(atlas.findRegion("MenuPrincipal/btFacebook"));
        buttonTwitter = new TextureRegionDrawable(atlas.findRegion("MenuPrincipal/btTwitter"));

        // swipe drawables (we use the as a HUD)
        swipeHand = new TextureRegionDrawable(atlas.findRegion("swipeHand"));
        swipeHandDown = new TextureRegionDrawable(atlas.findRegion("swipeHandDown"));
        swipeArrows = new TextureRegionDrawable(atlas.findRegion("swipeArrows"));

        // Sounds and musics
        soundTurn1 = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/turn1.mp3"));
        soundTurn2 = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/turn2.mp3"));
        soundCrash = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/crash.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("data/Sounds/DST-BreakOut.mp3"));
        music.setLooping(true);

        Settings.load();

        if (Settings.isMusicOn)
            music.play();

    }

    public static void playSound(Sound sound) {
        if (Settings.isMusicOn) {
            sound.play(1);
        }
    }
}
