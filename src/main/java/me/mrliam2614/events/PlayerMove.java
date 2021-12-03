package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.FreezeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!EventManager.isPlayerFreezeAction(player)) {
            return;
        }
        FreezeHandler freezeHandler = StaffControl.getInstance().getFreezeHandler();
        if (EventManager.isPlayer(player)) {
            UUID staffUUID = freezeHandler.getStaff(player.getUniqueId());
            if (player.getLocation().distance(freezeHandler.getStructure(staffUUID).getPlayerLoc()) > 1.5) {
                player.teleport(freezeHandler.getStructure(staffUUID).getPlayerLoc());
            }
        }
        if (EventManager.isStaff(player)) {
            if (player.getLocation().distance(freezeHandler.getStructure(player.getUniqueId()).getLocation()) > 5) {
                player.teleport(freezeHandler.getStructure(player.getUniqueId()).getStaffLoc());
            }
        }
    }
}
