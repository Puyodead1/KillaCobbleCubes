package me.puyodead1.killacobblecube;

import com.deanveloper.skullcreator.SkullCreator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CobbleCubeItem {

    public static HashMap<String, CobbleCubeItem> cobblecubes = new HashMap<>();

    private int size;
    private ItemStack item;

    public CobbleCubeItem(int size) {
        this.size = size;

        ItemStack skullItem = SkullCreator.fromBase64(SkullCreator.Type.ITEM, KillaCobblecube.plugin.getConfig().getString("settings.cobblecube.skull"));
        ItemMeta skullMeta = skullItem.getItemMeta();
        skullMeta.setDisplayName(KillaCobblecubeUtils.Color(KillaCobblecube.plugin.getConfig().getString("settings.cobblecube.name")));
        skullMeta.setLore(KillaCobblecubeUtils.ColorList(KillaCobblecube.plugin.getConfig().getStringList("settings.cobblecube.lore"), size + "x" + size));
        skullItem.setItemMeta(skullMeta);
        this.item = skullItem;

        cobblecubes.put(size + "x" + size, this);
    }

    public static HashMap<String, CobbleCubeItem> getCobblecubes() {
        return cobblecubes;
    }

    public int getSize() {
        return size;
    }

    public ItemStack getItem() {
        return item;
    }
}
