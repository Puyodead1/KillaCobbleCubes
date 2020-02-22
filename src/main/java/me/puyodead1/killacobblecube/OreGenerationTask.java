package me.puyodead1.killacobblecube;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

public class OreGenerationTask {

    private CobbleCube cube;
    private int taskID;

    public OreGenerationTask(CobbleCube cube) {
        this.cube = cube;
        final BukkitScheduler scheduler = Bukkit.getScheduler();
        this.taskID = scheduler.scheduleSyncRepeatingTask(KillaCobblecube.plugin, new Runnable() {
            @Override
            public void run() {
                cube.generateOre();
            }
            // delay the generation because the cube is full when spawned.
        }, 20L * KillaCobblecube.plugin.getConfig().getInt("settings.generation delay"), 20L * KillaCobblecube.plugin.getConfig().getInt("settings.generation delay"));
    }

    public CobbleCube getCube() {
        return cube;
    }

    public int getTaskID() {
        return taskID;
    }

    public void cancel() {
        final BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.cancelTask(taskID);
    }
}
