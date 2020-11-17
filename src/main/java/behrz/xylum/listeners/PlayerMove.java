package behrz.xylum.listeners;

import behrz.xylum.Earth;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;

public class PlayerMove implements Listener {

    @EventHandler
    public void limitHeight(PlayerMoveEvent event) {
        if (event.getTo().getBlockY() > 1000) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Limit] " + ChatColor.RED + "That's a little too high.");
        }
    }
    @EventHandler
    public void voidDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLocation().getBlockY() < 0) {
            Earth.getPlugin().getLogger().warning(event.getEntity().getName() + "'s inventory (fell in the void):");
            Earth.getPlugin().getLogger().warning(Arrays.toString(event.getEntity().getInventory().getContents()));
        }
    }
}
