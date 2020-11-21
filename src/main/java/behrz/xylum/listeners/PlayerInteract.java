package behrz.xylum.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void getOwner(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (!player.getActiveItem().getType().equals(Material.AIR)) {
            return;
        }

        if (entity instanceof Tameable) {
            Tameable tamed = (Tameable) entity;
            if (!tamed.isTamed()) {
                return;
            }
            if (tamed.getOwner() == player) {
                return;
            }
            player.sendMessage(ChatColor.GRAY + " " + ChatColor.ITALIC + "" + ChatColor.UNDERLINE + tamed.getOwner().getName() + ChatColor.GRAY + " " + ChatColor.ITALIC + "owns this " + entity.getType().getKey().toString().replace("minecraft:", "") + "...");
        }
    }
}