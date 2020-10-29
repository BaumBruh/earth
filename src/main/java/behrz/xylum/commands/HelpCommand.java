package behrz.xylum.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            player.sendMessage(" ");
            player.sendMessage(ChatColor.DARK_GREEN + "XylumEarth help" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.GRAY + " XylumEarth is a geopolitical Towny server. Alongside a dynmap, movecraft, breweries,");
            player.sendMessage(ChatColor.GRAY + " and McMMO, create your own town or join someone else and fight to become a world superpower.");
            player.sendMessage(ChatColor.GRAY + " Note: You can only sell gold using /sell.");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GRAY + " To view current players with bounties, do " + ChatColor.RED + "/bounties");
            player.sendMessage(ChatColor.GRAY + " More town and nation help can be found at " + ChatColor.AQUA + "/t help" + ChatColor.GRAY + " and " + ChatColor.YELLOW + "/n help " + ChatColor.GRAY + "respectively.");
            player.sendMessage(ChatColor.GRAY + " For basic information on McMMO commands, do " + ChatColor.LIGHT_PURPLE + "/mcmmo help");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GRAY + " More information, such as guides for movecraft, cannons, chestshops, custom recipes,");
            player.sendMessage(ChatColor.GRAY + " and voting links can be found on our " + ChatColor.AQUA + "/discord" + ChatColor.GRAY + ".");
            player.sendMessage(ChatColor.GRAY + " The dynmap can be accessed through " + ChatColor.GREEN + "/map" + ChatColor.GRAY + ".");
            player.sendMessage(ChatColor.GRAY + " Movecraft, server and community wikies can be found at " + ChatColor.YELLOW + "/wiki" + ChatColor.GRAY + ".");
            player.sendMessage(" ");

        } else {
            System.out.println("Only players can use /help.");
        }


        return false;
    }
}
