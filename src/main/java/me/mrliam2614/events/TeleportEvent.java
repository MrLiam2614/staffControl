package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.FreezeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportEvent implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!EventManager.isPlayerFreezeAction(player)) {
            return;
        }
        FreezeHandler freezeHandler = StaffControl.getInstance().getFreezeHandler();
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.PLUGIN)) {
            if (event.getTo().distance(freezeHandler.getStructure(freezeHandler.getFreeze(player.getUniqueId()).getStaffUUID()).getLocation()) < 10) {
                return;
            }
        }
        if (EventManager.isPlayer(player)) {
            StaffControl.getInstance().getFacilitisAPI().msg.sendMessage(player, "&cYou can't teleport while you are frozen!");
            event.setCancelled(true);
            return;
        }
        if (EventManager.isStaff(player)) {
            StaffControl.getInstance().getFacilitisAPI().msg.sendMessage(player, "&cYou can't teleport while you are freezing!");
            event.setCancelled(true);
        }
    }
}
