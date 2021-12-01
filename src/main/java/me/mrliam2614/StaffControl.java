package me.mrliam2614;

import me.mrliam2614.commands.CmdFreeze;
import me.mrliam2614.commands.CmdUnfreeze;
import me.mrliam2614.events.*;
import me.mrliam2614.freeze.FreezeHandler;
import me.mrliam2614.structure.StructureHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffControl extends JavaPlugin {

    private static StaffControl staffControl;
    private FacilitisAPI facilitisAPI;
    private FreezeHandler freezeHandler;

    @Override
    public void onEnable() {
        staffControl = this;
        facilitisAPI = FacilitisAPI.getInstance();
        facilitisAPI.messages.EnableMessage(this);

        saveDefaultConfig();
        saveConfig();

        freezeHandler = new FreezeHandler();
        registerListener();
        registerCommands();
        checkMaterials();
    }

    private void checkMaterials() {
        if(!(Material.valueOf(getConfig().getString("structure.central").toUpperCase()).isBlock() &&
                Material.valueOf(getConfig().getString("structure.central2").toUpperCase()).isBlock() &&
                Material.valueOf(getConfig().getString("structure.player").toUpperCase()).isBlock() &&
                Material.valueOf(getConfig().getString("structure.staff").toUpperCase()).isBlock() &&
                Material.valueOf(getConfig().getString("structure.borders").toUpperCase()).isBlock() &&
                Material.valueOf(getConfig().getString("structure.cage").toUpperCase()).isBlock())
        ){
            facilitisAPI.console.sendMessage(this, "CHECK THE MATERIAL OF THE BLOCKS", "critical");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    private void registerCommands() {
        getCommand("freeze").setExecutor(new CmdFreeze());
        getCommand("unfreeze").setExecutor(new CmdUnfreeze());
    }

    private void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChatDivision(), this);
        pm.registerEvents(new PlayerDamage(), this);
        pm.registerEvents(new RunCommand(), this);
        pm.registerEvents(new TeleportEvent(), this);
        pm.registerEvents(new PlayerMove(), this);
    }

    @Override
    public void onDisable() {
        facilitisAPI.messages.DisableMessage(this);
    }

    public FacilitisAPI getFacilitisAPI() {
        return facilitisAPI;
    }

    public FreezeHandler getFreezeHandler() {
        return freezeHandler;
    }

    public static StaffControl getInterface() {
        return staffControl;
    }
}
