package me.mrliam2614.commands;

import me.mrliam2614.FacilitisAPI;
import me.mrliam2614.StaffControl;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUnfreeze implements CommandExecutor {

    private final StaffControl plugin;

    public CommandUnfreeze() {
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

        if (!plugin.getFreezeHandler().isFrozen(frozenPlayer.getUniqueId())) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cThis player is not frozen");
            return true;
        }
        if (!plugin.getFreezeHandler().isFreezing(freezingPlayer.getUniqueId())) {
            facilitisAPI.msg.sendMessage((Player) sender, "&cYou are not freezing a player");
            return true;
        }

        plugin.getFreezeHandler().unfreezePlayer(freezingPlayer, frozenPlayer);
        return true;
    }
}
