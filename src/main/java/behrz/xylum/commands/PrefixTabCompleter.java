package behrz.xylum.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class PrefixTabCompleter implements TabCompleter {

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
