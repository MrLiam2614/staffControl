package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.Freeze;
import me.mrliam2614.freeze.FreezeHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!(StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId()) || StaffControl.getInterface().getFreezeHandler().isFrozen(player.getUniqueId()))) {
            return;
        }
        FreezeHandler freezeHandler = StaffControl.getInterface().getFreezeHandler();
        if (freezeHandler.getFreeze(player.getUniqueId()) != null) {
            Freeze freeze = freezeHandler.getFreeze(player.getUniqueId());
            Player staffPlayer = Bukkit.getPlayer(freeze.getStaffUUID());
            Player userPlayer = Bukkit.getPlayer(freeze.getPlayerUUID());
            freezeHandler.removeFreeze(freeze);

            if (staffPlayer.isOnline())
                staffPlayer.sendMessage("&4User " + userPlayer.getName() + " left control");


            if (userPlayer.isOnline()) {
                userPlayer.sendMessage("&4Your controller " + staffPlayer.getName() + " left control");
                for(Player p : Bukkit.getServer().getOnlinePlayers()){
                    if(p.hasPermission("staffcontrol.control")){
                        p.sendMessage("&c" + staffPlayer.getName() + " &6left while controlling: &c" + userPlayer.getName());
                    }
                }
            }
        }
    }
}
