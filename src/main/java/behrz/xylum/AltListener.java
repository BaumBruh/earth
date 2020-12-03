package behrz.xylum;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class AltListener implements Listener {
    private Earth plugin;

    public AltListener(Earth plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("altdetector.exempt"))
            return;
        String ip = player.getAddress().getAddress().getHostAddress();
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        this.plugin.altData.addUpdateIp(ip, uuid, name);
        if (this.plugin.saveInterval == 0L)
            this.plugin.altData.saveIpDataConfig();
        String altString = this.plugin.altData.getFormattedAltString(name, ip, uuid, "{0} may be an alt of ", "{0}", ", ");
        if (altString != null) {
            this.plugin.getLogger().info(altString.replaceAll("&[0123456789AaBbCcDdEeFfKkLlMmNnOoRr]", ""));
            String notifyString = ChatColor.translateAlternateColorCodes('&', String.valueOf("&4[Alts] &c") + altString);
            for (Player p : this.plugin.getServer().getOnlinePlayers()) {
                if (p.hasPermission("altdetector.notify"))
                    p.sendMessage(notifyString);
            }
        }
    }
}
