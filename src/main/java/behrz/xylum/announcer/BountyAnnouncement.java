package behrz.xylum.announcer;

import behrz.xylum.Earth;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BountyAnnouncement extends BukkitRunnable {

    Earth plugin;

    public BountyAnnouncement(Earth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.DARK_RED);

            TextComponent text = new TextComponent(" Hunt down and kill other players for rewards. ");
            text.setColor(ChatColor.RED);

            TextComponent command = new TextComponent("/bounties");
            command.setColor(ChatColor.RED);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/bounties"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
