package com.nopalsoft.dragracer.objetos;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.nopalsoft.dragracer.Assets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class InfiniteScrollBg extends Actor {

    MoveToAction moveAction;

    public InfiniteScrollBg(float width, float height) {
        setWidth(width);
        setHeight(height);
        setPosition(0, height);

        moveAction = new MoveToAction();
        moveAction.setPosition(0, 0);
        moveAction.setDuration(1.75f);

        addAction(forever(sequence(moveAction, moveTo(0, height))));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Assets.calle, getX(), getY() - getHeight(), getWidth(),
                getHeight() * 2);
    }

    public void setSpeed() {
        moveAction.setDuration(.3f);
    }

    public void stopSpeed() {
        moveAction.setDuration(1.75f);
    }

}
