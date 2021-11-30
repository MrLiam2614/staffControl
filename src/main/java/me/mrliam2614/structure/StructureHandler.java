package me.mrliam2614.structure;

import me.mrliam2614.StaffControl;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StructureHandler {
    private final List<Structure> structureList;
    private final StaffControl plugin;

    public StructureHandler() {
        structureList = new ArrayList<>();
        this.plugin = StaffControl.getInterface();
    }

    public boolean addStructure(Structure structure) {
        if (getStructure(structure.getCreator()) == null) {
            return false;
        }
        structureList.add(structure);
        return true;
    }

    public boolean removeStructure(Structure structure) {
        if (!(getStructure(structure.getCreator()) == null)) {
            return false;
        }
        structureList.remove(structure);
        return true;
    }

    public Structure getStructure(UUID creator) {
        for (Structure structure : structureList) {
            if (structure.getCreator() == creator) {
                return structure;
            }
        }
        return null;
    }


    public void createStructure(Structure structure) {
        World world = structure.getLocation().getWorld();
        double baseHeight = world.getMaxHeight() - 4;
        Location loc = structure.getLocation();
        loc.setY(baseHeight);

        //PlaceBlocks
        Material central = getMaterial(plugin.getConfig().getString("structure.central"));
        Material central2 = getMaterial(plugin.getConfig().getString("structure.central2"));
        Material player = getMaterial(plugin.getConfig().getString("structure.player"));
        Material staff = getMaterial(plugin.getConfig().getString("structure.staff"));
        Material borders = getMaterial(plugin.getConfig().getString("structure.borders"));
        Material cage = getMaterial(plugin.getConfig().getString("structure.cage"));


        world.getBlockAt(loc).setType(central);
        squareBlocks(world, loc, central2);
        loc.add(0.0, 0.0, -3.0);
        squareBlocks(world, loc, player);
        loc.add(0.0, 0.0, 6.0);
        squareBlocks(world, loc, staff);
        loc.add(-2.0, 0.0, 2.0);
        borderBlocks(world, loc, borders);
        loc.add(0.0, 1.0, 0.0);
        borderBlocks(world, loc, cage);
        loc.add(0.0, 1.0, 0.0);
        borderBlocks(world, loc, cage);
    }

    public void destroyStructure(Structure structure) {
        World world = structure.getLocation().getWorld();
        double baseHeight = world.getMaxHeight() - 4;
        Location loc = structure.getLocation();
        loc.setY(baseHeight);

        //PlaceBlocks
        Material air = Material.AIR;
        world.getBlockAt(loc).setType(air);
        squareBlocks(world, loc, air);
        loc.add(0.0, 0.0, -3.0);
        squareBlocks(world, loc, air);
        loc.add(0.0, 0.0, 6.0);
        squareBlocks(world, loc, air);
        loc.add(-2.0, 0.0, 2.0);
        borderBlocks(world, loc, air);
        loc.add(0.0, 1.0, 0.0);
        borderBlocks(world, loc, air);
        loc.add(0.0, 1.0, 0.0);
        borderBlocks(world, loc, air);
    }

    private Material getMaterial(String mat) {
        return Material.valueOf(mat);
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
        while (i < 12) {
            world.getBlockAt(loc).setType(mat);
            loc.add(0.0, 0.0, -1.0);
            i++;
        }
        i = 0;
        while (i < 6) {
            world.getBlockAt(loc).setType(mat);
            loc.add(1.0, 0.0, 0.0);
            i++;
        }
        i = 0;
        while (i < 12) {
            world.getBlockAt(loc).setType(mat);
            loc.add(0.0, 0.0, 1.0);
            i++;
        }
        i = 0;
        while (i < 6) {
            world.getBlockAt(loc).setType(mat);
            loc.add(-1.0, 0.0, 0.0);
            i++;
        }
    }
}
