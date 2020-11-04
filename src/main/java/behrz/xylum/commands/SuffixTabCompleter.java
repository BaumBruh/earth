package behrz.xylum.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SuffixTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            ArrayList<String> arguments = new ArrayList<>();
            arguments.add("dark_green");
            arguments.add("dark_aqua");
            arguments.add("dark_red");
            arguments.add("dark_purple");
            arguments.add("gold");
            arguments.add("gray");
            arguments.add("dark_gray");
            arguments.add("blue");
            arguments.add("green");
            arguments.add("aqua");
            arguments.add("red");
            arguments.add("light_purple");
            arguments.add("yellow");
            arguments.add("white");
            arguments.add("remove");

            return arguments;
        }

        return null;
    }
}
