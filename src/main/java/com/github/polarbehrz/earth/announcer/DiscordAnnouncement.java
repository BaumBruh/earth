package com.github.polarbehrz.earth.announcer;

import com.github.polarbehrz.earth.Earth;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DiscordAnnouncement extends BukkitRunnable {

    Earth plugin;

    public DiscordAnnouncement(Earth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.DARK_AQUA);

            TextComponent text = new TextComponent(" Join our discord for up-to-date information and constant help. ");
            text.setColor(ChatColor.AQUA);

            TextComponent command = new TextComponent("/discord");
            command.setColor(ChatColor.AQUA);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/discord"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
