package me.mrliam2614.events;

import me.mrliam2614.StaffControl;
import org.bukkit.entity.Player;

public class EventManager {

    public static boolean isPlayerFreezeAction(Player player){
        return (isPlayer(player) || isStaff(player));
    }

    public static boolean isPlayer(Player player){
        return StaffControl.getInterface().getFreezeHandler().isFrozen(player.getUniqueId());
    }

    public static boolean isStaff(Player player){
        return StaffControl.getInterface().getFreezeHandler().isFreezing(player.getUniqueId());
    }
}
