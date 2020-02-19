package me.puyodead1.killacobblecube;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class CobbleCube {

    private Location center;
    private int size;

    public static HashMap<Location, CobbleCube> cobbleCubes = new HashMap<>();

    public CobbleCube(Location center, int size) {
        this.center = center;

        cobbleCubes.put(center, this);
    }

    public int getSize() {
        return size;
    }

    public Location getCenter() {
        return center;
    }

    public static HashMap<Location, CobbleCube> getCobbleCubes() {
        return cobbleCubes;
    }

    public void generateFrame() {
        getCenter().getBlock().setType(Material.BEDROCK);
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                for (int z = 0; z < getSize(); z++) {

                    Block bl = getCenter().getBlock().getRelative(x, y, z);

                    if ((x == 0 || x == getSize() - 1) && (y == 0 || y == getSize() - 1) || (x == 0 || x == getSize() - 1) && (z == 0 || z == getSize() - 1) || (y == 0 || y == getSize() - 1) && (z == 0 || z == getSize() - 1)) {
                        bl.setType(Material.BEDROCK);
                    }
                }
            }
        }
    }

    public void generateOre() {
        // TODO: add a random ore to the inside of the cube every 7 seonds
        final ArrayList<Block> blocks = KillaCobblecubeUtils.getBlocks(getCenter().getBlock(), 3);
        for (Block b : blocks) {
            b.setType(Material.GLASS);
        }
    }
}
