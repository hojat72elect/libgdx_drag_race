package com.nopalsoft.dragracer.game_objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.nopalsoft.dragracer.Assets
import com.nopalsoft.dragracer.Settings
import com.nopalsoft.dragracer.shop.CharactersSubMenu

class EnemyCar(x: Float, y: Float) : Actor() {
    var type: Int = MathUtils.random(16)
    var isSuperSpeed: Boolean = false
    lateinit var keyframe: TextureRegion
    var renders: ShapeRenderer = ShapeRenderer()

    @JvmField
    val bounds: Rectangle = Rectangle()
    private val moveAction: MoveToAction

    init {
        val width: Float
        val height: Float

        when (type) {
            CharactersSubMenu.SKIN_CAR_DEVIL -> {
                keyframe = Assets.carDevil
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_BANSHEE -> {
                keyframe = Assets.carBanshee
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_TORNADO -> {
                keyframe = Assets.carTornado
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_TURISM -> {
                keyframe = Assets.carTourism
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_AUDI_S5 -> {
                keyframe = Assets.carAudiS5
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_BMW_X6 -> {
                keyframe = Assets.carBmwX6
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_BULLET -> {
                keyframe = Assets.carBullet
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_CHEVROLET_CROSSFIRE -> {
                keyframe = Assets.carChevroletCrossfire
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_CITROEN_C4 -> {
                keyframe = Assets.carCitroenC4
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_DODGE_CHARGER -> {
                keyframe = Assets.carDodgeCharger
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_FIAT_500_LOUNGE -> {
                keyframe = Assets.carFiat500Lounge
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_HONDA_CRV -> {
                keyframe = Assets.carHondaCRV
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_MAZDA_6 -> {
                keyframe = Assets.carMazda6
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_MAZDA_RX8 -> {
                keyframe = Assets.carMazdaRX8
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_SEAT_IBIZA -> {
                keyframe = Assets.carSeatIbiza
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            CharactersSubMenu.SKIN_CAR_VOLKSWAGEN_SCIROCCO -> {
                keyframe = Assets.carVolkswagenScirocco
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }

            else -> {
                keyframe = Assets.carVolkswagenScirocco
                width = keyframe.getRegionWidth().toFloat()
                height = keyframe.getRegionHeight().toFloat()
            }
        }
        // I subtract less 5 so that the bounds are not so big: See draw method.
        setWidth(width - 20)
        setHeight(height - 20)
        setPosition(x - getWidth() / 2f, y)

        moveAction = MoveToAction()
        moveAction.setPosition(getX(), -getHeight())
        moveAction.duration = MathUtils.random(4.0f, 6.0f)


        addAction(moveAction)
    }

    override fun act(delta: Float) {
        super.act(delta)
        updateBounds()
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        // I add 20 more to the current bounds so that the draw is better.
        val drawWidth = width + 20
        val drawHeight = height + 20
        // I subtract 10 because it is half of the +20
        batch.draw(
            keyframe, x - 10, y - 10, drawWidth / 2f,
            drawHeight / 2f, drawWidth, drawHeight, 1f, 1f, rotation
        )

        if (Settings.drawDebugLines) {
            batch.end()
            renders.projectionMatrix = batch.projectionMatrix
            renders.begin(ShapeType.Line)
            renders.rect(bounds.x, bounds.y, bounds.width, bounds.height)
            renders.end()
            batch.begin()
        }
    }

    private fun updateBounds() {
        bounds[x, y, width] = height
    }

    fun setSpeed() {
        if (!isSuperSpeed) {
            isSuperSpeed = true
            moveAction.reset()
            moveAction.duration = 1f
            addAction(moveAction)
        }
    }

    fun crash(front: Boolean, above: Boolean) {
        clearActions()
        addAction(Actions.fadeOut(1f))

        if (front && above) addAction(-360f, 200f, 200f)

        if (front && !above) addAction(360f, 200f, -200f)

        if (!front && above) addAction(360f, -200f, 200f)

        if (!front && !above) addAction(-360f, -200f, -200f)
    }

    private fun addAction(rotation: Float, positionX: Float, positionY: Float) {
        addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.rotateBy(rotation, 1.5f),
                    Actions.moveBy(positionX, positionY, 1.5f)
                ), Actions.removeActor()
            )
        )
    }
}
