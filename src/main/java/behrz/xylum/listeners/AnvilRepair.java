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
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilRepair implements Listener {

    private final Earth plugin;

    public AnvilRepair(Earth plugin){
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
                ItemStack translatedItem = AnvilColor.getTranslatedItem(player, inv, task);
                event.setCurrentItem(translatedItem);

                if (colorCheck(event.getCurrentItem(), player)) {
                    Economy economy = Earth.getEconomy();
                    if (economy.has(player, player.getWorld().getName(), 100)) {
                        player.sendMessage(ChatColor.DARK_GREEN + "[Color] " + ChatColor.GREEN + "You renamed this item for 100 gold.");
                        economy.withdrawPlayer(player, player.getWorld().getName(), 100);
                    } else {
                        event.setCancelled(true);
                        player.setExp(player.getExp());
                        player.sendMessage(ChatColor.DARK_RED + "[Color] " + ChatColor.RED + "Renaming an item with colors costs 100 gold!");
                    }
                }
            }
        }
    }

    private boolean colorCheck(ItemStack item, Player player) {
        ItemMeta itemMeta = item.getItemMeta();
        String coloredName = ChatColor.translateAlternateColorCodes('&', itemMeta.getDisplayName());
        for (int i = 0; i < coloredName.length(); i++) {
            if (coloredName.charAt(i) == '§') {
                char c = coloredName.charAt(i + 1);
                if (!AnvilColor.hasColorPermission(player, c)) coloredName = coloredName.replaceAll("§" + c, "&" + c);
            }
        }
        return coloredName.contains("§");
    }
}
