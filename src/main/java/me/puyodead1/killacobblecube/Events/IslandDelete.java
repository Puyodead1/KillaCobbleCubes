package me.puyodead1.killacobblecube.Events;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;
import com.wasteofplastic.askyblock.events.IslandDeleteEvent;
import me.puyodead1.killacobblecube.CobbleCube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class IslandDelete implements Listener {

    @EventHandler
    public void IslandDeleteEvent(IslandDeleteEvent e) {
        final ArrayList<Location> islandBlocks = KillaCobblecubeUtils.getBlocks(e.getLocation(), 100);


    }
}
