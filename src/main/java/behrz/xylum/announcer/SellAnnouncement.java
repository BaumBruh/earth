package behrz.xylum.announcer;

import behrz.xylum.Earth;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SellAnnouncement extends BukkitRunnable {

    Earth plugin;

    public SellAnnouncement(Earth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.GOLD);

            TextComponent text = new TextComponent(" There's currently a 20% Halloween sell on our store. ");
            text.setColor(ChatColor.YELLOW);

            TextComponent command = new TextComponent("/buy");
            command.setColor(ChatColor.YELLOW);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/buy"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}