package me.mrliam2614.freeze;

import org.bukkit.Location;

import java.util.UUID;

public class Freeze {
    private final UUID staffUUID;
    private final UUID playerUUID;
    private final Location playerLocation;
    private final Location staffLocation;

    private final boolean isFlying;
    private final boolean canPickUp;

    public Freeze(UUID staffUUID, UUID playerUUID, Location playerLocation, Location staffLocation, boolean isFlying, boolean canPickUp) {
        this.staffUUID = staffUUID;
        this.playerUUID = playerUUID;
        this.playerLocation = playerLocation;
        this.staffLocation = staffLocation;
        this.isFlying = isFlying;
        this.canPickUp = canPickUp;
    }


    public UUID getStaffUUID() {
        return staffUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public Location getStaffLocation() {
        return staffLocation;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public boolean isCanPickUp() {
        return canPickUp;
    }
}
