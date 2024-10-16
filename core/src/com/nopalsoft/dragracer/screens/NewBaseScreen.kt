package com.nopalsoft.dragracer.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.input.GestureDetector.GestureListener
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.nopalsoft.dragracer.Assets
import com.nopalsoft.dragracer.MainStreet
import com.nopalsoft.dragracer.Settings
import com.nopalsoft.dragracer.game.GameScreen
import com.nopalsoft.dragracer.shop.ShopScreen
import kotlin.math.abs

/**
 * This class was supposed to replace BaseScreen.java but I keep facing a weird runtime issue when I use this class.
 */
abstract class NewBaseScreen(game: MainStreet) : InputAdapter(), Screen, GestureListener {

    private val camera = OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT)
    private val batcher = game.batcher
    private val stage = game.stage

    private lateinit var blackFadeOut: Image


    init {
        stage.clear()
        camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0F)
        val detector = GestureDetector(20F, .5F, 2F, .15F, this)
        val input = InputMultiplexer(this, detector, stage)
        Gdx.input.inputProcessor = input
    }

    override fun render(delta: Float) {
        val optimizedDelta = if (delta > .1F) .1F else delta

        update(optimizedDelta)

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        batcher.projectionMatrix = camera.combined
        draw(optimizedDelta)

        stage.act(optimizedDelta)
        stage.draw()
    }

    fun changeScreenWithFadeOut(
        newScreen: Class<*>,
        game: MainStreet
    ) {
        blackFadeOut = Image(Assets.pixelBlack)
        blackFadeOut.setSize(SCREEN_WIDTH, SCREEN_HEIGHT)
        blackFadeOut.color.a = 0f
        blackFadeOut.addAction(
            Actions.sequence(
                Actions.fadeIn(.5f),
                Actions.run {
                    if (newScreen == MainMenuScreen::class.java) game.screen = MainMenuScreen(game)
                    else if (newScreen == GameScreen::class.java) game.screen = GameScreen(game)
                    else if (newScreen == ShopScreen::class.java) game.screen = ShopScreen(game)
                })
        )
        stage.addActor(blackFadeOut)
    }

    fun addPressEffect(actor: Actor) {
        actor.addListener(object : InputListener() {
            override fun touchDown(
                event: InputEvent, x: Float, y: Float,
                pointer: Int, button: Int
            ): Boolean {
                actor.setPosition(actor.x, actor.y - 3)
                event.stop()
                return true
            }

            override fun touchUp(
                event: InputEvent, x: Float, y: Float,
                pointer: Int, button: Int
            ) {
                actor.setPosition(actor.x, actor.y + 3)
            }
        })
    }

    abstract fun draw(delta: Float)
    abstract fun update(delta: Float)

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun show() {}

    override fun hide() {
        Settings.save()
    }

    override fun pause() {
        Assets.music.pause()
    }

    override fun resume() {
        if (Settings.isMusicOn) Assets.music.play()
    }

    override fun dispose() {
        stage.dispose()
        batcher.dispose()
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int) = false
    override fun tap(x: Float, y: Float, count: Int, button: Int) = false
    override fun longPress(x: Float, y: Float) = false

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        if (abs(velocityX) > abs(velocityY)) {
            if (velocityX > 0) {
                right()
            } else {
                left()
            }
        } else {
            if (velocityY > 0) {
                down()
            } else {
                up()
            }
        }
        return false
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float) = false
    override fun panStop(x: Float, y: Float, pointer: Int, button: Int) = false
    override fun zoom(initialDistance: Float, distance: Float) = false

    override fun pinch(
        initialPointer1: Vector2,
        initialPointer2: Vector2,
        pointer1: Vector2,
        pointer2: Vector2
    ) = false

    override fun pinchStop() {}

    fun up() {}

    fun down() {}

    fun left() {}

    fun right() {}

    protected fun entranceAction(act: Actor, y: Float, duration: Float) {
        act.addAction(
            Actions.moveTo(
                SCREEN_WIDTH / 2f - act.width / 2f,
                y, duration, Interpolation.exp10
            )
        )
    }

    fun setAnimationChangeColor(actor: Actor) {
        actor.addListener(object : InputListener() {
            override fun enter(
                event: InputEvent, x: Float, y: Float, pointer: Int,
                fromActor: Actor
            ) {
                actor.color = Color.RED
            }

            override fun exit(
                event: InputEvent, x: Float, y: Float, pointer: Int,
                toActor: Actor
            ) {
                actor.color = Color.WHITE
            }
        })
    }


    companion object {
        const val SCREEN_WIDTH = 480F
        const val SCREEN_HEIGHT = 800F
        const val WORLD_WIDTH = 480F
        const val WORLD_HEIGHT = 800F
    }
}