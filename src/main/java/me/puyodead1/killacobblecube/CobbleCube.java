package me.puyodead1.killacobblecube;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CobbleCube {

    private Location center;
    private int size;
    private Location oreGenCubeMin, oreGenCubeMax;
    private ArrayList<Location> oreGenCubeBlocks;

    public static HashMap<Location, CobbleCube> cobbleCubes = new HashMap<>();
    private ArrayList<Block> blocks;

    public CobbleCube(Location center, int size) {
        this.center = center;
        this.size = size;

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

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Location> getOreGenCubeBlocks() {
        return oreGenCubeBlocks;
    }

    public Location getOreGenCubeMin() {
        return oreGenCubeMin;
    }

    public void setOreGenCubeMin(Location oreGenCubeMin) {
        this.oreGenCubeMin = oreGenCubeMin;
    }

    public Location getOreGenCubeMax() {
        return oreGenCubeMax;
    }

    public void setOreGenCubeMax(Location oreGenCubeMax) {
        this.oreGenCubeMax = oreGenCubeMax;
    }

    public void generateFrame() {
        final int boxSize = size + 2;
        final ArrayList<Block> blocks = new ArrayList<>();

        for (int x = 0; x < boxSize; x++) {
            for (int y = 0; y < boxSize; y++) {
                for (int z = 0; z < boxSize; z++) {

                    Block bl = getCenter().getBlock().getRelative(x, y, z);

                    if ((x == 0 || x == boxSize - 1) && (y == 0 || y == boxSize - 1) || (x == 0 || x == boxSize - 1) && (z == 0 || z == boxSize - 1) || (y == 0 || y == boxSize - 1) && (z == 0 || z == boxSize - 1)) {
                        bl.setType(KillaCobblecube.plugin.getConfig().getString("settings.cube material") != null ? Material.valueOf(KillaCobblecube.plugin.getConfig().getString("settings.cube material")) : Material.BEDROCK);
                        blocks.add(bl);
                    }
                }
            }
        }

        setBlocks(blocks);

        final ArrayList<Block> innerCubeBlocks = getBlocks();
        final Location blockLocation1 = innerCubeBlocks.get(0).getLocation();
        final Location blockLocation2 = innerCubeBlocks.get(innerCubeBlocks.size() - 1).getLocation();

        oreGenCubeMin = new Location(blockLocation1.getWorld(), blockLocation1.getX() + 1, blockLocation1.getY() + 1, blockLocation1.getZ() + 1);
        oreGenCubeMax = new Location(blockLocation2.getWorld(), blockLocation2.getX() - 1, blockLocation2.getY() - 1, blockLocation2.getZ() - 1);
        oreGenCubeBlocks = KillaCobblecubeUtils.getBlocks(oreGenCubeMin, oreGenCubeMax);

        // fill the cube with random ores
        for (Location l : oreGenCubeBlocks) {
            l.getBlock().setType(KillaCobblecube.generationMaterials.getRandom());
        }
    }

    public void generateOre() {
        // TODO: add a random ore to the inside of the cube every 7 seconds
        final ArrayList<Location> emptyBlocks = new ArrayList<>();

        for (Location loc : oreGenCubeBlocks) {
            if (loc.getBlock().getType().equals(Material.AIR)) {
                emptyBlocks.add(loc);
            }
        }

        for(Location loc : emptyBlocks) {
            loc.getBlock().setType(KillaCobblecube.generationMaterials.getRandom());
        }
    }
}
