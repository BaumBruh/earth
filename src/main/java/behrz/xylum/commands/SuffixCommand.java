package behrz.xylum.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuffixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && !sender.hasPermission("xylum.suffix.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Suffix] &dOnly MVP+ can use this command."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("dark_green") ||
                    args[0].equalsIgnoreCase("dark_aqua") ||
                    args[0].equalsIgnoreCase("dark_red") ||
                    args[0].equalsIgnoreCase("dark_purple") ||
                    args[0].equalsIgnoreCase("gold") ||
                    args[0].equalsIgnoreCase("gray") ||
                    args[0].equalsIgnoreCase("dark_gray") ||
                    args[0].equalsIgnoreCase("blue") ||
                    args[0].equalsIgnoreCase("green") ||
                    args[0].equalsIgnoreCase("aqua") ||
                    args[0].equalsIgnoreCase("red") ||
                    args[0].equalsIgnoreCase("light_purple") ||
                    args[0].equalsIgnoreCase("yellow") ||
                    args[0].equalsIgnoreCase("white")) {
                if (args.length == 2) {
                    if (args[1].length() <= 8) {
                        String tag = args[0].toLowerCase()
                                .replace("dark_green", "&2")
                                .replace("dark_aqua", "&3")
                                .replace("dark_red", "&4")
                                .replace("dark_purple", "&5")
                                .replace("gold", "&6")
                                .replace("gray", "&7")
                                .replace("dark_gray", "&8")
                                .replace("blue", "&9")
                                .replace("green", "&a")
                                .replace("aqua", "&b")
                                .replace("red", "&c")
                                .replace("light_purple", "&d")
                                .replace("yellow", "&e")
                                .replace("white", "&f")
                                + "[" + args[1] + "]" + ChatColor.RESET;
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " meta setsuffix 500 \"&r " + tag + "\"");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Suffix] &dSuccesfully set suffix to " + tag + "&d."));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Suffix] &dThat suffix is too long, it can only be 8 characters."));
                    }

                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Suffix] &dYou have a color, what's the text?"));
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " meta removesuffix 500");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Suffix] &dRemoved custom suffix."));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Suffix] &dInvalid color, try using tab complete. If you're not sure what each color is, do /colors. Use /suffix remove to clear current suffix."));
            }

        } else {
            player.sendMessage(" ");
            player.sendMessage(ChatColor.DARK_PURPLE + "How custom suffixes work:");
            player.sendMessage(ChatColor.GRAY + " Choose a color (or tabcomplete the available ones).");
            player.sendMessage(ChatColor.GRAY + " Write anything. Must be less than 10 characters.");
            player.sendMessage(ChatColor.GRAY + "");
            player.sendMessage(ChatColor.AQUA + " And then you have a custom suffix.");
            player.sendMessage(" ");
        }
        return false;
    }
}
