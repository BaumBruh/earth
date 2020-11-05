package behrz.xylum.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColorHandler {

    public static boolean hasColorPermission(Player p, char c) {
        return !(!p.hasPermission("coloredanvils.*") && !p.hasPermission("coloredanvils.color.*") &&
                !p.hasPermission("coloredanvils.color." + c) && !p.hasPermission("coloredanvils.color.&" + c));
    }

    public static ItemStack getTranslatedItem(Player p, AnvilInventory inv, AnvilTask task) {
        ItemStack outputItem = inv.getItem(2);
        if (outputItem != null && outputItem.hasItemMeta()) {
            ItemMeta outputItemMeta = outputItem.getItemMeta();
            if (outputItemMeta.hasDisplayName()) {
                ItemStack inputItem = inv.getItem(0);
                if (inputItem != null && inputItem.hasItemMeta()) {
                    ItemMeta inputItemMeta = inputItem.getItemMeta();
                    if (inputItemMeta.hasDisplayName() && outputItemMeta.getDisplayName().replaceAll("&", "").replaceAll("§", "").equals(inputItemMeta.getDisplayName().replaceAll("§", ""))) {
                        outputItemMeta.setDisplayName(inputItemMeta.getDisplayName());
                        outputItem.setItemMeta(outputItemMeta);
                        return outputItem;
                    }
                }
                return colorItemWithPermissions(outputItem, p);
            }
        }
        return outputItem;
    }

    public static ItemStack colorItemWithPermissions(ItemStack item, Player p) {
        ItemMeta itemMeta = item.getItemMeta();
        String coloredName = ChatColor.translateAlternateColorCodes('&', itemMeta.getDisplayName());
        for (int i = 0; i < coloredName.length(); i++) {
            if (coloredName.charAt(i) == '§') {
            char c = coloredName.charAt(i + 1);
            if (!hasColorPermission(p, c))
                coloredName = coloredName.replaceAll("§" + c, "&" + c);
            }
        }
    itemMeta.setDisplayName(coloredName);
    item.setItemMeta(itemMeta);
    return item;
  }
}