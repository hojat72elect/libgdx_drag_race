package com.nopalsoft.dragracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    final public static int TIMES_TO_SHOW_AD = 5;
    private final static Preferences pref = Gdx.app.getPreferences("com.tiar.dragrace.shop");
    public static boolean drawDebugLines = false;
    public static int numberOfTimesPlayed = 0;
    public static int bestScore = 0;
    public static int coinsTotal = 0;
    public static boolean didBuyNoAds;
    public static boolean didLikeFacebook;
    public static boolean isMusicOn = true;
    public static int selectedSkin = com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_DEVIL;

    public static void load() {
        numberOfTimesPlayed = pref.getInteger("numeroVecesJugadas");
        bestScore = pref.getInteger("bestScore");
        coinsTotal = pref.getInteger("coinsTotal");
        selectedSkin = pref.getInteger("skinSeleccionada");

        didBuyNoAds = pref.getBoolean("didBuyNoAds");
        didLikeFacebook = pref.getBoolean("didLikeFacebook");
        isMusicOn = pref.getBoolean("isMusicOn", true);

    }

    public static void save() {
        pref.putInteger("numeroVecesJugadas", numberOfTimesPlayed);
        pref.putInteger("bestScore", bestScore);
        pref.putInteger("coinsTotal", coinsTotal);
        pref.putInteger("skinSeleccionada", selectedSkin);

        pref.putBoolean("didBuyNoAds", didBuyNoAds);
        pref.putBoolean("didLikeFacebook", didLikeFacebook);
        pref.putBoolean("isMusicOn", isMusicOn);
        pref.flush();

    }


    public static void setNewScore(int score) {
        if (bestScore < score)
            bestScore = score;
        save();
    }

}