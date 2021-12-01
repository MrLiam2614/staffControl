package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatDivision implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        if(!(StaffControl.getInterface().getFreezeHandler().isFreezing(sender.getUniqueId()) || StaffControl.getInterface().getFreezeHandler().isFrozen(sender.getUniqueId()))){
            return;
        }
        //Is Sender Staff
        if (StaffControl.getInterface().getFreezeHandler().isFreezing(sender.getUniqueId())) {
            event.getRecipients().clear();
            event.getRecipients().add(sender);
            event.getRecipients().add(Bukkit.getPlayer(StaffControl.getInterface().getFreezeHandler().getPlayer(sender.getUniqueId())));
        }
        //Is Sender Player
        if (StaffControl.getInterface().getFreezeHandler().isFrozen(sender.getUniqueId())) {
            event.getRecipients().clear();
            event.getRecipients().add(sender);
            event.getRecipients().add(Bukkit.getPlayer(StaffControl.getInterface().getFreezeHandler().getStaff(sender.getUniqueId())));
        }
    }
}
