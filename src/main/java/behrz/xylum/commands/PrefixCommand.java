package behrz.xylum.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PrefixCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && !sender.hasPermission("xylum.prefix.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Prefix] &dOnly VIP and above can use this command."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {

            if (args[0].equalsIgnoreCase("alpha")) {
                if (player.hasPermission("xylum.prefix.alpha")) {
                    setPrefix(player, "Alpha", "#a0c0d8Alpha &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }
            } else if (args[0].equalsIgnoreCase("beta")) {
                if (player.hasPermission("xylum.prefix.beta")) {
                    setPrefix(player, "Beta", "#8b8b96Beta &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("youtube")) {
                if (player.hasPermission("xylum.prefix.youtube")) {
                    setPrefix(player, "YouTube", "#ffffff&lY#fedbd2&lO#f9b7a6&lU#f0937c&lT#e46f53&lU#d4472c&lB#c20000&lE&r &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("vip")) {
                if (player.hasPermission("xylum.prefix.vip")) {
                    setPrefix(player, "VIP", "#5BCB4FVIP &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("vip+")) {
                if (player.hasPermission("xylum.prefix.vip+")) {
                    setPrefix(player, "VIP+", "#057108VIP+ &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("mvp")) {
                if (player.hasPermission("xylum.prefix.mvp")) {
                    setPrefix(player, "MVP", "#ffd800M#ffbc00V#ffa000P &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("mvp+")) {
                if (player.hasPermission("xylum.prefix.mvp+")) {
                    setPrefix(player, "MVP+", "#ffb404M#ff9900V#ff7b00P#ff5900+ &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("builder")) {
                if (player.hasPermission("xylum.prefix.builder")) {
                    setPrefix(player, "Builder", "#5ae0f2&lB#36caea&lU#00b5e0&lI#009fd5&lL#0089c8&lD#0072b8&lE#1a5ca6&lR&r &r");
                } else {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[Prefix] " + ChatColor.LIGHT_PURPLE + "You don't have permission for that prefix!");
                }

            } else if (args[0].equalsIgnoreCase("remove")) {
                removePrefix(player);
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Prefix] &dInvalid rank, try using tab complete. Use /prefix remove to clear current prefix."));
            }

        } else {

            try {
                player.sendMessage(" ");
                player.sendMessage(ChatColor.DARK_PURPLE + "How choosing a prefix works:");
                player.sendMessage(ChatColor.GRAY + " Tab complete the one you want displayed.");
                player.sendMessage(ChatColor.GRAY + "");
                player.sendMessage(ChatColor.AQUA + " That's it, you have a new prefix.");
                if (player.hasPermission("xylum.prefix.spooky")) {
                    player.sendMessage(ChatColor.GOLD + " SPOOKY: This have no effect on your prefixes.");
                } else if (player.hasPermission("xylum.staff")) {
                    player.sendMessage(ChatColor.RED + " STAFF: This have no effect on your prefixes.");
                }
            } finally {
                player.sendMessage(" ");
            }




        }
        return false;
    }

    private void removePrefix(Player player) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " meta removeprefix 35");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Prefix] &dRemoved custom prefix."));
    }

    private void setPrefix(Player player, String rank,String prefix) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " meta setprefix 35 \"" + prefix + "\"");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[Prefix] &dSuccesfully set prefix to " + rank + "'s."));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            ArrayList<String> arguments = new ArrayList<>();

            if (sender.hasPermission("xylum.prefix.use")) {
                arguments.add("remove");
            }
            if (sender.hasPermission("xylum.prefix.youtube")) {
                arguments.add("youtube");
            }
            if (sender.hasPermission("xylum.prefix.beta")) {
                arguments.add("beta");
            }
            if (sender.hasPermission("xylum.prefix.alpha")) {
                arguments.add("alpha");
            }
            if (sender.hasPermission("xylum.prefix.vip")) {
                arguments.add("vip");
            }
            if (sender.hasPermission("xylum.prefix.vip+")) {
                arguments.add("vip+");
            }
            if (sender.hasPermission("xylum.prefix.mvp")) {
                arguments.add("mvp");
            }
            if (sender.hasPermission("xylum.prefix.mvp+")) {
                arguments.add("mvp+");
            }
            if (sender.hasPermission("xylum.prefix.builder")) {
                arguments.add("builder");
            }

            return arguments;
        }

        return null;
    }
}
