package me.puyodead1.killacobblecube.Events;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;
import com.wasteofplastic.askyblock.events.IslandDeleteEvent;
import me.puyodead1.killacobblecube.CobbleCube;
import me.puyodead1.killacobblecube.KillaCobblecube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import me.puyodead1.killacobblecube.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class IslandDelete implements Listener {

    @EventHandler
    public void IslandDeleteEvent(IslandDeleteEvent e) {
        final Player player = Bukkit.getPlayer(e.getPlayerUUID());
        final PlayerStorage playerStorage = PlayerStorage.valueOf(player);

        if(playerStorage != null) {
            final ArrayList<CobbleCube> cubes = playerStorage.getCubes();

            Bukkit.broadcastMessage("cubes: " + cubes.size());

            if(cubes.size() > 0) {
                Bukkit.broadcastMessage("more then 0");
//                for(CobbleCube cube : cubes) {
//                    cube.getTask().cancel();
//                    // cube.cleanUp();
//                }

                KillaCobblecubeUtils.sendConsole(KillaCobblecube.PREFIX + "&aCleaned up &e" + cubes.size() + "&a cubes from " + player.getName());
            }
        } else {
            player.sendMessage("playerStorage is null");
        }
    }
}
