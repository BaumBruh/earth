package behrz.xylum.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MVPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(" ");
            player.sendMessage(ChatColor.YELLOW + "MVP rank perks" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.YELLOW + " MVP" + ChatColor.GRAY + " tag.");
            player.sendMessage(ChatColor.GRAY + " Access to VIP, VIP+ and MVP resource kits.");
            player.sendMessage(ChatColor.LIGHT_PURPLE + " Ablity to use [balance] and [bounty] in chat.");
            player.sendMessage(ChatColor.LIGHT_PURPLE + " Ablity to use [item], [inv], and [end] in that chat.");
            player.sendMessage(ChatColor.GRAY + " Access to /feed.");
            player.sendMessage(ChatColor.GRAY + " Access to /hat");
            player.sendMessage(ChatColor.GRAY + " Access to /nightvision.");
            player.sendMessage(ChatColor.GRAY + " Access to /enderchest.");
            player.sendMessage(ChatColor.GRAY + " Access to /workbench.");
            player.sendMessage(ChatColor.GRAY + " Colors in /nick.");
            player.sendMessage(ChatColor.GRAY + " Access to /particles.");
            player.sendMessage(ChatColor.GRAY + " Access to /tfly in town claims.");
            player.sendMessage(ChatColor.GRAY + " Right-click shulkers to open.");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.RED + "Purchase this rank, or see its perks in other servers at http://store.xylumearth.net/.");
            player.sendMessage(" ");

        } else
            System.out.println("Only players can use /mvp.");
        return false;
    }
}
