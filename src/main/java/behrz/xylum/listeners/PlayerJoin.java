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

        // MOTD
        try { wait(5L); } catch (InterruptedException ignored) {}
        motdMessage(player);
        player.setViewDistance(8);
        perksMessage(player);

    }

    private void motdMessage(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "[MOTD] " + ChatColor.AQUA + "/help lists important commands from many of our plugins.");
        player.sendMessage(ChatColor.GREEN + "       You can receive gold and other rewards through /vote.");
        player.sendMessage(ChatColor.GREEN + "       For more help, contact a staff member in-game or in /discord.");
        player.sendMessage(ChatColor.GREEN + "       Switch servers through /plots and /earth.");
    }

    private void perksMessage(Player player) {
        player.hasPermission("motd.perks");
        player.sendMessage(ChatColor.DARK_GREEN + "[Perks] " + ChatColor.GREEN + "Thanks for donating! Here's some of your perks:");
        int render = 8;
        if (player.hasPermission("motd.vip")) {
            player.sendMessage(ChatColor.GREEN + "        VIP: ");
            render = 10;
        }
        if (player.hasPermission("motd.vip+")) {
            player.sendMessage(ChatColor.GREEN + "        VIP+: ");
            render = 12;
        }
        if (player.hasPermission("motd.mvp")) {
            player.sendMessage(ChatColor.GREEN + "        MVP: ");
            render = 14;
        }
        if (player.hasPermission("motd.mvp+")) {
            player.sendMessage(ChatColor.GREEN + "        MVP+: ");
            render = 16;
        }
        player.setViewDistance(render);
    }

}
