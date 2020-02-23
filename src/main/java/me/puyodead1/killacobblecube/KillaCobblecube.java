package me.puyodead1.killacobblecube;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import me.puyodead1.killacobblecube.Commands.CobbleCubeCommand;
import me.puyodead1.killacobblecube.Events.BlockBreak;
import me.puyodead1.killacobblecube.Events.BlockPlace;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class KillaCobblecube extends JavaPlugin {

    public static KillaCobblecube plugin;
    public static ASkyBlockAPI askyblockapi;
    public static WeightedRandomMaterial<Material> generationMaterials = new WeightedRandomMaterial<>();
    public static File dataFile;
    public static YamlConfiguration dataYaml;

    public final static String PREFIX = "&7[&dKilla Coblecube&7] ";

    @Override
    public void onEnable() {
        plugin = this;

        KillaCobblecubeUtils.sendConsole(PREFIX + "&b=============================================================");

        // Ensure essentials is installed
        if (!Bukkit.getPluginManager().getPlugin("ASkyBlock").isEnabled()) {
            KillaCobblecubeUtils.sendConsole(KillaCobblecubeUtils.Color("&cASkyBlock not enabled or not installed! Plugin will be disabled!"));
            Bukkit.getPluginManager().disablePlugin(this);
        }

        initConfig();
        initEvents();
        InitCommands();
        InitCubeItems();
        InitData();
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
        Bukkit.getScheduler().cancelTasks(this);
        generationMaterials = null;

        CobbleCube.saveCubes();
    }

    public void initConfig() {
        final long STARTED = System.currentTimeMillis();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        for (String s : getConfig().getStringList("settings.generation blocks")) {
            final Material material = Material.valueOf(s);
            generationMaterials.addEntry(material, getConfig().getInt("settings.generation block rarities." + s));
        }

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Configuration &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void initEvents() {
        final long STARTED = System.currentTimeMillis();

        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Events &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void InitCubeItems() {
        final long STARTED = System.currentTimeMillis();

        for (int x = 1; x < getConfig().getInt("settings.max size"); x++) {
            new CobbleCubeItem(x);
        }

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded " + CobbleCubeItem.getCobblecubes().size() + " Cube Items &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    public void InitCommands() {
        final long STARTED = System.currentTimeMillis();

        getCommand("cobblecubes").setExecutor(new CobbleCubeCommand());

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Commands &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    /**
     * This method loads the data file which stores the cubes
     */
    public void InitData() {
        final long STARTED = System.currentTimeMillis();

        dataFile = new File(getDataFolder() + File.separator + "_data.yml");
        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                KillaCobblecubeUtils.sendConsole(PREFIX + "&cFAILED TO CREATE DATA FILE!");
            }
        }
        dataYaml = YamlConfiguration.loadConfiguration(dataFile);

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded Data File &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }

    /**
     * this method loads the existing cubes and starts OreGenerationTasks
     */
    public void InitCubes() {
        final long STARTED = System.currentTimeMillis();

        CobbleCube.loadCubes();

        KillaCobblecubeUtils.sendConsole(PREFIX + "&bLoaded " + CobbleCube.getCobbleCubes().size() + " Cubes &e(took " + (System.currentTimeMillis() - STARTED) + "ms)");
    }
}
