package behrz.xylum.announcer;

import behrz.xylum.Xylum;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MapAnnouncement extends BukkitRunnable {

    Xylum plugin;

    public MapAnnouncement(Xylum plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.DARK_GREEN);

            TextComponent text = new TextComponent(" The dynmap provides real-time town information and player locations. ");
            text.setColor(ChatColor.GREEN);

            TextComponent command = new TextComponent("/map");
            command.setColor(ChatColor.GREEN);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/map"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
