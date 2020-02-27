package me.puyodead1.killacobblecube;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerStorage {

    private Player player;
    private ArrayList<CobbleCube> cubes;

    public static HashMap<Player, PlayerStorage> playerStorageHashMap = new HashMap<>();

    public PlayerStorage(Player player) {
        this.player = player;
        this.cubes = new ArrayList<>();


        playerStorageHashMap.put(player, this);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<CobbleCube> getCubes() {
        return cubes;
    }

    public static HashMap<Player, PlayerStorage> getPlayerStorageHashMap() {
        return playerStorageHashMap;
    }

    public static PlayerStorage valueOf(Player player) {
        return playerStorageHashMap.getOrDefault(player, null);
    }
}
