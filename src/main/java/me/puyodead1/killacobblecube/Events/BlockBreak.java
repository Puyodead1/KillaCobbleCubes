package me.puyodead1.killacobblecube.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        final Player player = e.getPlayer();
        final Block block = e.getBlock();
        final int exp = e.getExpToDrop();
        // TODO: check if the block broken was inside the ore generation location of a cube on the players island, set cancelled and add item to inv and give exp
    }
}
