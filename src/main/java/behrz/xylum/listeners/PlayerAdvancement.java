package behrz.xylum.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerAdvancement implements Listener {

    @EventHandler
    void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        if (check(event.getAdvancement().getKey().getKey())) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', " &b" + event.getPlayer().getDisplayName() + "&b made the advancement &3[" + event.getAdvancement().toString() + "&3]"));
        }
    }

    private boolean check(String adv) {
        if (adv.contains("root") || adv.contains("recipes"))
            return false;
        return true;
    }

}
