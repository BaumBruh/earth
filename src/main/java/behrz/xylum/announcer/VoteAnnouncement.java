package behrz.xylum.announcer;

import behrz.xylum.Earth;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VoteAnnouncement extends BukkitRunnable {

    Earth plugin;

    public VoteAnnouncement(Earth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.DARK_PURPLE);

            TextComponent text = new TextComponent(" Voting helps the server, while also giving rewards! ");
            text.setColor(ChatColor.LIGHT_PURPLE);

            TextComponent command = new TextComponent("/vote");
            command.setColor(ChatColor.LIGHT_PURPLE);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/vote"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
