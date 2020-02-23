package me.puyodead1.killacobblecube.Events;

import com.wasteofplastic.askyblock.Island;
import me.puyodead1.killacobblecube.CobbleCube;
import me.puyodead1.killacobblecube.KillaCobblecube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import me.puyodead1.killacobblecube.OreGenerationTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class BlockPlace implements Listener {
    private boolean canPlace = true;

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        if(e.getItemInHand().getType().equals(Material.SKULL_ITEM) && e.getItemInHand().getItemMeta().getDisplayName().equals(KillaCobblecubeUtils.Color(KillaCobblecube.plugin.getConfig().getString("settings.cobblecube.name")))) {
            // item is a cobblecube
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);

            if (KillaCobblecube.askyblockapi.hasIsland(e.getPlayer().getUniqueId())) {
                Island island = KillaCobblecube.askyblockapi.getIslandOwnedBy(e.getPlayer().getUniqueId());
                final ArrayList<Block> islandBlocks = KillaCobblecubeUtils.getBlocksCheck(island, e.getBlock().getY());

                if(islandBlocks.contains(e.getBlock())) {
                    final int cubesize = Integer.parseInt(ChatColor.stripColor(e.getPlayer().getItemInHand().getItemMeta().getLore().get(3)).split(": ")[1].split("x")[0]);

                    // TODO: check if there are blocks in the way

                    final CobbleCube cobbleCube = new CobbleCube(e.getBlock().getLocation(), cubesize);
                    cobbleCube.generateFrame();
                } else {
                    e.getPlayer().sendMessage("can only place on island");
                }

            } else {
                e.getPlayer().sendMessage("can only place on island");
            }
        }
    }
}
