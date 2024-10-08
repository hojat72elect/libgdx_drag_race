package com.nopalsoft.dragracer.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.game_objects.EnemyCar;
import com.nopalsoft.dragracer.game_objects.PlayerCar;
import com.nopalsoft.dragracer.screens.Screens;

import java.util.Iterator;

public class TrafficGame extends Table {

    public static final int STATE_RUNNING = 0;
    public static final int STATE_GAMEOVER = 1;

    public final static int NUM_COINS_FOR_SUPER_SPEED = 10;

    public final float lane2 = 390;
    public final float lane1 = 240;
    public final float lane0 = 90;

    final float width = Screens.WORLD_WIDTH;
    final float height = Screens.WORLD_HEIGHT;

    final float TIME_TO_SPAWN_CAR = 2;
    final float TIME_TO_SPAWN_COIN = 1f;
    final float DURATION_SUPER_SPEED = 5;

    private final com.nopalsoft.dragracer.game_objects.InfiniteScrollBackground backgroundRoad;
    private final Array<EnemyCar> arrayEnemyCars;
    private final Array<com.nopalsoft.dragracer.game_objects.Coin> arrCoins;
    public int state;
    public int numberOfCoinsForSuperSpeed;
    public PlayerCar playerCar;
    boolean canSuperSpeed;
    float timeToSpawnCar;
    float timeToSpawnCoin;
    float durationSuperSpeed = 0;
    boolean isSuperSpeed;
    float currentSpeed = 5;
    float score;
    int coins;

    public TrafficGame() {
        setBounds(0, 0, width, height);
        setClip(true);
        backgroundRoad = new com.nopalsoft.dragracer.game_objects.InfiniteScrollBackground(getWidth(), getHeight());
        addActor(backgroundRoad);

        playerCar = new PlayerCar(this);
        addActor(playerCar);
        arrayEnemyCars = new Array<>();
        arrCoins = new Array<>();

        state = STATE_RUNNING;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        durationSuperSpeed += delta;
        if (durationSuperSpeed >= DURATION_SUPER_SPEED) {
            stopSuperSpeed();
        }

        if (numberOfCoinsForSuperSpeed >= NUM_COINS_FOR_SUPER_SPEED) {
            canSuperSpeed = true;
        }


        updateEnemyCar(delta);
        updateMonedas(delta);
        score += delta * currentSpeed;

        if (playerCar.state == PlayerCar.STATE_DEAD) {
            state = STATE_GAMEOVER;
        }

    }

    private void updateEnemyCar(float delta) {
        // I first create a car if necessary.

        timeToSpawnCar += delta;
        if (timeToSpawnCar >= TIME_TO_SPAWN_CAR) {
            timeToSpawnCar -= TIME_TO_SPAWN_CAR;
            spawnCar();

        }

        Iterator<EnemyCar> iteratorEnemyCar = arrayEnemyCars.iterator();
        while (iteratorEnemyCar.hasNext()) {
            EnemyCar enemyCar = iteratorEnemyCar.next();
            if (enemyCar.getBounds().y + enemyCar.getHeight() <= 0) {
                iteratorEnemyCar.remove();
                removeActor(enemyCar);
                continue;
            }

            if (isSuperSpeed)
                enemyCar.setSpeed();
        }

        // Then I check the collisions with the player
        iteratorEnemyCar = arrayEnemyCars.iterator();
        while (iteratorEnemyCar.hasNext()) {
            EnemyCar enemyCar = iteratorEnemyCar.next();
            if (enemyCar.getBounds().overlaps(playerCar.getBounds())) {
                iteratorEnemyCar.remove();

                if (enemyCar.getX() > playerCar.getX()) {
                    enemyCar.crash(true, enemyCar.getY() > playerCar.getY());
                    if (!isSuperSpeed)
                        playerCar.crash(false, true);
                } else {
                    enemyCar.crash(false, enemyCar.getY() > playerCar.getY());
                    if (!isSuperSpeed)
                        playerCar.crash(true, true);
                }
                Assets.soundCrash.stop();
                Assets.playSound(Assets.soundCrash);

            }
        }

    }

    private void updateMonedas(float delta) {

        timeToSpawnCoin += delta;

        if (timeToSpawnCoin >= TIME_TO_SPAWN_COIN) {
            timeToSpawnCoin -= TIME_TO_SPAWN_COIN;
            spawnCoin();
        }

        Iterator<com.nopalsoft.dragracer.game_objects.Coin> iterator = arrCoins.iterator();
        while (iterator.hasNext()) {
            com.nopalsoft.dragracer.game_objects.Coin obj = iterator.next();
            if (obj.getBounds().y + obj.getHeight() <= 0) {
                iterator.remove();
                removeActor(obj);
                continue;
            }
            // I see if they are touching my car
            if (playerCar.getBounds().overlaps(obj.getBounds())) {
                iterator.remove();
                removeActor(obj);
                coins++;
                numberOfCoinsForSuperSpeed++;
                continue;
            }

            // I see if it's touching an enemy
            for (EnemyCar objEnemy : arrayEnemyCars) {
                if (obj.getBounds().overlaps(objEnemy.getBounds())) {
                    iterator.remove();
                    removeActor(obj);
                    break;
                }
            }

            if (isSuperSpeed)
                obj.setSpeed();

        }

    }

    public void setSuperSpeed() {
        canSuperSpeed = false;
        durationSuperSpeed = 0;
        isSuperSpeed = true;
        currentSpeed = 30;
        numberOfCoinsForSuperSpeed = 0;
        backgroundRoad.setSpeed();

    }

    public void stopSuperSpeed() {
        isSuperSpeed = false;
        currentSpeed = 5;
        backgroundRoad.stopSpeed();
    }

    private void spawnCar() {
        int lane = MathUtils.random(0, 2);
        float x = 0;
        if (lane == 0)
            x = lane0;
        if (lane == 1)
            x = lane1;
        if (lane == 2)
            x = lane2;
        EnemyCar enemyCar = new EnemyCar(x, getHeight());
        arrayEnemyCars.add(enemyCar);
        addActor(enemyCar);
    }

    private void spawnCoin() {
        int lane = MathUtils.random(0, 2);
        float x = 0;
        if (lane == 0)
            x = lane0;
        if (lane == 1)
            x = lane1;
        if (lane == 2)
            x = lane2;
        com.nopalsoft.dragracer.game_objects.Coin obj = new com.nopalsoft.dragracer.game_objects.Coin(x, getHeight());
        arrCoins.add(obj);
        addActor(obj);
    }

}
