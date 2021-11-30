package me.mrliam2614.commands;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.Freeze;
import me.mrliam2614.structure.Structure;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFreeze implements CommandExecutor {

    private final StaffControl plugin;

    public CmdFreeze() {
        this.plugin = StaffControl.getInterface();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("staffcontrol.control")) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cYou don't have permission to do this command");
            return true;
        }
        if (!(sender instanceof Player)) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cYou must to be a player");
            return true;
        }
        if (args.length < 1) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cPlease specify a player");
            return true;
        }

        Player frozenPlayer = Bukkit.getPlayer(args[0]);
        Player freezingPlayer = (Player) sender;


        if (!frozenPlayer.isOnline()) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cThis player is not online");
            return true;
        }

        if (plugin.getFreezeHandler().isFreezed(frozenPlayer.getUniqueId())) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cThis player is already frozen");
            return true;
        }
        if (plugin.getFreezeHandler().isFreezing(freezingPlayer.getUniqueId())) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cYou are already freezing a player");
            return true;
        }

        freezePlayer(freezingPlayer, frozenPlayer);
        return true;
    }

    public void freezePlayer(Player freezingPlayer, Player frozenPlayer) {
        Freeze freeze = new Freeze(freezingPlayer.getUniqueId(), frozenPlayer.getUniqueId(), frozenPlayer.getLocation(),
                freezingPlayer.getLocation(), frozenPlayer.getWalkSpeed(), frozenPlayer.isFlying(),
                frozenPlayer.getCanPickupItems());
        plugin.getFreezeHandler().addFreeze(freeze);
        plugin.getFacilitisAPI().msg.sendMessage(freezingPlayer, "&aYou have frozen " + frozenPlayer.getName());
        plugin.getFacilitisAPI().msg.sendMessage(frozenPlayer, "&cYou have been frozen by " + frozenPlayer.getName());
        frozenPlayer.setWalkSpeed(0f);
        frozenPlayer.setFlying(false);
        frozenPlayer.setCanPickupItems(false);

        Structure structure = new Structure(freezingPlayer.getUniqueId(), freezingPlayer.getLocation());
        plugin.structureHandler().addStructure(structure);
        plugin.structureHandler().createStructure(structure);
        freezingPlayer.teleport(structure.getStaffLoc());
        frozenPlayer.teleport(structure.getPlayerLoc());
    }
}
