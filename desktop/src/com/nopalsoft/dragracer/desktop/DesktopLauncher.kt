package com.nopalsoft.dragracer.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.nopalsoft.dragracer.MainStreet
import com.nopalsoft.dragracer.Settings
import com.nopalsoft.dragracer.handlers.GoogleGameServicesHandler
import com.nopalsoft.dragracer.handlers.RequestHandler

/**
 * warning: Don't remove args from this function.
 */
fun main(args: Array<String>) {
    val reqHandler = object : RequestHandler {
        override fun showRater() {
            // TODO Auto-generated method stub
        }

        override fun showInterstitial() {
            // TODO Auto-generated method stub
        }

        override fun showFacebook() {
            // TODO Auto-generated method stub
        }

        override fun showAdBanner() {
            // TODO Auto-generated method stub
        }

        override fun shareOnTwitter(message: String) {
            // TODO Auto-generated method stub
        }

        override fun shareOnFacebook(message: String) {
            // TODO Auto-generated method stub
        }

        override fun removeAds() {
            // TODO Auto-generated method stub
        }

        override fun hideAdBanner() {
            // TODO Auto-generated method stub
        }

        override fun buy50millionCoins() {
            Settings.coinsTotal += 50000
        }
    }
    val gameHandler = object : GoogleGameServicesHandler {

        override fun submitScore(score: Long) {
            // TODO Auto-generated method stub
        }

        override fun signIn() {
            // TODO Auto-generated method stub
        }

        override fun isSignedIn(): Boolean {
            // TODO Auto-generated method stub
            return false
        }

        override fun getLeaderboard() {
            // TODO Auto-generated method stub
        }
    }

    val config = LwjglApplicationConfiguration()
    with(config) {
        title = "StreetSwipinRace"
        width = 480
        height = 800
    }

    LwjglApplication(MainStreet(reqHandler, gameHandler), config)
}


