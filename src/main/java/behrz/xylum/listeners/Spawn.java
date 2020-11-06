package behrz.xylum.listeners;

import behrz.xylum.Earth;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.SpawnEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class Spawn implements Listener {

    @EventHandler
    public void onSpawn(SpawnEvent event) {

        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();

        if (player.hasPermission("teleport.ignorecost")) {
            return;
        }

        Location from = event.getFrom();
        Location to = event.getTo();

        Economy economy = Earth.getEconomy();

        if (from.getWorld() != to.getWorld()) {
            if (hasBalance(player, from.getWorld().getName(), 250)) {
                player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "Teleporting has cost you 250 gold.");
                economy.withdrawPlayer(player, player.getWorld().getName(), 250);
                return;
            } else {
                player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "You can not teleport because teleporting between worlds costs 250 gold!");
                event.setCancelled(true);
                return;
            }
        }

        int cost = (int)from.distance(to)/48;
        cost = residentDiscount(player, cost);

        if (isOutpost(to)) {
            cost = cost/4;
            if (hasBalance(player, from.getWorld().getName(), cost)) {
                player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "Teleporting has cost you " + cost + " gold.");
                economy.withdrawPlayer(player, player.getWorld().getName(), cost);
            } else {
                player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "You can not teleport because that would cost " + cost + " gold!");
                event.setCancelled(true);
            }
            return;
        }

        if (hasBalance(player, from.getWorld().getName(), cost)) {
            player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "Teleporting has cost you " + cost + " gold.");
            economy.withdrawPlayer(player, player.getWorld().getName(), cost);
        } else {
            player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "You can not teleport because that would cost " + cost + " gold!");
            event.setCancelled(true);
        }

    }

    private boolean hasBalance(Player player, String world, int cost) {
        Economy economy = Earth.getEconomy();
        return economy.has(player, world, cost);
    }

    private boolean isOutpost(Location location) {
        TownBlock tb = TownyAPI.getInstance().getTownBlock(location);
        try {
            Town town = tb.getTown();
            List outposts = town.getAllOutpostSpawns();
            if (outposts.contains(location)) {
                return true;
            } else {
                return false;
            }
        } catch (NotRegisteredException e) {
            return false;
        }
    }

    private int residentDiscount(Player player, int cost) {
        Town town;
        try {
            town = TownyAPI.getInstance().getDataSource().getResident(player.getName()).getTown();
        } catch (NotRegisteredException e) {
            return cost;
        }

        int residents = town.getNumResidents();
        return cost/residents;

    }
}
