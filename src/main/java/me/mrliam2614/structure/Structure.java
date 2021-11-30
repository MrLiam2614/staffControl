package me.mrliam2614.structure;

import org.bukkit.Location;

import java.util.UUID;

public class Structure {
    private final UUID creator;
    private final Location location;

    public Structure(UUID creator, Location location) {
        this.creator = creator;
        this.location = location;
    }

    public UUID getCreator() {
        return creator;
    }

    public Location getLocation() {
        return location;
    }

    public Location getPlayerLoc(){
        return location.clone().add(0.0,0.0,3.0);
    }

    public Location getStaffLoc(){
        return location.clone().add(0.0,0.0,-3.0);
    }
}
