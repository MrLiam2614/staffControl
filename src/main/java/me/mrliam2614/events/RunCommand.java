package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RunCommand implements Listener {

    @EventHandler
    public void onCommandRun(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if(!(StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId()) || StaffControl.getInterface().getFreezeHandler().isFreezed(player.getUniqueId()))){
            return;
        }
        if(StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId())){
            if(!event.getMessage().equalsIgnoreCase("unfreeze")) {
                StaffControl.getInterface().getFacilitisAPI().msg.sendMessage(player, "&cYou can't teleport while you are freezing!");
                event.setCancelled(true);
            }
        }
        if(StaffControl.getInterface().getFreezeHandler().isFreezed(player.getUniqueId())){
            StaffControl.getInterface().getFacilitisAPI().msg.sendMessage(player, "&cYou can't teleport while you are freezed!");
            event.setCancelled(true);
        }
    }
}
