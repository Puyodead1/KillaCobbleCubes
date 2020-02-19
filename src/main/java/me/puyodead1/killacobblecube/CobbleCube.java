package me.puyodead1.killacobblecube;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;

public class CobbleCube {

    private Location center;
    private int minX, minY, minZ, maxX, maxY, maxZ;

    public static HashMap<Location, CobbleCube> cobbleCubes = new HashMap<>();

    public CobbleCube(Location center, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.center = center;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

        cobbleCubes.put(center, this);
    }

    public Location getCenter() {
        return center;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public static HashMap<Location, CobbleCube> getCobbleCubes() {
        return cobbleCubes;
    }

    public void generateFrame() {
        // TODO: create frame
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                for (int z = 0; z < 5; z++) {

                    Block bl = getCenter().getBlock().getRelative(x, y, z);
                    Bukkit.getServer().broadcastMessage(x + ";" + y + ";" + z + ";;;" + bl.getX() + ";" + bl.getY() + ";" + bl.getZ());

                    if (x == 0 || y == 0 || z == 0) {
                        bl.setType(Material.BEDROCK);
                    }
                }
            }
        }
    }

    public void generateOre() {
        // TODO: add a random ore to the inside of the cube
    }
}
