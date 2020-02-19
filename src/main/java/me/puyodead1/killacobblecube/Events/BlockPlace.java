package me.puyodead1.killacobblecube.Events;

import com.wasteofplastic.askyblock.Island;
import me.puyodead1.killacobblecube.CobbleCube;
import me.puyodead1.killacobblecube.KillaCobblecube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

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
                    final ArrayList<Block> cubeblocks = KillaCobblecubeUtils.getBlocks(e.getBlock(), cubesize);

                    // TODO check if there are blocks in the way
                    // TODO: Fix cube generation
                    // TODO: generate cube structure
                    // TODO: add inner cube dimentions to CobbleCube constructor
                    // TODO: start generation task
                    final int minX = e.getBlock().getX();
                    final int minY = e.getBlock().getY();
                    final int minZ = e.getBlock().getZ();
                    final int maxX = e.getBlock().getX() + cubesize;
                    final int maxY = e.getBlock().getX() + cubesize;
                    final int maxZ = e.getBlock().getX() + cubesize;

                    final CobbleCube cobbleCube = new CobbleCube(e.getBlock().getLocation(), minX, minY, minZ, maxX, maxY, maxZ);
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
