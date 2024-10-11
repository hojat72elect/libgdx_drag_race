package com.nopalsoft.dragracer

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.nopalsoft.dragracer.handlers.GameServicesHandler
import com.nopalsoft.dragracer.handlers.RequestHandler
import com.nopalsoft.dragracer.screens.MainMenuScreen
import com.nopalsoft.dragracer.screens.Screens

class MainStreet( val reqHandler: RequestHandler,  val gameServiceHandler: GameServicesHandler):Game() {

    lateinit var stage: Stage
lateinit var batcher: SpriteBatch


    override fun create() {

        stage =
            Stage(StretchViewport(Screens.SCREEN_WIDTH.toFloat(), Screens.SCREEN_HEIGHT.toFloat()))
        batcher = SpriteBatch()

        Assets.load()

        if (Settings.didBuyNoAds) reqHandler.removeAds()
        setScreen(MainMenuScreen(this))
    }
}