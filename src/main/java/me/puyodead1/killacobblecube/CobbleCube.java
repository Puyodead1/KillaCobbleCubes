package me.puyodead1.killacobblecube;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CobbleCube {
    private Location center;
    private int size;
    private Location oreGenCubeMin, oreGenCubeMax, cubeMin, cubeMax;
    private ArrayList<Location> oreGenCubeBlocks;
    private OreGenerationTask task;

    public static HashMap<Location, CobbleCube> cobbleCubes = new HashMap<>();
    private ArrayList<Location> blocks;

    public CobbleCube(Location center, int size) {
        this.center = center;
        this.size = size;

        cobbleCubes.put(center, this);
    }

    public static CobbleCube valueOf(Location location) {
        for(CobbleCube c : getCobbleCubes().values()) {
            for(Location loc : c.getOreGenCubeBlocks()) {
                if(loc.equals(location)) {
                    return c;
                }
            }
        }
        return null;
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

    public ArrayList<Location> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Location> blocks) {
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

    public OreGenerationTask getTask() {
        return task;
    }

    public Location getCubeMin() {
        return cubeMin;
    }

    public Location getCubeMax() {
        return cubeMax;
    }

    public void setOreGenCubeBlocks(ArrayList<Location> oreGenCubeBlocks) {
        this.oreGenCubeBlocks = oreGenCubeBlocks;
    }

    public void setCubeMin(Location cubeMin) {
        this.cubeMin = cubeMin;
    }

    public void setCubeMax(Location cubeMax) {
        this.cubeMax = cubeMax;
    }

    public static void saveCubes() {
        int saved = 0;
        for (int x = 0; x < CobbleCube.getCobbleCubes().size(); x++) {
            final CobbleCube c = (CobbleCube) CobbleCube.getCobbleCubes().values().toArray()[x];
            final YamlConfiguration dataYaml = KillaCobblecube.dataYaml;
            final ConfigurationSection section = dataYaml.createSection(String.valueOf(x));

            section.set("size", c.getSize());

            final ConfigurationSection centerSection = section.createSection("center");
            centerSection.set("x", c.getCenter().getX());
            centerSection.set("y", c.getCenter().getY());
            centerSection.set("z", c.getCenter().getZ());
            centerSection.set("world", c.getCenter().getWorld().getName());

            final ConfigurationSection oreMinSection = section.createSection("ore min");
            oreMinSection.set("x", c.getOreGenCubeMin().getX());
            oreMinSection.set("y", c.getOreGenCubeMin().getY());
            oreMinSection.set("z", c.getOreGenCubeMin().getZ());
            oreMinSection.set("world", c.getOreGenCubeMin().getWorld().getName());

            final ConfigurationSection oreMaxSection = section.createSection("ore max");
            oreMaxSection.set("x", c.getOreGenCubeMax().getX());
            oreMaxSection.set("y", c.getOreGenCubeMax().getY());
            oreMaxSection.set("z", c.getOreGenCubeMax().getZ());
            oreMaxSection.set("world", c.getOreGenCubeMax().getWorld().getName());

            final ConfigurationSection cubeMinSection = section.createSection("cube min");
            cubeMinSection.set("x", c.getCubeMin().getX());
            cubeMinSection.set("y", c.getCubeMin().getY());
            cubeMinSection.set("z", c.getCubeMin().getZ());
            cubeMinSection.set("world", c.getCubeMin().getWorld().getName());

            final ConfigurationSection cubeMaxSection = section.createSection("cube max");
            cubeMaxSection.set("x", c.getCubeMax().getX());
            cubeMaxSection.set("y", c.getCubeMax().getY());
            cubeMaxSection.set("z", c.getCubeMax().getZ());
            cubeMaxSection.set("world", c.getCubeMax().getWorld().getName());

            try {
                dataYaml.save(KillaCobblecube.dataFile);
            } catch (IOException e) {
                e.printStackTrace();
                KillaCobblecubeUtils.sendConsole(KillaCobblecube.PREFIX + "&cFAILED TO SAVE CUBE!");
            }
            saved++;
        }
        KillaCobblecubeUtils.sendConsole(KillaCobblecube.PREFIX + "&bSaved " + saved + " cubes.");
    }

    public static void loadCubes() {
        final YamlConfiguration dataYaml = KillaCobblecube.dataYaml;
        for(String s : dataYaml.getKeys(false)) {
            final int size = dataYaml.getInt(s + ".size");
            final Location centerLoc = new Location(Bukkit.getServer().getWorld(dataYaml.getString(s + ".center.world")), dataYaml.getInt(s + ".center.x"), dataYaml.getDouble(s + ".center.y"), dataYaml.getDouble(s + ".center.z"));
            final Location oreMin = new Location(Bukkit.getServer().getWorld(dataYaml.getString(s + ".ore min.world")), dataYaml.getInt(s + ".ore min.x"), dataYaml.getDouble(s + ".ore min.y"), dataYaml.getDouble(s + ".ore min.z"));
            final Location oreMax = new Location(Bukkit.getServer().getWorld(dataYaml.getString(s + ".ore max.world")), dataYaml.getInt(s + ".ore max.x"), dataYaml.getDouble(s + ".ore max.y"), dataYaml.getDouble(s + ".ore max.z"));
            final Location cubeMin = new Location(Bukkit.getServer().getWorld(dataYaml.getString(s + ".cube min.world")), dataYaml.getInt(s + ".cube min.x"), dataYaml.getDouble(s + ".cube min.y"), dataYaml.getDouble(s + ".cube min.z"));
            final Location cubeMax = new Location(Bukkit.getServer().getWorld(dataYaml.getString(s + ".cube max.world")), dataYaml.getInt(s + ".cube max.x"), dataYaml.getDouble(s + ".cube max.y"), dataYaml.getDouble(s + ".cube max.z"));

            final CobbleCube cube = new CobbleCube(centerLoc, size);
            cube.setBlocks(KillaCobblecubeUtils.getBlocks(cubeMin, cubeMax));
            cube.setCubeMin(cubeMin);
            cube.setCubeMax(cubeMax);
            cube.setOreGenCubeMax(oreMax);
            cube.setOreGenCubeMin(oreMin);
            cube.setOreGenCubeBlocks(KillaCobblecubeUtils.getBlocks(oreMin, oreMax));

            cube.task = new OreGenerationTask(cube);
        }

        try {
            KillaCobblecube.dataFile.delete();
            KillaCobblecube.dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            KillaCobblecubeUtils.sendConsole(KillaCobblecube.PREFIX + "&bcFAILED TO EMPTY DATA FILE!");
        }
    }

    public void generateFrame() {
        final int boxSize = size + 2;
        final ArrayList<Location> blocks = new ArrayList<>();

        for (int x = 0; x < boxSize; x++) {
            for (int y = 0; y < boxSize; y++) {
                for (int z = 0; z < boxSize; z++) {

                    Block bl = getCenter().getBlock().getRelative(x, y, z);

                    if ((x == 0 || x == boxSize - 1) && (y == 0 || y == boxSize - 1) || (x == 0 || x == boxSize - 1) && (z == 0 || z == boxSize - 1) || (y == 0 || y == boxSize - 1) && (z == 0 || z == boxSize - 1)) {
                        bl.setType(KillaCobblecube.plugin.getConfig().getString("settings.cube material") != null ? Material.valueOf(KillaCobblecube.plugin.getConfig().getString("settings.cube material")) : Material.BEDROCK);
                        blocks.add(bl.getLocation());
                    }
                }
            }
        }

        setBlocks(blocks);

        cubeMin = blocks.get(0);
        cubeMax = blocks.get(blocks.size() - 1);
        final Location blockLocation1 = blocks.get(0);
        final Location blockLocation2 = blocks.get(blocks.size() - 1);

        oreGenCubeMin = new Location(blockLocation1.getWorld(), blockLocation1.getX() + 1, blockLocation1.getY() + 1, blockLocation1.getZ() + 1);
        oreGenCubeMax = new Location(blockLocation2.getWorld(), blockLocation2.getX() - 1, blockLocation2.getY() - 1, blockLocation2.getZ() - 1);
        oreGenCubeBlocks = KillaCobblecubeUtils.getBlocks(oreGenCubeMin, oreGenCubeMax);

        // fill the cube with random ores
        for (Location l : oreGenCubeBlocks) {
            l.getBlock().setType(KillaCobblecube.generationMaterials.getRandom());
        }

        task = new OreGenerationTask(this);
    }

    public void generateOre() {
        final ArrayList<Location> emptyBlocks = new ArrayList<>();

        for (Location loc : oreGenCubeBlocks) {
            if (loc.getBlock().getType().equals(Material.AIR)) {
                emptyBlocks.add(loc);
            }
        }

        if (emptyBlocks.size() > 0) { // only run if there are empty blocks
            final Random rand = new Random();
            final int r = rand.nextInt(KillaCobblecube.plugin.getConfig().getInt("settings.max block gen per cycle"));

            for (int x = 0; x < r; x++) {
                try {
                    final Location l = emptyBlocks.get(rand.nextInt(emptyBlocks.size()));
                    l.getBlock().setType(KillaCobblecube.generationMaterials.getRandom());
                    emptyBlocks.remove(l);
                } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                    break;
                }
            }
        }
    }
}
