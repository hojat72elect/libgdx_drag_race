package com.nopalsoft.dragracer.handlers;

public interface RequestHandler {
    void showRater();

    void showInterstitial();

    void showFacebook();

    void shareOnFacebook(final String message);

    void shareOnTwitter(final String message);

    void removeAds();

    void showAdBanner();

    void hideAdBanner();

    void buy50millionCoins();

}
