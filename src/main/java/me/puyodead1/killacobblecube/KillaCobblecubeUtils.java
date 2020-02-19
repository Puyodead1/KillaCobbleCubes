package me.puyodead1.killacobblecube;

import com.deanveloper.skullcreator.SkullCreator;
import com.wasteofplastic.askyblock.Island;
import com.wasteofplastic.askyblock.util.Pair;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KillaCobblecubeUtils {

    public static String Color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage(Color(msg));
    }

    public static void sendPlayer(Player player, String msg) {
        player.sendMessage(Color(msg));
    }

    public static void sendSender(CommandSender sender, String msg) {
        sender.sendMessage(Color(msg));
    }

    public static List<String> ColorList(List<String> stringList, String cubesize) {
        List<String> newList = new ArrayList<>();
        for (String s : stringList) {
            newList.add(Color(s).replace("%CUBE_SIZE%", cubesize));
        }

        return newList;
    }

    public static ArrayList<Block> getBlocksCheck(Island island, int y) {
        ArrayList<Block> blocks = new ArrayList<>();

        for (int x = island.getMinProtectedX(); x < (island.getMinProtectedX() + island.getProtectionSize()); x++) {
            for (int z = island.getMinProtectedZ(); z < (island.getMinProtectedZ() + island.getProtectionSize()); z++) {
                Location loc = new Location(island != null ? island.getCenter().getWorld() : null, x, y, z);
                blocks.add(loc.getBlock());
            }
        }
        return blocks;
    }

    public static ArrayList<Block> getBlocks(Block start, int radius) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++) {
            for (double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++) {
                for (double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++) {
                    Location loc = new Location(start.getWorld(), x, y, z);
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;
    }

    public static void pasteHollowCube(Location loc, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {

                    Block bl = loc.getBlock().getRelative(x, y, z);

                    if (x == 0 || y == 0 || z == 0) {
                        if (x == size - 1 || y == size - 1 || z == size - 1) {
                            bl.setType(Material.BEDROCK);
                        }
                    }
                }
            }
        }
    }
}
