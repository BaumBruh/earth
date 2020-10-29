package behrz.xylum.listeners;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.inventory.ItemStack;

public class Riptide implements Listener {

    @EventHandler
    void onPlayerRiptide(PlayerRiptideEvent ev){
        Player player = ev.getPlayer();
        ev.getItem().removeEnchantment(Enchantment.RIPTIDE);
        player.sendMessage(ChatColor.RED + " Riptide enchantment removed.");
    }

    @EventHandler
    void onRiptideEnchant(EnchantItemEvent ev){
        ItemStack item = ev.getItem();
        if (item.containsEnchantment(Enchantment.RIPTIDE)) {
            item.removeEnchantment(Enchantment.RIPTIDE);

            // If riptide was the only enchant added, add loyalty.
            if (item.getEnchantments().isEmpty()) {
                item.addEnchantment(Enchantment.LOYALTY, '2');
            }
        }
    }


}
