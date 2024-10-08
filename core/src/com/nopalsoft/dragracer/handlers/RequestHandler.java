package com.nopalsoft.dragracer.handlers;

public interface RequestHandler {
    void showRater();

    void showInterstitial();

    void showFacebook();

    void showMoreGames();

    void shareOnFacebook(final String mensaje);

    void shareOnTwitter(final String mensaje);

    void removeAds();

    void showAdBanner();

    void hideAdBanner();

    void buy50milCoins();

}
