package me.mrliam2614.commands;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdUnfreeze implements CommandExecutor {

    private final StaffControl plugin;

    public CmdUnfreeze() {
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

        if (!plugin.getFreezeHandler().isFrozen(frozenPlayer.getUniqueId())) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cThis player is not frozen");
            return true;
        }
        if (!plugin.getFreezeHandler().isFreezing(freezingPlayer.getUniqueId())) {
            plugin.getFacilitisAPI().msg.sendMessage((Player) sender, "&cYou are not freezing a player");
            return true;
        }

        unfreezePlayer(freezingPlayer, frozenPlayer);
        return true;
    }

    public void unfreezePlayer(Player freezingPlayer, Player frozenPlayer) {
        Freeze freeze = plugin.getFreezeHandler().getFrozen(frozenPlayer.getUniqueId());
        frozenPlayer.setFlying(freeze.isFlying());
        frozenPlayer.setCanPickupItems(freeze.isCanPickUp());
        plugin.getFacilitisAPI().msg.sendMessage(freezingPlayer, "&aYou have un-frozen " + frozenPlayer.getName());
        plugin.getFacilitisAPI().msg.sendMessage(frozenPlayer, "&cYou have been un-frozen by " + frozenPlayer.getName());
        frozenPlayer.teleport(freeze.getPlayerLocation());
        freezingPlayer.teleport(freeze.getStaffLocation());
        plugin.getFreezeHandler().removeFreeze(freeze);
    }
}
