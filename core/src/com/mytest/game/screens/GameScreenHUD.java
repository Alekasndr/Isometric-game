package com.mytest.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mytest.game.loaders.UiLoader;
import com.mytest.game.system.ClothesSystem;

public class GameScreenHUD implements Screen {
    private Stage stage;

    public GameScreenHUD(final ClothesSystem system) {
        this.stage = new Stage();
        final Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.align(Align.bottomRight);
        final TextureRegionDrawable hat = UiLoader.getHatButtonRegion1();
        final TextureRegionDrawable cloth = UiLoader.getClothButtonRegion1();
        final Button buttonHat = new Button(hat);
        buttonHat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                system.changeHat();
            }
        });
        rootTable.add(buttonHat).width(Value.percentWidth(0.3512f)).height(Value.percentWidth(0.3512f)).pad(20f);
        final Button buttonCloth = new Button(cloth);
        buttonCloth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                system.changeCloth();
            }
        });
        rootTable.add(buttonCloth).width(Value.percentWidth(0.5f)).height(Value.percentWidth(0.5f)).pad(20f);

        stage.addActor(rootTable);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (stage != null) {
            stage.getViewport().setScreenSize(width, height);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
    }

    public Stage getStage() {
        return stage;
    }
}
