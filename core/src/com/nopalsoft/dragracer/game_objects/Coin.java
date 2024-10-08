package com.nopalsoft.dragracer.game_objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.Settings;

public class Coin extends Actor {

    final int STATE_NORMAL = 0;
    private final Rectangle bounds = new Rectangle();
    private final MoveToAction moveAction;
    public int state;
    boolean isSuperSpeed;
    ShapeRenderer renders = new ShapeRenderer();

    public Coin(float x, float y) {

        // I subtract less 5 so that the bounds are not so big: See draw method.
        setWidth(10);
        setHeight(32);
        setPosition(x - getWidth() / 2f, y);

        addAction(Actions.forever(Actions.rotateBy(360, 1f)));

        moveAction = new MoveToAction();
        moveAction.setPosition(getX(), -getHeight());
        moveAction.setDuration(5);
        addAction(moveAction);

        state = STATE_NORMAL;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBounds();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(Assets.coin, getX(), getY(), getWidth() / 2f,
                getHeight() / 2f, getWidth(), getHeight(), 1, 1, getRotation());

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

    public void setSpeed() {
        if (!isSuperSpeed) {
            isSuperSpeed = true;
            moveAction.reset();
            moveAction.setDuration(1f);
            addAction(moveAction);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
