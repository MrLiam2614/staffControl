package me.mrliam2614.storage;

import me.mrliam2614.StaffControl;
import org.bukkit.Material;

public class ConfigStorage {
    private final StaffControl plugin;
    private Material central;
    private Material central2;
    private Material player;
    private Material staff;
    private Material borders;
    private Material cage;
    private Material air;


    public ConfigStorage(){
        this.plugin = StaffControl.getInstance();
        loadData();
    }

    private void loadData() {
        central = getMaterial(plugin.getConfig().getString("structure.central"));
        central2 = getMaterial(plugin.getConfig().getString("structure.central2"));
        player = getMaterial(plugin.getConfig().getString("structure.player"));
        staff = getMaterial(plugin.getConfig().getString("structure.staff"));
        borders = getMaterial(plugin.getConfig().getString("structure.borders"));
        cage = getMaterial(plugin.getConfig().getString("structure.cage"));
        air = Material.AIR;
    }

    private Material getMaterial(String mat) {
        return Material.valueOf(mat);
    }

    public Material getCentral() {
        return central;
    }

    public Material getCentral2() {
        return central2;
    }

    public Material getPlayer() {
        return player;
    }

    public Material getStaff() {
        return staff;
    }

    public Material getBorders() {
        return borders;
    }

    public Material getCage() {
        return cage;
    }

    public Material getAir() {
        return air;
    }
}
