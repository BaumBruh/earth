package behrz.xylum.announcer;

import behrz.xylum.Xylum;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WikiAnnouncement extends BukkitRunnable {

    Xylum plugin;

    public WikiAnnouncement(Xylum plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.DARK_GREEN);

            TextComponent text = new TextComponent(" Our wiki provides indepth help about many of our plugins. ");
            text.setColor(ChatColor.GREEN);

            TextComponent command = new TextComponent("/wiki");
            command.setColor(ChatColor.GREEN);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/wiki"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
