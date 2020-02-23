package me.puyodead1.killacobblecube.Events;

import com.wasteofplastic.askyblock.Island;
import me.puyodead1.killacobblecube.CobbleCube;
import me.puyodead1.killacobblecube.KillaCobblecube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import me.puyodead1.killacobblecube.OreGenerationTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

            if (KillaCobblecube.askyblockapi.hasIsland(e.getPlayer().getUniqueId())) {
                Island island = KillaCobblecube.askyblockapi.getIslandOwnedBy(e.getPlayer().getUniqueId());
                final ArrayList<Block> islandBlocks = KillaCobblecubeUtils.getBlocksCheck(island, e.getBlock().getY());

                if(islandBlocks.contains(e.getBlock())) {
                    final int cubesize = Integer.parseInt(ChatColor.stripColor(e.getPlayer().getItemInHand().getItemMeta().getLore().get(3)).split(": ")[1].split("x")[0]);

                    final ArrayList<Block> blocks = KillaCobblecubeUtils.getBlocks(e.getBlock(), cubesize + 2);
                    blocks.remove(e.getBlock());
                    for (Block b : blocks) {
                        if (!b.getType().equals(Material.AIR)) {
                            b.setType(Material.GLASS);
                            canPlace = false;
                            break;
                        }
                    }

                    if (canPlace && !blocks.contains(e.getPlayer().getLocation().getBlock())) {
                        final CobbleCube cobbleCube = new CobbleCube(e.getBlock().getLocation(), cubesize);
                        cobbleCube.generateFrame();
                        e.getPlayer().getInventory().remove(e.getItemInHand());
                    } else {
                        KillaCobblecubeUtils.sendPlayer(e.getPlayer(), "&cCannot place cube here!");
                    }
                } else {
                    KillaCobblecubeUtils.sendPlayer(e.getPlayer(), "&cYou can only place cubes on your island!");
                }

            } else {
                KillaCobblecubeUtils.sendPlayer(e.getPlayer(), "&cYou can only place cubes on your island!");
            }
        }
    }
}
