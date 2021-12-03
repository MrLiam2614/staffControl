package me.mrliam2614.commands;

import me.mrliam2614.FacilitisAPI;
import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFreeze implements CommandExecutor {

    private final StaffControl plugin;

    public CommandFreeze() {
        this.plugin = StaffControl.getInstance();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FacilitisAPI facilitisAPI = plugin.getFacilitisAPI();
        if (!sender.hasPermission("staffcontrol.control")) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cYou don't have permission to do this command");
            return true;
        }
        if (!(sender instanceof Player)) {
            facilitisAPI.console.sendMessage("&cYou must be a player to do that");
            return true;
        }
        if (args.length < 1) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cPlease specify a player");
            return true;
        }

        Player frozenPlayer = Bukkit.getPlayer(args[0]);
        Player freezingPlayer = (Player) sender;


        if (!frozenPlayer.isOnline()) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cThis player is not online");
            return true;
        }

        if (plugin.getFreezeHandler().isFrozen(frozenPlayer.getUniqueId())) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cThis player is already frozen");
            return true;
        }
        if (plugin.getFreezeHandler().isFreezing(freezingPlayer.getUniqueId())) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cYou are already freezing a player");
            return true;
        }

        freezePlayer(freezingPlayer, frozenPlayer);
        return true;
    }

    private void freezePlayer(Player freezingPlayer, Player frozenPlayer) {
        FacilitisAPI facilitisAPI = plugin.getFacilitisAPI();
        Freeze freeze = new Freeze(freezingPlayer.getUniqueId(), frozenPlayer.getUniqueId(), frozenPlayer.getLocation(),
                freezingPlayer.getLocation(), frozenPlayer.isFlying(), frozenPlayer.getCanPickupItems());
        if (plugin.getFreezeHandler().addFreeze(freeze)) {
            facilitisAPI.msg.sendMessage(freezingPlayer, "&aYou have frozen " + frozenPlayer.getName());
            facilitisAPI.msg.sendMessage(frozenPlayer, "&cYou have been frozen by " + freezingPlayer.getName());
            frozenPlayer.setFlying(false);
            frozenPlayer.setCanPickupItems(false);
        }
    }
}
