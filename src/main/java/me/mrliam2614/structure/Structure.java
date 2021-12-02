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

    public Location getPlayerLoc() {
        Location loc = location.clone().add(0.0, 1.1, -3.0);
        loc.setYaw(0);
        loc.setPitch(0);

        return loc;
    }

    public Location getStaffLoc() {
        Location loc = location.clone().add(0.0, 1.1, 3.0);
        loc.setYaw(180);
        loc.setPitch(0);
        return loc;
    }
}
