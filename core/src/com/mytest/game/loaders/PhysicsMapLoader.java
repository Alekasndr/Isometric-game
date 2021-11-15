package com.mytest.game.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.badlogic.gdx.utils.XmlReader;
import com.mytest.game.utility.physics.Cell;
import com.mytest.game.utility.physics.PhysicsMap;
import com.mytest.game.utility.map.Zone;

import java.util.Hashtable;

public class PhysicsMapLoader {
    public static PhysicsMap loadCells() {
        String configName = "maps/main_island/main_island_physics_config.xml";
        FileHandle fileHandle = Gdx.files.classpath(configName);
        XmlReader xmlReader = new XmlReader();
        XmlReader.Element parseConfig = xmlReader.parse(fileHandle);

        Hashtable<Integer, Zone> zones = new Hashtable<>();
        for (XmlReader.Element zone : parseConfig.getChildByName("zones").getChildByName("list").getChildrenByName("PhysicsZone")) {
            zones.put(Integer.parseInt(zone.getAttribute("id")),
                    new Zone(Integer.parseInt(zone.getAttribute("id")),
                            Boolean.parseBoolean(zone.getAttribute("availableForBuilding")),
                            Integer.parseInt(zone.getAttribute("color")),
                            Boolean.parseBoolean(zone.getAttribute("locked")),
                            zone.getAttribute("name"),
                            Boolean.parseBoolean(zone.getAttribute("passable"))
                    ));
        }

        PhysicsMap physicsMap = new PhysicsMap(Integer.parseInt(parseConfig.getAttribute("width")), Integer.parseInt(parseConfig.getAttribute("height")));
        for (XmlReader.Element cellElement : parseConfig.getChildByName("cells").getChildByName("list").getChildrenByName("PhysicsCell")) {
            int x = Integer.parseInt(cellElement.getAttribute("i"));
            int y = Integer.parseInt(cellElement.getAttribute("j"));
            int zone = Integer.parseInt(cellElement.getAttribute("zone"));

            physicsMap.SetCell(x, y, new Cell(zone, zones, x, y));
        }
        return physicsMap;
    }
}
