package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RunCommand implements Listener {

    @EventHandler
    public void onCommandRun(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!(StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId()) || StaffControl.getInterface().getFreezeHandler().isFrozen(player.getUniqueId()))) {
            return;
        }
        if (EventManager.isStaff(player)) {
            if (!event.getMessage().contains("unfreeze") && !event.getMessage().contains("freeze")) {
                StaffControl.getInterface().getFacilitisAPI().msg.sendMessage(player, "&cYou can't run commands while you are freezing!");
                event.setCancelled(true);
            }
        }
        if (EventManager.isPlayer(player)) {
            StaffControl.getInterface().getFacilitisAPI().msg.sendMessage(player, "&cYou can't run commands while you are frozen!");
            event.setCancelled(true);
        }
    }
}
