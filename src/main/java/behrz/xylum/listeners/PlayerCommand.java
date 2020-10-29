package behrz.xylum.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommand implements Listener {

    @EventHandler
    public void illegalCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        Player player = event.getPlayer();

        if (command.startsWith("?")) {
            event.setCancelled(true);
            player.sendMessage("No.");
        }

        if (command.startsWith("bukkit:help")) {
            event.setCancelled(true);
            player.sendMessage("No.");
        }

        if (command.startsWith("bukkit:?")) {
            event.setCancelled(true);
            player.sendMessage("No.");
        }

        if (command.startsWith("bukkit:about")) {
            event.setCancelled(true);
            player.sendMessage("No.");
        }
    }
}
