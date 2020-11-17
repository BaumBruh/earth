package behrz.xylum.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VIPCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GREEN + "VIP rank perks" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.GREEN + " VIP" + ChatColor.GRAY + " tag.");
            player.sendMessage(ChatColor.GRAY + " Access to VIP resource kit.");
            player.sendMessage(ChatColor.LIGHT_PURPLE + " Ablity to use [balance] and [bounty] in chat.");
            player.sendMessage(ChatColor.GRAY + " Access to /feed.");
            player.sendMessage(ChatColor.GRAY + " Access to /nightvision.");
            player.sendMessage(ChatColor.GRAY + " Access to /enderchest.");
            player.sendMessage(ChatColor.GRAY + " Access to /workbench.");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.RED + "Purchase this rank, or see its perks in other servers at http://store.xylumearth.net/.");
            player.sendMessage(" ");


        } else {
            System.out.println("Only players can use /vip.");
        }




        return false;
    }
}
