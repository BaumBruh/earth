package behrz.xylum.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (!player.hasPermission("mcatlas.tamedowner")) {
            return;
        }

        ItemStack mainItem = player.getActiveItem();

        if (mainItem == null) {
            return;
        }

        if (mainItem.getType() == Material.NAME_TAG) {
            String name = mainItem.getItemMeta().getDisplayName();
            Location loc = player.getLocation();

            Bukkit.getLogger().info(player.getName() + " named a creature \"" + name + "\" at " +
                    loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
        }

        if (!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }

        if (entity instanceof Tameable) {
            Tameable tamed = (Tameable) entity;

            if (!tamed.isTamed()) {
                return;
            }

            if (tamed.getOwner().equals(player)) {
                return;
            }

            player.sendMessage(ChatColor.GREEN + "The owner of this creature is "
                    + ChatColor.GOLD + tamed.getOwner().getName());
        }
    }
}
