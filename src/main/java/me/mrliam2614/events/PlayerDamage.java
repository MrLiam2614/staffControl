package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof Player)){
            return;
        }
        Player player = (Player) entity;
        if(!(StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId()) || StaffControl.getInterface().getFreezeHandler().isFreezed(player.getUniqueId()))){
            return;
        }
        event.setCancelled(true);
    }
}
