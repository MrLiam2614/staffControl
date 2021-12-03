package me.mrliam2614.structure;

import me.mrliam2614.StaffControl;
import me.mrliam2614.storage.ConfigStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StructureHandler {
    private final List<Structure> structureList;
    private final StaffControl plugin;
    private final ConfigStorage configStorage;

    public StructureHandler() {
        structureList = new ArrayList<>();
        this.plugin = StaffControl.getInstance();
        this.configStorage = plugin.getConfigStorage();
    }

    public boolean addStructure(Structure structure) {
        if (structure.getCreator() != null) {
            structureList.add(structure);
            createStructure(structure);
            return true;
        }
        return false;
    }

    public void removeStructure(Structure structure) {
        if ((getStructure(structure.getCreator()) == null)) {
            return;
        }
        destroyStructure(structure);
        structureList.remove(structure);
    }

    public Structure getStructure(UUID creator) {
        for (Structure structure : structureList) {
            if (structure.getCreator() == creator) {
                return structure;
            }
        }
        return null;
    }


    private void createStructure(Structure structure) {
        Location loc = structure.getLocation();
        World world = structure.getLocation().getWorld();

        loopStructureBlocks(world, loc, configStorage.getCentral(), configStorage.getCentral2(), configStorage.getPlayer(),
                configStorage.getStaff(), configStorage.getBorders(), configStorage.getCage(), configStorage.getCage());
    }

    private void destroyStructure(Structure structure) {
        World world = structure.getLocation().getWorld();
        Location loc = structure.getLocation();

        //PlaceBlocks
        loopStructureBlock(world, loc, configStorage.getAir());
    }

    private void loopStructureBlock(World world, Location loc, Material mat) {
        loopStructureBlocks(world, loc, mat, mat, mat, mat, mat, mat, mat);
    }

    private void loopStructureBlocks(World world, Location loc, Material central, Material central2, Material player, Material staff, Material border, Material cage1, Material cage2) {
        squareBlocks(world, loc.clone(), central2);
        world.getBlockAt(loc.clone()).setType(central);
        squareBlocks(world, loc.clone().add(0.0,0.0,-3.0), player);
        squareBlocks(world, loc.clone().add(0.0,0.0,3.0), staff);
        borderBlocks(world, loc.clone().add(-2.0,0.0,5.0), border);
        borderBlocks(world, loc.clone().add(-2.0,1.0,5.0), cage1);
        borderBlocks(world, loc.clone().add(-2.0,2.0,5.0), cage2);
    }

    private void squareBlocks(World world, Location loc, Material mat) {
        world.getBlockAt(loc).setType(mat);
        world.getBlockAt(loc.add(1.0, 0.0, 0.0)).setType(mat);
        world.getBlockAt(loc.add(0.0, 0.0, 1.0)).setType(mat);
        world.getBlockAt(loc.add(-1.0, 0.0, 0.0)).setType(mat);
        world.getBlockAt(loc.add(-1.0, 0.0, 0.0)).setType(mat);
        world.getBlockAt(loc.add(0.0, 0.0, -1.0)).setType(mat);
        world.getBlockAt(loc.add(0.0, 0.0, -1.0)).setType(mat);
        world.getBlockAt(loc.add(1.0, 0.0, 0.0)).setType(mat);
        world.getBlockAt(loc.add(1.0, 0.0, 0.0)).setType(mat);
    }


    private void borderBlocks(World world, Location loc, Material mat) {
        int i = 0;
        while (i < 10) {
            world.getBlockAt(loc).setType(mat);
            loc.add(0.0, 0.0, -1.0);
            i++;
        }
        i = 0;
        while (i < 4) {
            world.getBlockAt(loc).setType(mat);
            loc.add(1.0, 0.0, 0.0);
            i++;
        }
        i = 0;
        while (i < 10) {
            world.getBlockAt(loc).setType(mat);
            loc.add(0.0, 0.0, 1.0);
            i++;
        }
        i = 0;
        while (i < 4) {
            world.getBlockAt(loc).setType(mat);
            loc.add(-1.0, 0.0, 0.0);
            i++;
        }
    }
}
