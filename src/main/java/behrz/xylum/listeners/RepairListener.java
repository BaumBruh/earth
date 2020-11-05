package behrz.xylum.listeners;

import behrz.xylum.Earth;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class RepairListener implements Listener {

    private final Earth plugin;

    public RepairListener(Earth plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onAnvilGUIClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL &&
                event.getWhoClicked() instanceof Player) {
            Player player = (Player)event.getWhoClicked();
            AnvilInventory inv = (AnvilInventory)event.getInventory();
            AnvilTask task = AnvilTask.getTask(inv);
            if (task == null)
                task = new AnvilTask(inv, player, plugin);
            if (event.getRawSlot() == 2) {
                ItemStack translatedItem = ColorHandler.getTranslatedItem(player, inv, task);
                if (!(translatedItem == event.getCurrentItem())) {

                    if (event.isCancelled()) {
                        event.setCurrentItem(translatedItem);
                        return;
                    }

                    Economy economy = Earth.getEconomy();
                    if (economy.has(player, player.getWorld().getName(), 250)) {
                        player.sendMessage(ChatColor.DARK_GREEN + "[Color] " + ChatColor.GREEN + "You renamed this item for 250 gold.");
                        economy.withdrawPlayer(player, player.getWorld().getName(), 250);
                        event.setCurrentItem(translatedItem);
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "[Color] " + ChatColor.RED + "Renaming an item with colors costs 250 gold!");
                    }
                }
            }
        }
    }
}
