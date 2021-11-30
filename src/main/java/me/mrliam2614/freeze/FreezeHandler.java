package me.mrliam2614.freeze;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FreezeHandler {
    private final List<Freeze> freezeList;

    public FreezeHandler() {
        freezeList = new ArrayList<>();
    }

    public boolean addFreeze(Freeze freeze) {
        if (getFrozen(freeze.getPlayerUUID()) == null) {
            freezeList.add(freeze);
            return true;
        }
        return false;
    }

    public boolean removeFreeze(Freeze freeze) {
        if (getFrozen(freeze.getPlayerUUID()) != null) {
            freezeList.remove(freeze);
            return true;
        }
        return false;
    }

    public boolean isFreezed(UUID target) {
        return getFrozen(target) != null;
    }

    public boolean isFreezing(UUID staff) {
        return getFreezer(staff) != null;
    }

    public Freeze getFrozen(UUID target) {
        for (Freeze freeze : freezeList) {
            if (freeze.getPlayerUUID() == target) {
                return freeze;
            }
        }
        return null;
    }

    public Freeze getFreezer(UUID staff) {
        for (Freeze freeze : freezeList) {
            if (freeze.getStaffUUID() == staff) {
                return freeze;
            }
        }
        return null;
    }

    public UUID getFreezed(UUID byStaff) {
        for (Freeze freeze : freezeList) {
            if (freeze.getStaffUUID() == byStaff) {
                return freeze.getPlayerUUID();
            }
        }
        return null;
    }

    public UUID getFreezing(UUID byUser) {
        for (Freeze freeze : freezeList) {
            if (freeze.getPlayerUUID() == byUser) {
                return freeze.getStaffUUID();
            }
        }
        return null;
    }
}
