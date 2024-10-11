package com.nopalsoft.dragracer.game

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Array
import com.nopalsoft.dragracer.Assets
import com.nopalsoft.dragracer.Assets.playSound
import com.nopalsoft.dragracer.game_objects.Coin
import com.nopalsoft.dragracer.game_objects.EnemyCar
import com.nopalsoft.dragracer.game_objects.InfiniteScrollBackground
import com.nopalsoft.dragracer.game_objects.PlayerCar
import com.nopalsoft.dragracer.screens.Screens

class TrafficGame : Table() {
    val lane2: Float = 390f
    val lane1: Float = 240f
    val lane0: Float = 90f

    val _width: Float = Screens.WORLD_WIDTH
    val _height: Float = Screens.WORLD_HEIGHT

    val TIME_TO_SPAWN_CAR: Float = 2f
    val TIME_TO_SPAWN_COIN: Float = 1f
    val DURATION_SUPER_SPEED: Float = 5f

    private val backgroundRoad: InfiniteScrollBackground
    private val arrayEnemyCars: Array<EnemyCar>
    private val arrCoins: Array<Coin>
    var state: Int
    var numberOfCoinsForSuperSpeed: Int = 0
    var playerCar: PlayerCar
    var canSuperSpeed: Boolean = false
    var timeToSpawnCar: Float = 0f
    var timeToSpawnCoin: Float = 0f
    var durationSuperSpeed: Float = 0f
    var isSuperSpeed: Boolean = false
    var currentSpeed: Float = 5f
    var score: Float = 0f
    var coins: Int = 0

    init {
        setBounds(0f, 0f, _width, _height)
        clip = true
        backgroundRoad = InfiniteScrollBackground(getWidth(), getHeight())
        addActor(backgroundRoad)

        playerCar = PlayerCar(this)
        addActor(playerCar)
        arrayEnemyCars = Array()
        arrCoins = Array()

        state = STATE_RUNNING
    }

    override fun act(delta: Float) {
        super.act(delta)

        durationSuperSpeed += delta
        if (durationSuperSpeed >= DURATION_SUPER_SPEED) {
            stopSuperSpeed()
        }

        if (numberOfCoinsForSuperSpeed >= NUM_COINS_FOR_SUPER_SPEED) {
            canSuperSpeed = true
        }


        updateEnemyCar(delta)
        updateMonedas(delta)
        score += delta * currentSpeed

        if (playerCar.state == PlayerCar.STATE_DEAD) {
            state = STATE_GAMEOVER
        }
    }

    private fun updateEnemyCar(delta: Float) {
        // I first create a car if necessary.

        timeToSpawnCar += delta
        if (timeToSpawnCar >= TIME_TO_SPAWN_CAR) {
            timeToSpawnCar -= TIME_TO_SPAWN_CAR
            spawnCar()
        }

        var iteratorEnemyCar: MutableIterator<EnemyCar> = arrayEnemyCars.iterator()
        while (iteratorEnemyCar.hasNext()) {
            val enemyCar = iteratorEnemyCar.next()
            if (enemyCar.bounds.y + enemyCar.height <= 0) {
                iteratorEnemyCar.remove()
                removeActor(enemyCar)
                continue
            }

            if (isSuperSpeed) enemyCar.setSpeed()
        }

        // Then I check the collisions with the player
        iteratorEnemyCar = arrayEnemyCars.iterator()
        while (iteratorEnemyCar.hasNext()) {
            val enemyCar = iteratorEnemyCar.next()
            if (enemyCar.bounds.overlaps(playerCar.bounds)) {
                iteratorEnemyCar.remove()

                if (enemyCar.x > playerCar.x) {
                    enemyCar.crash(true, enemyCar.y > playerCar.y)
                    if (!isSuperSpeed) playerCar.crash(false, true)
                } else {
                    enemyCar.crash(false, enemyCar.y > playerCar.y)
                    if (!isSuperSpeed) playerCar.crash(true, true)
                }
                Assets.soundCrash.stop()
                playSound(Assets.soundCrash)
            }
        }
    }

    private fun updateMonedas(delta: Float) {
        timeToSpawnCoin += delta

        if (timeToSpawnCoin >= TIME_TO_SPAWN_COIN) {
            timeToSpawnCoin -= TIME_TO_SPAWN_COIN
            spawnCoin()
        }

        val iterator: MutableIterator<Coin> = arrCoins.iterator()
        while (iterator.hasNext()) {
            val obj = iterator.next()
            if (obj.bounds.y + obj.height <= 0) {
                iterator.remove()
                removeActor(obj)
                continue
            }
            // I see if they are touching my car
            if (playerCar.bounds.overlaps(obj.bounds)) {
                iterator.remove()
                removeActor(obj)
                coins++
                numberOfCoinsForSuperSpeed++
                continue
            }

            // I see if it's touching an enemy
            for (objEnemy in arrayEnemyCars) {
                if (obj.bounds.overlaps(objEnemy.bounds)) {
                    iterator.remove()
                    removeActor(obj)
                    break
                }
            }

            if (isSuperSpeed) obj.setSpeed()
        }
    }

    fun setSuperSpeed() {
        canSuperSpeed = false
        durationSuperSpeed = 0f
        isSuperSpeed = true
        currentSpeed = 30f
        numberOfCoinsForSuperSpeed = 0
        backgroundRoad.setSpeed()
    }

    fun stopSuperSpeed() {
        isSuperSpeed = false
        currentSpeed = 5f
        backgroundRoad.stopSpeed()
    }

    private fun spawnCar() {
        val lane = MathUtils.random(0, 2)
        var x = 0f
        if (lane == 0) x = lane0
        if (lane == 1) x = lane1
        if (lane == 2) x = lane2
        val enemyCar = EnemyCar(x, getHeight())
        arrayEnemyCars.add(enemyCar)
        addActor(enemyCar)
    }

    private fun spawnCoin() {
        val lane = MathUtils.random(0, 2)
        var x = 0f
        if (lane == 0) x = lane0
        if (lane == 1) x = lane1
        if (lane == 2) x = lane2
        val obj = Coin(x, getHeight())
        arrCoins.add(obj)
        addActor(obj)
    }

    companion object {
        const val STATE_RUNNING: Int = 0
        const val STATE_GAMEOVER: Int = 1

        const val NUM_COINS_FOR_SUPER_SPEED: Int = 10
    }
}
