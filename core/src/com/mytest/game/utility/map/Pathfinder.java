package com.mytest.game.utility.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.mytest.game.utility.physics.Cell;
import com.mytest.game.utility.physics.PhysicsMap;

import java.util.ArrayList;
import java.util.HashSet;

public class Pathfinder {
    private PhysicsMap physicsMap;
    private Vector2 startPosition;
    private Vector2 endPosition;

    public Pathfinder(PhysicsMap physicsMap) {
        this.physicsMap = physicsMap;
        this.startPosition = new Vector2();
        this.endPosition = new Vector2();
    }

    public Queue<Vector2> BuildPath(int startX, int startY, int endX, int endY, float tileWidth, float tileHeight, Vector2 offset) {
        this.startPosition.x = startX;
        this.startPosition.y = startY;
        this.endPosition.x = endX;
        this.endPosition.y = endY;
        Queue<Vector2> result = new Queue<>();
        for (Vector2 isometricPoint : searchingTheWay()) {
            result.addLast(isometricToWorldCoordinates((int) isometricPoint.x, (int) isometricPoint.y, tileWidth, tileHeight).add(offset));
        }
        return result;
    }

    public ArrayList<Vector2> searchingTheWay() {
        Queue<PathQueueItem> queue = new Queue<>();
        HashSet<Cell> visited = new HashSet<>();
        queue.addLast(new PathQueueItem((int) startPosition.x, (int) startPosition.y, new ArrayList<Vector2>()));
        while (queue.size > 0) {
            PathQueueItem pathQueueItem = queue.removeFirst();
            pathQueueItem.path.add(new Vector2(pathQueueItem.currentX, pathQueueItem.currentY));

            if (pathQueueItem.currentX == endPosition.x && pathQueueItem.currentY == endPosition.y) {
                startPosition.x = endPosition.x;
                startPosition.y = endPosition.y;
                return pathQueueItem.path;
            }
            //down left
            if (pathQueueItem.currentX + 1 < physicsMap.getWidth()
                    && physicsMap.getCell(pathQueueItem.currentX + 1, pathQueueItem.currentY) != null
                    && physicsMap.getCell(pathQueueItem.currentX + 1, pathQueueItem.currentY).isPassable()
                    && !visited.contains(physicsMap.getCell(pathQueueItem.currentX + 1, pathQueueItem.currentY))) {
                visited.add(physicsMap.getCell(pathQueueItem.currentX + 1, pathQueueItem.currentY));
                queue.addLast(new PathQueueItem(pathQueueItem.currentX + 1, pathQueueItem.currentY, new ArrayList<>(pathQueueItem.path)));
            }
            //down right
            if (pathQueueItem.currentY + 1 < physicsMap.getHeight()
                    && physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY + 1) != null
                    && physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY + 1).isPassable()
                    && !visited.contains(physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY + 1))) {
                visited.add(physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY + 1));
                queue.addLast(new PathQueueItem(pathQueueItem.currentX, pathQueueItem.currentY + 1, new ArrayList<>(pathQueueItem.path)));
            }
            //up right
            if (pathQueueItem.currentX - 1 > -1
                    && physicsMap.getCell(pathQueueItem.currentX - 1, pathQueueItem.currentY) != null
                    && physicsMap.getCell(pathQueueItem.currentX - 1, pathQueueItem.currentY).isPassable()
                    && !visited.contains(physicsMap.getCell(pathQueueItem.currentX - 1, pathQueueItem.currentY))) {
                visited.add(physicsMap.getCell(pathQueueItem.currentX - 1, pathQueueItem.currentY));
                queue.addLast(new PathQueueItem(pathQueueItem.currentX - 1, pathQueueItem.currentY, new ArrayList<>(pathQueueItem.path)));
            }
            //up left
            if (pathQueueItem.currentY - 1 > -1
                    && physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY - 1) != null
                    && physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY - 1).isPassable()
                    && !visited.contains(physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY - 1))) {
                visited.add(physicsMap.getCell(pathQueueItem.currentX, pathQueueItem.currentY - 1));
                queue.addLast(new PathQueueItem(pathQueueItem.currentX, pathQueueItem.currentY - 1, new ArrayList<>(pathQueueItem.path)));
            }
        }
        return new ArrayList<Vector2>() {{
            add(new Vector2(startPosition.x, startPosition.y));
        }};
    }

    private static class PathQueueItem {
        public int currentX;
        public int currentY;
        public ArrayList<Vector2> path;

        public PathQueueItem(int currentX, int currentY, ArrayList<Vector2> path) {
            this.currentX = currentX;
            this.currentY = currentY;
            this.path = path;
        }
    }

    public static Vector2 isometricToWorldCoordinates(int x, int y, float cellWidth, float cellHeight) {
        return new Vector2(
                (y + x) * cellWidth / 2.0f,
                (y - x) * cellHeight / 2.0f
        );
    }
}

