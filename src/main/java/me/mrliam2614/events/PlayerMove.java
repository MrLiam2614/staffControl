package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.FreezeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!(StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId()) || StaffControl.getInterface().getFreezeHandler().isFrozen(player.getUniqueId()))) {
            return;
        }
        FreezeHandler freezeHandler = StaffControl.getInterface().getFreezeHandler();
        if (freezeHandler.isFrozen(player.getUniqueId())) {
            player.teleport(event.getFrom());
        }
        if (freezeHandler.isFreezing(player.getUniqueId())) {
            if (player.getLocation().distance(freezeHandler.getStructure(player.getUniqueId()).getLocation()) > 5) {
                player.teleport(event.getFrom());
            }
        }
    }
}
