package me.puyodead1.killacobblecube;

import com.wasteofplastic.askyblock.Island;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

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
                Location loc = new Location(island.getCenter().getWorld(), x, y, z);
                blocks.add(loc.getBlock());
            }
        }
        return blocks;
    }

    public static ArrayList<Block> getBlocksCheck(Island island) {
        ArrayList<Block> blocks = new ArrayList<>();

        for (int x = island.getMinProtectedX(); x < (island.getMinProtectedX() + island.getProtectionSize()); x++) {
            for (int y = 0; y < island.getCenter().getWorld().getMaxHeight(); y++) {
                for (int z = island.getMinProtectedZ(); z < (island.getMinProtectedZ() + island.getProtectionSize()); z++) {
                    Location loc = new Location(island.getCenter().getWorld(), x, y, z);
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;
    }

    public static ArrayList<Location> getBlocks(Location loc1, Location loc2) {
        final ArrayList<Location> blocks = new ArrayList<>();

        for (double x = loc1.getX(); x <= loc2.getX(); x++) {
            for (double y = loc1.getY(); y <= loc2.getY(); y++) {
                for (double z = loc1.getZ(); z <= loc2.getZ(); z++) {
                    final Location block = new Location(loc1.getWorld(), (int) x, (int) y, (int) z);
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }
    public static ArrayList<Location> getBlocks(Location location, int size) {
        final ArrayList<Location> blocks = new ArrayList<>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {

                    final Block bl = new Location(location.getWorld(), x, y, z).getBlock();
                    blocks.add(bl.getLocation());
                }
            }
        }

        return blocks;
    }

    public static boolean isInAABB(Player player, Vector min, Vector max) {
        final double x = player.getLocation().getX();
        final double y = player.getLocation().getY();
        final double z = player.getLocation().getZ();
        return x >= min.getX() && x <= max.getX() && y >= min.getY() && y <= max.getY() && z >= min.getZ() && z <= max.getZ();
    }
}
