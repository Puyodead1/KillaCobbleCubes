package me.puyodead1.killacobblecube;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedRandomBag<Material> {

    /**
     * This class was found here: https://gamedev.stackexchange.com/a/162987
     */

    private class Entry {
        double accumulatedWeight;
        Material material;
    }

    private List<Entry> entries = new ArrayList<>();
    private double accumulatedWeight;
    private Random rand = new Random();

    public void addEntry(Material material, double weight) {
        accumulatedWeight += weight;
        Entry e = new Entry();
        e.material = material;
        e.accumulatedWeight = accumulatedWeight;
        entries.add(e);
    }

    public Material getRandom() {
        double r = rand.nextDouble() * accumulatedWeight;

        for (Entry entry: entries) {
            if (entry.accumulatedWeight >= r) {
                return entry.material;
            }
        }
        return null;
    }
}