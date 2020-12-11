package com.github.polarbehrz.earth.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            TextComponent message = new TextComponent( "The server dynmap is available at " );
            message.setColor( ChatColor.GREEN );

            TextComponent link = new TextComponent( "http://xylumearth.net:8123/" );
            link.setColor( ChatColor.GREEN );
            link.setClickEvent( new ClickEvent(ClickEvent.Action.OPEN_URL, "http://xylumearth.net:8123/") );

            message.addExtra(link);
            player.spigot().sendMessage( message );

        } else
            System.out.println("Only players can use /map.");
        return false;
    }
}
