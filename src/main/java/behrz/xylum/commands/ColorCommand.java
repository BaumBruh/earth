package behrz.xylum.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GREEN + "C" + ChatColor.AQUA + "o" + ChatColor.RED + "l" + ChatColor.LIGHT_PURPLE + "o" + ChatColor.YELLOW + "r" + ChatColor.WHITE + "s" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.GRAY + " &1 - " + ChatColor.DARK_BLUE + "Dark Blue");
            player.sendMessage(ChatColor.GRAY + " &2 - " + ChatColor.DARK_GREEN + "Dark Green");
            player.sendMessage(ChatColor.GRAY + " &3 - " + ChatColor.DARK_AQUA + "Dark Aqua");
            player.sendMessage(ChatColor.GRAY + " &4 - " + ChatColor.DARK_RED + "Dark Red");
            player.sendMessage(ChatColor.GRAY + " &5 - " + ChatColor.DARK_PURPLE + "Dark Purple");
            player.sendMessage(ChatColor.GRAY + " &6 - " + ChatColor.GOLD + "Gold");
            player.sendMessage(ChatColor.GRAY + " &7 - " + ChatColor.GRAY + "Gray");
            player.sendMessage(ChatColor.GRAY + " &8 - " + ChatColor.DARK_GRAY + "Dark Gray");
            player.sendMessage(ChatColor.GRAY + " &9 - " + ChatColor.BLUE + "Blue");
            player.sendMessage(ChatColor.GRAY + " &0 - " + ChatColor.BLACK + "Black");
            player.sendMessage(ChatColor.GRAY + " &a - " + ChatColor.GREEN + "Green");
            player.sendMessage(ChatColor.GRAY + " &b - " + ChatColor.AQUA + "Aqua");
            player.sendMessage(ChatColor.GRAY + " &c - " + ChatColor.RED + "Red");
            player.sendMessage(ChatColor.GRAY + " &d - " + ChatColor.LIGHT_PURPLE + "Light Purple");
            player.sendMessage(ChatColor.GRAY + " &e - " + ChatColor.YELLOW + "Yellow");
            player.sendMessage(ChatColor.GRAY + " &f - " + ChatColor.WHITE + "White");
            player.sendMessage(" ");
        } else {
            System.out.println("Only players can use /color.");
        }
        return false;
    }
}