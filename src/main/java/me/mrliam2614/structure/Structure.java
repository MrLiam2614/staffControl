package me.mrliam2614.structure;

import org.bukkit.Location;

import java.util.UUID;

public class Structure {
    private final UUID structureOwner;
    private final Location location;

    public Structure(UUID structureOwner, Location location) {
        this.structureOwner = structureOwner;
        this.location = location;
    }

    public UUID getCreator() {
        return structureOwner;
    }

    public Location getLocation() {
        return location;
    }

    public Location getPlayerLoc(){
        return location.clone().add(0.0,1.0,-3.0);
    }

    public Location getStaffLoc(){
        return location.clone().add(0.0,1.0,3.0);
    }
}
