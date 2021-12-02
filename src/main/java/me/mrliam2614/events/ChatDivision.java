package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import me.mrliam2614.freeze.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

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

        //Is Sender is in FreezeMode
        if (EventManager.isPlayerFreezeAction(sender)) {
            event.getRecipients().clear();
            event.getRecipients().addAll(addRecipients(sender));
        }
    }

    public List<Player> addRecipients(Player player){
        List<Player> list = new ArrayList<>();
        list.add(Bukkit.getPlayer(StaffControl.getInterface().getFreezeHandler().getFreeze(player.getUniqueId()).getPlayerUUID()));
        list.add(Bukkit.getPlayer(StaffControl.getInterface().getFreezeHandler().getFreeze(player.getUniqueId()).getStaffUUID()));

        return list;
    }
}
