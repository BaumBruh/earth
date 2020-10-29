package behrz.xylum.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void limitHeight(PlayerMoveEvent event) {
        if (event.getTo().getBlockY() > 1000) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Limit] " + ChatColor.RED + "That's a little too high.");
        }
    }
}
