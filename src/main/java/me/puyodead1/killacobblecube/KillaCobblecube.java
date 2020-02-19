package me.puyodead1.killacobblecube;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import me.puyodead1.killacobblecube.Commands.CobbleCubeCommand;
import me.puyodead1.killacobblecube.Events.BlockPlace;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class KillaCobblecube extends JavaPlugin {

    public static KillaCobblecube plugin;
    public static ASkyBlockAPI askyblockapi;

    private final String PREFIX = "&7[&dKilla Coblecube&7] ";

    @Override
    public void onEnable() {
        plugin = this;

        KillaCobblecubeUtils.sendConsole(PREFIX + "&b=============================================================");

        // Ensure essentials is installed
        if(!Bukkit.getPluginManager().getPlugin("ASkyBlock").isEnabled()) {
            KillaCobblecubeUtils.sendConsole(KillaCobblecubeUtils.Color("&cASkyBlock not enabled or not installed! Plugin will be disabled!"));
            Bukkit.getPluginManager().disablePlugin(this);
        }

        initConfig();
        initEvents();
        InitCommands();
        InitCubes();

        KillaCobblecubeUtils.sendConsole(PREFIX + "&d========================");
        KillaCobblecubeUtils.sendConsole(PREFIX + "&bAuthor: &ePuyodead1");
        KillaCobblecubeUtils.sendConsole(PREFIX + "&b" + getDescription().getName() + " Version: &e" + getServer().getPluginManager().getPlugin(this.getDescription().getName()).getDescription().getVersion());
        KillaCobblecubeUtils.sendConsole(PREFIX + "&bASkyBlock Version: &e" + getServer().getPluginManager().getPlugin("ASkyBlock").getDescription().getVersion());
        KillaCobblecubeUtils.sendConsole(PREFIX + "&bMinecraft Version: &e" + getServer().getVersion());
        KillaCobblecubeUtils.sendConsole(PREFIX + "&b=============================================================");

        askyblockapi = ASkyBlockAPI.getInstance();
    }

    @Override
    public void onDisable() {
        CobbleCubeItem.getCobblecubes().clear();
        plugin = null;
    }

    public void initConfig() {
        final long STARTED = System.currentTimeMillis();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Configuration &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void initEvents() {
        final long STARTED = System.currentTimeMillis();

        getServer().getPluginManager().registerEvents(new BlockPlace(), this);

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Events &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void InitCubes() {
        final long STARTED = System.currentTimeMillis();

        for (int x = 1; x < getConfig().getInt("settings.max size"); x++) {
            new CobbleCubeItem(x);
        }

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded " + CobbleCubeItem.getCobblecubes().size() + " Cubes &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void InitCommands() {
        final long STARTED = System.currentTimeMillis();

        getCommand("cobblecubes").setExecutor(new CobbleCubeCommand());

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Commands &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }
}
