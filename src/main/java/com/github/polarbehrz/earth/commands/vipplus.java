package com.github.polarbehrz.earth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class vipplus implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(" ");
            player.sendMessage(ChatColor.DARK_GREEN + "VIP+ rank perks" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.DARK_GREEN + " VIP+" + ChatColor.GRAY + " tag.");
            player.sendMessage(ChatColor.GRAY + " Access to VIP and VIP+ resource kits.");
            player.sendMessage(ChatColor.LIGHT_PURPLE + " Ablity to use [balance] and [bounty] in chat.");
            player.sendMessage(ChatColor.LIGHT_PURPLE + " Ablity to use [item] in that chat.");
            player.sendMessage(ChatColor.GRAY + " Access to /feed.");
            player.sendMessage(ChatColor.GRAY + " Access to /hat");
            player.sendMessage(ChatColor.GRAY + " Access to /nightvision.");
            player.sendMessage(ChatColor.GRAY + " Access to /enderchest.");
            player.sendMessage(ChatColor.GRAY + " Access to /workbench.");
            player.sendMessage(ChatColor.GRAY + " Right-click shulkers to open.");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.RED + "Purchase this rank, or see its perks in other servers at http://store.xylumearth.net/.");
            player.sendMessage(" ");

        } else
            System.out.println("Only players can use /vip+.");
        return false;
    }
}
