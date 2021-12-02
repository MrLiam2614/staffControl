package me.mrliam2614.freeze;

import me.mrliam2614.StaffControl;
import me.mrliam2614.structure.Structure;
import me.mrliam2614.structure.StructureHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FreezeHandler {
    private final List<Freeze> freezeList;
    private final StructureHandler structureHandler;

    public FreezeHandler() {
        freezeList = new ArrayList<>();
        structureHandler = new StructureHandler();
    }

    public boolean addFreeze(Freeze freeze) {
        if (freeze.getStaffUUID() != null) {
            freezeList.add(freeze);

            Location strLoc = freeze.getStaffLocation();
            World world = freeze.getStaffLocation().getWorld();
            double baseHeight = world.getMaxHeight() - 4;
            strLoc.setY(baseHeight);

            Structure structure = new Structure(freeze.getStaffUUID(), strLoc);
            if (!structureHandler.addStructure(structure)) {
                freezeList.remove(freeze);
                return false;
            }
            teleportToStructure(freeze, structure);
            return true;
        }
        return false;
    }

    private void teleportToStructure(Freeze freeze, Structure structure) {
        Player staff = Bukkit.getPlayer(freeze.getStaffUUID());
        Player player = Bukkit.getPlayer(freeze.getPlayerUUID());

        staff.teleport(structure.getStaffLoc());
        player.teleport(structure.getPlayerLoc());
    }

    public boolean removeFreeze(Freeze freeze) {
        if (freeze.getStaffUUID() != null) {
            Structure structure = structureHandler.getStructure(freeze.getStaffUUID());
            structureHandler.removeStructure(structure);
            freezeList.remove(freeze);
            return true;
        }
        return false;
    }

    public boolean isFrozen(UUID target) {
        return getFrozen(target) != null;
    }

    public boolean isFreezing(UUID staff) {
        return getFreezer(staff) != null;
    }

    public Freeze getFrozen(UUID target) {
        for (Freeze freeze : freezeList) {
            if (freeze.getPlayerUUID() == target) {
                return freeze;
            }
        }
        return null;
    }

    public Freeze getFreezer(UUID staff) {
        for (Freeze freeze : freezeList) {
            if (freeze.getStaffUUID() == staff) {
                return freeze;
            }
        }
        return null;
    }

    public Freeze getFreeze(UUID user) {
        for (Freeze freeze : freezeList) {
            if (freeze.getStaffUUID() == user) {
                return freeze;
            }
            if (freeze.getPlayerUUID() == user) {
                return freeze;
            }
        }
        return null;
    }

    public UUID getPlayer(UUID staffUUID) {
        for (Freeze freeze : freezeList) {
            if (freeze.getStaffUUID() == staffUUID) {
                return freeze.getPlayerUUID();
            }
        }
        return null;
    }

    public UUID getStaff(UUID userUUID) {
        for (Freeze freeze : freezeList) {
            if (freeze.getPlayerUUID() == userUUID) {
                return freeze.getStaffUUID();
            }
        }
        return null;
    }

    public List<Freeze> getFreezeList() {
        return freezeList;
    }

    public Structure getStructure(UUID staffID) {
        return structureHandler.getStructure(staffID);
    }

    public boolean unfreezePlayer(Freeze freeze) {
        unfreezePlayer(Bukkit.getPlayer(freeze.getStaffUUID()), Bukkit.getPlayer(freeze.getPlayerUUID()));
        return true;
    }


    public boolean unfreezePlayer(Player freezingPlayer, Player frozenPlayer) {
        StaffControl plugin = StaffControl.getInterface();
        Freeze freeze = plugin.getFreezeHandler().getFrozen(frozenPlayer.getUniqueId());
        frozenPlayer.setFlying(freeze.isFlying());
        frozenPlayer.setCanPickupItems(freeze.isCanPickUp());
        Location playerLoc = freeze.getPlayerLocation();
        Location staffLoc = freeze.getStaffLocation();

        if (plugin.getFreezeHandler().removeFreeze(freeze)) {
            plugin.getFacilitisAPI().msg.sendMessage(freezingPlayer, "&aYou have un-frozen " + frozenPlayer.getName());
            plugin.getFacilitisAPI().msg.sendMessage(frozenPlayer, "&cYou have been un-frozen by " + freezingPlayer.getName());

            System.out.println(freezingPlayer.getLocation());
            System.out.println(staffLoc);
            freezingPlayer.teleport(staffLoc);
            System.out.println(freezingPlayer.getLocation());
            frozenPlayer.teleport(playerLoc);
            return true;
        }
        return false;
    }
}
