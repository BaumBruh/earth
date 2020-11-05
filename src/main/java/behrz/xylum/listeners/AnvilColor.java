package behrz.xylum.listeners;

import behrz.xylum.Earth;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilColor {
    public static final int INPUT_LEFT = 0;

    public static final int OUTPUT = 2;

    public static boolean hasColor(String s, ChatColor c) {
        return s.contains((CharSequence) c);
    }

    public static boolean hasColorPermission(Player p, ChatColor c) {
        return hasColorPermission(p, c.getChar());
    }

    public static boolean hasColorPermission(Player p, char c) {
        return !(Earth.usingPermissions() && !p.hasPermission("coloredanvils.*") && !p.hasPermission("coloredanvils.color.*") && !p.hasPermission("coloredanvils.color." + c) && !p.hasPermission("coloredanvils.color.&" + c));
    }

    public static ItemStack getTranslatedItem(Player p, AnvilInventory inv, AnvilTask task) {
        ItemStack outputItem = inv.getItem(2);
        if (outputItem != null && outputItem.hasItemMeta()) {
            ItemMeta outputItemMeta = outputItem.getItemMeta();
            if (outputItemMeta.hasDisplayName()) {
                return colorItemWithPermissions(outputItem, p);
            }
            return colorItemWithPermissions(outputItem, p);
        }
        return outputItem;
    }

    public static ItemStack colorItemWithPermissions(ItemStack item, Player p) {
        ItemMeta itemMeta = item.getItemMeta();
        String coloredName = ChatColor.translateAlternateColorCodes('&', itemMeta.getDisplayName());
        for (int i = 0; i < coloredName.length(); i++) {
            if (coloredName.charAt(i) == '§') {
                char c = coloredName.charAt(i + 1);
                if (!hasColorPermission(p, c)) coloredName = coloredName.replaceAll("§" + c, "&" + c);
            }
        }
        itemMeta.setDisplayName(coloredName);
        item.setItemMeta(itemMeta);
        return item;
    }
}