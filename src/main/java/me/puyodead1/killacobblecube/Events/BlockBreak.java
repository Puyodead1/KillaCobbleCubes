package me.puyodead1.killacobblecube.Events;

import com.wasteofplastic.askyblock.Island;
import me.puyodead1.killacobblecube.CobbleCube;
import me.puyodead1.killacobblecube.KillaCobblecube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BlockBreak implements Listener {

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        final int exp = e.getExpToDrop();

        if (KillaCobblecube.askyblockapi.hasIsland(e.getPlayer().getUniqueId())) {
            Island island = KillaCobblecube.askyblockapi.getIslandOwnedBy(e.getPlayer().getUniqueId());
            final ArrayList<Block> islandBlocks = KillaCobblecubeUtils.getBlocksCheck(island, e.getBlock().getY());

            if(islandBlocks.contains(e.getBlock())) {
                e.setCancelled(true);
                final CobbleCube cube = CobbleCube.valueOf(e.getBlock().getLocation());
                if (cube != null) {
                    if (e.getPlayer().hasPermission("cobblecubes.blockbreak.giveblock")) {
                        // add drop to inv
                        for(ItemStack drop : e.getBlock().getDrops()) {
                            e.getPlayer().getInventory().addItem(drop);
                        }
                    }
                    if (e.getPlayer().hasPermission("cobblecubes.blockbreak.giveexp")) {
                        // add exp
                        e.getPlayer().giveExp(e.getExpToDrop());
                    }
                }

                e.getBlock().setType(Material.AIR);
            } else {
                e.setCancelled(true);
                e.getPlayer().sendMessage("you do not have permission to use this cube!");
            }

        } else {
            e.setCancelled(true);
            e.getPlayer().sendMessage("you do not have permission to use this cube!");
        }
        // TODO: check if the block broken was inside the ore generation location of a cube on the players island, set cancelled and add item to inv and give exp
    }
}
