package com.nopalsoft.dragracer.handlers

interface RequestHandler {
    fun showRater()

    fun showInterstitial()

    fun showFacebook()

    fun shareOnFacebook(message: String?)

    fun shareOnTwitter(message: String?)

    fun removeAds()

    fun showAdBanner()

    fun hideAdBanner()

    fun buy50millionCoins()
}
