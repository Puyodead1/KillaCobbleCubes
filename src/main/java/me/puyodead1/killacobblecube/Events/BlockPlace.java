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
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class BlockPlace implements Listener {

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

                    final ArrayList<Location> blocks = KillaCobblecubeUtils.getBlocks(e.getBlock().getLocation(), cubesize);
                    final ArrayList<Block> blocksInWay = new ArrayList<>();
                    blocks.remove(e.getBlock().getLocation());
                    for (Location l : blocks) {
                        final Block b = l.getBlock();
                        if (!b.getType().equals(Material.AIR)) {
                            Bukkit.broadcastMessage(b.getType().name());
                            blocksInWay.add(b);
                        }
                    }

                    final Vector min = new Vector(blocks.get(0).getX(), blocks.get(0).getY(), blocks.get(0).getZ());
                    final Vector max = new Vector(blocks.get(blocks.size() - 1).getX(), blocks.get(blocks.size() - 1).getY(), blocks.get(blocks.size() - 1).getZ());

                    if (blocksInWay.size() == 0) {
                        if(!KillaCobblecubeUtils.isInAABB(e.getPlayer(), min, max)) {
                            final CobbleCube cobbleCube = new CobbleCube(e.getBlock().getLocation(), cubesize);
                            cobbleCube.generateFrame();
                            e.getPlayer().getInventory().getItemInHand().setAmount(e.getPlayer().getInventory().getItemInHand().getAmount() - 1);
                        } else {
                            KillaCobblecubeUtils.sendPlayer(e.getPlayer(), "&cYou are in the way!");
                        }
                    } else {
                        KillaCobblecubeUtils.sendPlayer(e.getPlayer(), "&cCannot place cube here! There are " + blocksInWay.size() + " blocks in the way!");
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
