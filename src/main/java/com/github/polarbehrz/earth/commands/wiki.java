package com.github.polarbehrz.earth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class wiki implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(" ");
            player.sendMessage(ChatColor.DARK_GREEN + "XylumEarth wikis" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.GRAY + " You can access the wiki at " + ChatColor.GREEN + "https://github.com/BaumBruh/xylumearthwiki/wiki");
            player.sendMessage(" ");

        } else
            System.out.println("Only players can use /wiki.");
        return false;
    }
}
