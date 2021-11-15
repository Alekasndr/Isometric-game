package com.mytest.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.entity.*;
import com.mytest.game.loaders.MapLoader;
import com.mytest.game.system.*;
import com.mytest.game.screens.GameScreenHUD;

public class Main extends ApplicationAdapter {
    static public PooledEngine EngineInstance = new PooledEngine();
    private InputMultiplexer inputMultiplexer;
    SpriteBatch batch;
    final float GAME_WORLD_WIDTH = 1700;
    final float GAME_WORLD_HEIGHT = 1400;
    OrthographicCamera worldCamera;
    GameScreenHUD gameScreenHUD;
    private boolean windowResized;

    @Override
    public void create() {
        float aspectRatio = GAME_WORLD_WIDTH / GAME_WORLD_HEIGHT;
        worldCamera = new OrthographicCamera(GAME_WORLD_HEIGHT * aspectRatio, GAME_WORLD_HEIGHT);
        setWorldCamera(worldCamera);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(worldCamera.combined);

        initEcsSystems(batch);
        EngineInstance.addEntity(new TiledMapEntity());
        EngineInstance.addEntity(new MouseEntity());
        EngineInstance.addEntity(new RandomBuildingsPhysicMapEntity());
        EngineInstance.addEntity(new PlayerEntity(new Vector2(1500, 2000)));
    }

    @Override
    public void render() {
        Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        EngineInstance.update(Gdx.graphics.getDeltaTime());
        gameScreenHUD.render(Gdx.graphics.getDeltaTime());
        if (windowResized) {
            worldCamera.viewportWidth = Gdx.graphics.getWidth();
            worldCamera.viewportHeight = Gdx.graphics.getHeight();
            windowResized = false;
        }
    }

    @Override
    public void dispose() {
        EngineInstance.clearPools();
        batch.dispose();
        if (gameScreenHUD != null) {
            gameScreenHUD.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        windowResized = true;
        if (gameScreenHUD != null) {
            gameScreenHUD.resize(width, height);
        }
    }

    private void initEcsSystems(SpriteBatch batch) {
        ClothesSystem clothesSystem = new ClothesSystem();
        gameScreenHUD = new GameScreenHUD(clothesSystem);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gameScreenHUD.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
        MouseInputSystem mouseInputSystem = new MouseInputSystem(worldCamera);
        inputMultiplexer.addProcessor(mouseInputSystem);

        EngineInstance.addSystem(mouseInputSystem);
        EngineInstance.addSystem(new CameraSystem(worldCamera, batch));
        EngineInstance.addSystem(new MapRenderSystem(batch, worldCamera));
        EngineInstance.addSystem(new PlayerSystem(worldCamera));
        EngineInstance.addSystem(new MobsZombieSystem());
        EngineInstance.addSystem(new WoodcutSystem());
        EngineInstance.addSystem(new PathSystem());
        EngineInstance.addSystem(clothesSystem);
        EngineInstance.addSystem(new AnimationSystem());
        EngineInstance.addSystem(new RenderSystem(worldCamera));
        //    EngineInstance.addSystem(new CellMapSystem(worldCamera));
    }

    private void setWorldCamera(OrthographicCamera worldCamera) {
        worldCamera.setToOrtho(true);
        worldCamera.position.x = MapLoader.loadMapMaxSize().get(1) / 2;
        worldCamera.position.y = MapLoader.loadMapMaxSize().get(0) / 2;
        worldCamera.zoom = MapLoader.loadScale().get(0);
        worldCamera.update();
    }
}
//end.
