package com.mytest.game.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.MouseInputComponent;


import static com.badlogic.gdx.Gdx.input;

public class MouseInputSystem extends EntitySystem implements InputProcessor {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<MouseInputComponent> componentMapper = ComponentMapper.getFor(MouseInputComponent.class);
    OrthographicCamera orthographicCamera;
    private Vector2 dragPos = new Vector2();
    private Vector2 touchDown = new Vector2();
    private Vector2 touchUp = new Vector2();
    private Vector2 rightClick = new Vector2();
    private float scroll = 0;

    public MouseInputSystem(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(MouseInputComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            MouseInputComponent mouseInputComponent = componentMapper.get(entity);
            mouseInputComponent.setDrag(dragPos);
            dragPos.x = 0.0F;
            dragPos.y = 0.0F;

            if (rightClick.x != 0.0F && rightClick.y != 0.0F) {
                mouseInputComponent.setRightClik(rightClick);
                rightClick.x = 0.0F;
                rightClick.y = 0.0F;
            }
            mouseInputComponent.setZoomScroll(this.scroll);
            this.scroll = 0;
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (input.isButtonPressed(Input.Buttons.LEFT)) {
            touchUp.x = screenX;
            touchUp.y = screenY;
            // dragPos will now hold the difference between the last and the current touch positions
            // dragPos.x > 0 means the touch moved to the right, dragPos.x < 0 means a move to the left
            dragPos = touchUp.cpy().sub(touchDown);
            touchDown.x = touchUp.x;
            touchDown.y = touchUp.y;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (input.isButtonPressed(Input.Buttons.LEFT)) {
            touchDown.x = screenX;
            touchDown.y = screenY;
        }
        if (input.isButtonPressed(Input.Buttons.RIGHT)) {
            rightClick.x = screenX;
            rightClick.y = screenY;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        scroll += amountY / 20;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}
