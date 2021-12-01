package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatDivision implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();
        for (Freeze freeze : StaffControl.getInterface().getFreezeHandler().getFreezeList()) {
            event.getRecipients().remove(Bukkit.getPlayer(freeze.getPlayerUUID()));
            event.getRecipients().remove(Bukkit.getPlayer(freeze.getStaffUUID()));
        }
        if ((StaffControl.getInterface().getFreezeHandler().getFreeze(sender.getUniqueId()) == null)) {
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
