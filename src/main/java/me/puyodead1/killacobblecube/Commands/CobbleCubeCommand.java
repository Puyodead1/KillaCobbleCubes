package me.puyodead1.killacobblecube.Commands;

import me.puyodead1.killacobblecube.CobbleCubeItem;
import me.puyodead1.killacobblecube.KillaCobblecube;
import me.puyodead1.killacobblecube.KillaCobblecubeUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CobbleCubeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final int l = args.length;
        if (l == 0 && sender.hasPermission("cobblecubes.info")) {
            // send plugin information
            KillaCobblecubeUtils.sendSender(sender, "&6&lCOBBLE&6&lCUBES &eV" + KillaCobblecube.plugin.getDescription().getVersion() + " by Puyodead1");
            KillaCobblecubeUtils.sendSender(sender, "&e-=-=-=-=-=-=-=-=-=-=-=-=");
            KillaCobblecubeUtils.sendSender(sender, "&e/cobblecubes give <player> <amount> <size>");
            return true;
        }
        else if(l==1 && args[0].toLowerCase().equals("give") && sender.hasPermission("cobblecubes.give")) {
            KillaCobblecubeUtils.sendSender(sender, "&e/cobblecubes give <player> <amount> <size>");
            return true;
        }else if(l==4 && args[0].toLowerCase().equals("give")&& sender.hasPermission("cobblecubes.give")) {
            final Player p = Bukkit.getServer().getPlayer(args[1]);

            if (p != null) {
                // player is valid
                try {
                    final int amount = Integer.parseInt(args[2]);
                    final int size = Integer.parseInt(args[3]);

                    if (CobbleCubeItem.getCobblecubes().containsKey(size + "x" + size)) {
                        ItemStack is = CobbleCubeItem.getCobblecubes().get(size + "x" + size).getItem().clone();
                        is.setAmount(amount);
                        p.getInventory().addItem(is);
                        return true;
                    } else {
                        KillaCobblecubeUtils.sendSender(sender, KillaCobblecube.plugin.getConfig().getString("messages.invalid cube"));
                        return false;
                    }
                } catch(NumberFormatException e) {
                    KillaCobblecubeUtils.sendSender(sender, KillaCobblecube.plugin.getConfig().getString("messages.invalid number"));
                    return false;
                }
            } else {
                // player is invalid
                KillaCobblecubeUtils.sendSender(sender, KillaCobblecube.plugin.getConfig().getString("messages.invalid player"));
                return false;
            }
        }
        return false;
    }
}
