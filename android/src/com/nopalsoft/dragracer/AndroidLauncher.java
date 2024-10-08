package com.nopalsoft.dragracer;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.nopalsoft.dragracer.handlers.GameServicesHandler;
import com.nopalsoft.dragracer.handlers.RequestHandler;

public class AndroidLauncher extends AndroidApplication implements GameServicesHandler, RequestHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new MainStreet(this, this), config);
    }

    @Override
    public void submitScore(long score) {

    }

    @Override
    public void getLeaderboard() {

    }

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public void signIn() {

    }

    @Override
    public void showRater() {

    }

    @Override
    public void showInterstitial() {

    }

    @Override
    public void showFacebook() {

    }

    @Override
    public void shareOnFacebook(String message) {

    }

    @Override
    public void shareOnTwitter(String message) {

    }

    @Override
    public void removeAds() {

    }

    @Override
    public void showAdBanner() {

    }

    @Override
    public void hideAdBanner() {

    }

    @Override
    public void buy50millionCoins() {

    }
}
