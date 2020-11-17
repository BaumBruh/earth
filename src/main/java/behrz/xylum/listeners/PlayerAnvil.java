package behrz.xylum.listeners;

import behrz.xylum.Earth;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerAnvil implements Listener {
    private Earth main;

    public PlayerAnvil(Earth main) {
        this.main = main;
    }
    private boolean isColored = false;

    // Credit to Feitan_Sama
    @EventHandler
    public void Color(PrepareAnvilEvent event) {
        if (event.getResult() != null && event.getResult().hasItemMeta() && event.getInventory().getRenameText() != "") {
            Player player = (Player)event.getView().getPlayer();
            String Name = event.getInventory().getItem(0).getType().toString();
            if (!this.main.getBlacklist().contains(Name) || !this.main.getConfig().getString("Blacklist." + Name).contains("true"))
                if (player.hasPermission("xylum.anvil.use")) {
                    ItemStack result = event.getResult();
                    ItemMeta meta = result.getItemMeta();
                    String rename = event.getInventory().getRenameText();
                    String final_rename = "";
                    if (event.getInventory().contains(Material.ENCHANTED_BOOK, 1)) {
                        String color = event.getInventory().getItem(0).getItemMeta().getDisplayName();
                        final_rename = color;
                        meta.setDisplayName(final_rename);
                        result.setItemMeta(meta);
                    }
                    if (rename.contains("&") || rename.contains("@")) {
                        char[] chars = rename.toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            if (chars[i] == '@' || chars[i] == '&') {
                                String out = "";
                                for (int j = i; j < chars.length; j++)
                                    out = String.valueOf(out) + chars[j];
                                if (this.main.Matches(out, "@", "&")) {
                                    int a = out.indexOf('@', 1);
                                    int b = out.indexOf('&', 1);
                                    int end = 0;
                                    if (a == -1 || b == -1) {
                                        if (a == -1 && b == -1) {
                                            end = -1;
                                        } else if (a == -1) {
                                            end = b;
                                        } else {
                                            end = a;
                                        }
                                    } else if (a < b) {
                                        end = a;
                                    } else {
                                        end = b;
                                    }
                                    out = out.substring(0, end);
                                }
                                if (out.startsWith("@")) {
                                    for (int k = 0; k < this.main.colors.size(); k++) {
                                        if (out.contains("@" + (String)this.main.colors.get(k))) {
                                            if (out.length() > 2)
                                                out = out.substring(3);
                                            out = ChatColor.of("#" + (String)this.main.hex.get(k)) + out;
                                        }
                                    }
                                } else {
                                    out = ChatColor.translateAlternateColorCodes('&', out);
                                }
                                final_rename = String.valueOf(final_rename) + out;
                            }
                        }
                        Economy economy = Earth.getEconomy();
                        if (economy.has(player, player.getWorld().getName(), 600)) {
                            isColored = true;
                            meta.setDisplayName(final_rename);
                        } else {
                            isColored = false;
                            meta.setDisplayName(rename);
                        }
                    } else {
                        isColored = false;
                        meta.setDisplayName(rename);
                    }
                    result.setItemMeta(meta);
                }
        }
    }

    @EventHandler
    public void onAnvilUse(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL && event.getWhoClicked() instanceof Player) {
            Player player = (Player)event.getWhoClicked();
            if (event.getRawSlot() == 2) {
                if (isColored) {
                    player.sendMessage(ChatColor.DARK_GREEN + "[Color] " + ChatColor.GREEN + "You renamed this item for 600 gold.");
                    Economy economy = Earth.getEconomy();
                    economy.withdrawPlayer(player, player.getWorld().getName(), 600);
                }
            }
        }
    }
}
