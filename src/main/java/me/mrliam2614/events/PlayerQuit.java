package me.mrliam2614.events;

import me.mrliam2614.FacilitisAPI;
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
        if (!EventManager.isPlayerFreezeAction(player)) {
            return;
        }
        FreezeHandler freezeHandler = StaffControl.getInterface().getFreezeHandler();
        if (freezeHandler.getFreeze(player.getUniqueId()) != null) {
            Freeze freeze = freezeHandler.getFreeze(player.getUniqueId());
            Player staffPlayer = Bukkit.getPlayer(freeze.getStaffUUID());
            Player userPlayer = Bukkit.getPlayer(freeze.getPlayerUUID());

            if(!freezeHandler.unfreezePlayer(freeze)){
                return;
            }
            if (player.getUniqueId().equals(staffPlayer.getUniqueId())) {
                //Is Staff
                FacilitisAPI.getInstance().msg.sendMessage(userPlayer, "&4Your controller " + staffPlayer.getName() + " left control");
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.hasPermission("staffcontrol.control")) {
                        FacilitisAPI.getInstance().msg.sendMessage(p, "&c" + staffPlayer.getName() + " &6left while controlling: &c" + userPlayer.getName());
                    }
                }
            } else {
                //Is not Staff
                FacilitisAPI.getInstance().msg.sendMessage(staffPlayer, "&4User " + userPlayer.getName() + " left control");
            }
        }
    }
}
