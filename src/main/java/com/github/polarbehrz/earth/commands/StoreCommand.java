package com.github.polarbehrz.earth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(" ");
            player.sendMessage(ChatColor.YELLOW + "The server store is available at http://store.xylumearth.net/");
            player.sendMessage(" ");
        } else
            System.out.println("Only players can use /store.");
        return false;
    }
}
