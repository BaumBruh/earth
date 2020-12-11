package com.github.polarbehrz.earth.announcer;

import com.github.polarbehrz.earth.Earth;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StoreAnnouncement extends BukkitRunnable {

    Earth plugin;

    public StoreAnnouncement(Earth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.GOLD);

            TextComponent text = new TextComponent(" If you would like to support our server, you can donate at ");
            text.setColor(ChatColor.YELLOW);

            TextComponent command = new TextComponent("/store");
            command.setColor(ChatColor.YELLOW);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/store"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
