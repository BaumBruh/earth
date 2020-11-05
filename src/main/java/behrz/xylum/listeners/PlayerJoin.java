package behrz.xylum.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent ev){
        Player player = ev.getPlayer();
        ev.setJoinMessage(ChatColor.translateAlternateColorCodes('&', " &a&o" + player.getDisplayName() + "&a&o joined the server."));

        motdMessage(player);
        perksMessage(player);

    }

    private void motdMessage(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "[MOTD] " + ChatColor.AQUA + "/help lists important commands from many of our plugins.");
        player.sendMessage(ChatColor.AQUA + "         You can receive gold and other rewards through /vote.");
        player.sendMessage(ChatColor.AQUA + "         For more help, contact a staff member in-game or in /discord.");
        player.sendMessage(ChatColor.AQUA + "         Switch servers through /plots and /earth.");
    }

    private void perksMessage(Player player) {
        player.hasPermission("motd.perks");
        player.sendMessage(ChatColor.DARK_GREEN + "[Perks] " + ChatColor.GREEN + "Thanks for donating! Here's some of your perks:");
        if (player.hasPermission("motd.vip")) {
            player.sendMessage(ChatColor.GREEN + "           VIP: " + ChatColor.GRAY + "VIP kit, /feed, /nightvision, /enderchest, /workbench. Full list at [/vip]");
        }
        if (player.hasPermission("motd.vip+")) {
            player.sendMessage(ChatColor.GREEN + "           VIP+: " + ChatColor.GRAY + "All VIP perks, VIP+ kit, /hat, and ability to right-click shulkers to open. Full list at [/vip+]");
        }
        if (player.hasPermission("motd.mvp")) {
            player.sendMessage(ChatColor.GREEN + "           MVP: " + ChatColor.GRAY + "All VIP/VIP+ perks, MVP kit, /tfly, /nick. Full list at [/mvp]");
        }
        if (player.hasPermission("motd.mvp+")) {
            player.sendMessage(ChatColor.GREEN + "           MVP+: " + ChatColor.GRAY + "All VIP/VIP+/MVP perks, MVP+ kit, /kittycannon, /chestsort, /inventorysort, colored anvil names. Full list at [/mvp+]");
        }
    }

}
