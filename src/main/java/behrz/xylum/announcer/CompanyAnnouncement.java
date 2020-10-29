package behrz.xylum.announcer;

import behrz.xylum.Xylum;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CompanyAnnouncement extends BukkitRunnable {

    Xylum plugin;

    public CompanyAnnouncement(Xylum plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            TextComponent info = new TextComponent(" ℹ");
            info.setColor(ChatColor.GOLD);

            TextComponent text = new TextComponent(" Have goods to sell? Create your own company! ");
            text.setColor(ChatColor.YELLOW);

            TextComponent command = new TextComponent("/company");
            command.setColor(ChatColor.YELLOW);
            command.setUnderlined( true );
            command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/company"));

            TextComponent blank = new TextComponent(" ");

            info.addExtra(text);
            info.addExtra(command);

            player.spigot().sendMessage(blank);
            player.spigot().sendMessage(info);
            player.spigot().sendMessage(blank);
        }
    }
}
