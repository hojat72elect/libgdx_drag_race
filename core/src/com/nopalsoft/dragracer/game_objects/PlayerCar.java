package com.nopalsoft.dragracer.game_objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.Settings;
import com.nopalsoft.dragracer.game.TrafficGame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class PlayerCar extends Actor {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_SPINNING = 1;
    public static final int STATE_EXPLOSION = 2;
    public static final int STATE_DEAD = 3;
    public static final float TIME_EXPLOSION = Assets.newExplosion.getAnimationDuration();
    public static final float TIME_SPINNING = 1.5f;
    private final TrafficGame trafficGame;
    private final Rectangle bounds = new Rectangle();
    public int state;
    public float stateTime;
    float moveTime = .75f;
    TextureRegion keyframe;
    ShapeRenderer renders = new ShapeRenderer();
    private int lane;

    public PlayerCar(TrafficGame trafficGame) {
        this.trafficGame = trafficGame;

        float width, height;

        switch (Settings.selectedSkin) {
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_DEVIL:
                keyframe = Assets.carDevil;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_BANSHEE:
                keyframe = Assets.carBanshee;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_TORNADO:
                keyframe = Assets.carTornado;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_TURISM:
                keyframe = Assets.carTourism;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_AUDI_S5:
                keyframe = Assets.carAudiS5;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_BMW_X6:
                keyframe = Assets.carBmwX6;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_BULLET:
                keyframe = Assets.carBullet;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_CHEVROLET_CROSSFIRE:
                keyframe = Assets.carChevroletCrossfire;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_CITROEN_C4:
                keyframe = Assets.carCitroenC4;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_DODGE_CHARGER:
                keyframe = Assets.carDodgeCharger;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_FIAT_500_LOUNGE:
                keyframe = Assets.carFiat500Lounge;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_HONDA_CRV:
                keyframe = Assets.carHondaCRV;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_MAZDA_6:
                keyframe = Assets.carMazda6;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_MAZDA_RX8:
                keyframe = Assets.carMazdaRX8;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_SEAT_IBIZA:
                keyframe = Assets.carSeatIbiza;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
            case com.nopalsoft.dragracer.shop.CharactersSubMenu.SKIN_CAR_VOLKSWAGEN_SCIROCCO:
            default:
                keyframe = Assets.carVolkswagenScirocco;
                width = keyframe.getRegionWidth();
                height = keyframe.getRegionHeight();
                break;
        }

        setWidth(width - 10);
        setHeight(height - 10);

        lane = 1;
        setPosition(trafficGame.lane1 - getWidth() / 2, 200);

        state = STATE_NORMAL;
        stateTime = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBounds();

        if (state == STATE_SPINNING && stateTime >= TIME_SPINNING) {
            state = STATE_EXPLOSION;
            stateTime = 0;
        }

        if (state == STATE_EXPLOSION) {
            if (stateTime >= TIME_EXPLOSION) {
                remove();
                state = STATE_DEAD;
                stateTime = 0;
            }
        }

        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float drawWidth = getWidth() + 10;
        float drawHeight = getHeight() + 10;
        float angle = getRotation();

        switch (state) {
            case STATE_NORMAL:
            case STATE_SPINNING:
                batch.draw(keyframe, getX(), getY(), drawWidth / 2, drawHeight / 2,
                        drawWidth, drawHeight, 1, 1, angle);
                break;
            default:
            case STATE_EXPLOSION:
                drawWidth = getHeight() + 20;
                drawHeight = getHeight() + 20;
                angle = 0;
                batch.draw(Assets.newExplosion.getKeyFrame(stateTime), getX()
                                - drawWidth / 2 / 2f, getY(), drawWidth / 2,
                        drawHeight / 2, drawWidth, drawHeight, 1, 1, angle);
                break;
        }

        if (Settings.drawDebugLines) {
            batch.end();
            renders.setProjectionMatrix(batch.getProjectionMatrix());
            renders.begin(ShapeType.Line);
            renders.rect(bounds.x, bounds.y, bounds.width, bounds.height);
            renders.end();
            batch.begin();
        }
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    public void tryMoveRight() {
        if ((getActions().size == 0) && (lane != 2)) {
            addAction(Actions.rotateTo(-10));
            moveToLane(lane + 1);
        }
    }

    public void tryMoveLeft() {
        if ((getActions().size == 0) && (lane != 0)) {
            addAction(Actions.rotateTo(10));
            moveToLane(lane - 1);
        }
    }

    private void moveToLane(int lane) {
        this.lane = lane;

        switch (lane) {
            case 0:
                addAction(Actions.sequence(
                        moveTo(trafficGame.lane0 - getWidth() / 2f, getY(),
                                moveTime), Actions.rotateTo(0)));
                break;
            case 1:
                addAction(Actions.sequence(
                        moveTo(trafficGame.lane1 - getWidth() / 2f, getY(),
                                moveTime), Actions.rotateTo(0)));
                break;
            case 2:
                addAction(Actions.sequence(
                        moveTo(trafficGame.lane2 - getWidth() / 2f, getY(),
                                moveTime), Actions.rotateTo(0)));
                break;
        }

        if (MathUtils.randomBoolean())
            Assets.playSound(Assets.soundTurn1);
        else
            Assets.playSound(Assets.soundTurn2);
    }

    public void crash(boolean front, boolean above) {
        clearActions();
        if (state == STATE_NORMAL) {
            state = STATE_SPINNING;
            stateTime = 0;
        }

        if (front && above)
            addAction(-360, 125, 125);

        if (front && !above)
            addAction(360, 125, -125);

        if (!front && above)
            addAction(360, -125, 125);

        if (!front && !above)
            addAction(-360, -125, -125);
    }

    private void addAction(float rotation, float positionX, float positionY) {
        addAction(sequence(parallel(Actions.rotateBy(rotation, TIME_SPINNING),
                Actions.moveBy(positionX, positionY, TIME_SPINNING))));
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
