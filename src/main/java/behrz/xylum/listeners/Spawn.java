package behrz.xylum.listeners;

import behrz.xylum.Earth;
import com.palmergames.bukkit.towny.event.SpawnEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Spawn implements Listener {

    @EventHandler
    public void onSpawn(SpawnEvent event) {

        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();

        Location from = event.getFrom();
        Location to = event.getTo();

        Economy economy = Earth.getEconomy();

        if (from.getWorld() != to.getWorld()) {
            if (hasBalance(player, from.getWorld().getName(), 500)) {
                player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "Teleporting has cost you 500 gold.");
                economy.withdrawPlayer(player, player.getWorld().getName(), 500);
                return;
            } else {
                player.sendMessage(ChatColor.DARK_GREEN + "[Teleport] " + ChatColor.GREEN + "You can not teleport because teleporting between worlds costs 500 gold!");
                event.setCancelled(true);
                return;
            }
        }

        int cost = (int)from.distance(to)/12;
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
}
