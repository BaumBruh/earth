package behrz.xylum.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WikiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage(" ");
            player.sendMessage(ChatColor.DARK_GREEN + "XylumEarth wikis" + ChatColor.DARK_GRAY + ":");
            player.sendMessage(ChatColor.GRAY + " You can access the wiki at " + ChatColor.GREEN + "https://github.com/HightechCaveman/Xylum/wiki/");
            player.sendMessage(ChatColor.GRAY + " You can access the community wiki at " + ChatColor.YELLOW + "https://xylum-earth-mc.fandom.com/wiki/");
            player.sendMessage(ChatColor.GRAY + " You can access more infomation about MoveCraft at " + ChatColor.BLUE + "https://github.com/HightechCaveman/Xylum/wiki/Movecraft");
            player.sendMessage(" ");

        } else
            System.out.println("Only players can use /wiki.");
        return false;
    }
}
