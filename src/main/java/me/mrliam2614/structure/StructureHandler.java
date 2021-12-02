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
        if (structure.getCreator() != null) {
            structureList.add(structure);
            createStructure(structure);
            return true;
        }
        return false;
    }

    public boolean removeStructure(Structure structure) {
        if ((getStructure(structure.getCreator()) == null)) {
            return false;
        }
        destroyStructure(structure);
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


    private void createStructure(Structure structure) {
        Location loc = structure.getLocation();
        World world = structure.getLocation().getWorld();

        //PlaceBlocks
        Material central = getMaterial(plugin.getConfig().getString("structure.central"));
        Material central2 = getMaterial(plugin.getConfig().getString("structure.central2"));
        Material player = getMaterial(plugin.getConfig().getString("structure.player"));
        Material staff = getMaterial(plugin.getConfig().getString("structure.staff"));
        Material borders = getMaterial(plugin.getConfig().getString("structure.borders"));
        Material cage = getMaterial(plugin.getConfig().getString("structure.cage"));

        loopStructureBlocks(world, loc, central, central2, player, staff, borders, cage, cage);
    }

    private void destroyStructure(Structure structure) {
        World world = structure.getLocation().getWorld();
        Location loc = structure.getLocation();

        //PlaceBlocks
        Material air = Material.AIR;
        loopStructureBlock(world, loc, air);
    }

    private Material getMaterial(String mat) {
        return Material.valueOf(mat);
    }

    private void loopStructureBlock(World world, Location loc, Material mat) {
        loopStructureBlocks(world, loc, mat, mat, mat, mat, mat, mat, mat);
    }

    private void loopStructureBlocks(World world, Location loc, Material mat1, Material mat2, Material mat3, Material mat4, Material mat5, Material mat6, Material mat7) {
        squareBlocks(world, loc.clone(), mat2);
        world.getBlockAt(loc.clone()).setType(mat1);
        squareBlocks(world, loc.clone().add(0.0,0.0,-3.0), mat3);
        squareBlocks(world, loc.clone().add(0.0,0.0,3.0), mat4);
        borderBlocks(world, loc.clone().add(-2.0,0.0,5.0), mat5);
        borderBlocks(world, loc.clone().add(-2.0,1.0,5.0), mat6);
        borderBlocks(world, loc.clone().add(-2.0,2.0,5.0), mat7);
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
