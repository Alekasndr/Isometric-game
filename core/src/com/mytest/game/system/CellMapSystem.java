package com.mytest.game.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.MouseInputComponent;
import com.mytest.game.component.PhysicMapCellComponent;
import com.mytest.game.component.TransformComponent;
import com.mytest.game.utility.physics.Cell;

public class CellMapSystem extends EntitySystem {
    private ImmutableArray<Entity> mouseEntities;
    private ImmutableArray<Entity> cellMapEntities;
    private final ComponentMapper<PhysicMapCellComponent> mapTilesComponents = ComponentMapper.getFor(PhysicMapCellComponent.class);
    private final ComponentMapper<TransformComponent> physicMapTransformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<MouseInputComponent> mouseInputComponentComponentMapper = ComponentMapper.getFor(MouseInputComponent.class);
    OrthographicCamera orthographicCamera;
    ShapeRenderer shapeRenderer;
    ShapeRenderer shapeRenderer1;

    public CellMapSystem(OrthographicCamera orthographicCamera) {
        super();
        this.orthographicCamera = orthographicCamera;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer1 = new ShapeRenderer();
    }

    public void addedToEngine(Engine engine) {
        cellMapEntities = engine.getEntitiesFor(Family.all(PhysicMapCellComponent.class, TransformComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int a = 0; a < cellMapEntities.size(); ++a) {
            Entity entity = cellMapEntities.get(a);
            PhysicMapCellComponent physicMapCellComponent = mapTilesComponents.get(entity);
            TransformComponent transformComponent = physicMapTransformComponentComponentMapper.get(entity);
            shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (int i = 0; i < physicMapCellComponent.getCellMapWidth(); i++) {
                for (int j = 0; j < physicMapCellComponent.getCellMapHeight(); j++) {
                    Cell cell = physicMapCellComponent.getPhysicsMap().getCell(i, j);

                    Vector2 calc = isometricToWorldCoordinates(i, j, physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight());

                    float calcX = calc.x + transformComponent.getPosition().x;
                    float calcY = calc.y + transformComponent.getPosition().y;

                    if (cell != null) {
                        if (cell.isAvailableForBuilding()) {
                            shapeRenderer.setColor(255, 255, 255, 0);
                            shapeRenderer.circle(calcX, calcY, 2);
                        }
                    }
                }
            }
            shapeRenderer.end();
            orthographicCamera.update();
        }
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        shapeRenderer.dispose();
    }

    public static Vector2 isometricToWorldCoordinates(int x, int y, float cellWidth, float cellHeight) {
        return new Vector2(
                (y + x) * cellWidth / 2.0f,
                (y - x) * cellHeight / 2.0f
        );
    }
}
