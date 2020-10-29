package behrz.xylum.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent ev){
        Player player = ev.getPlayer();
        ev.setQuitMessage(ChatColor.translateAlternateColorCodes('&', " &c&o" + player.getDisplayName() + "&c&o left the server."));
    }


}
