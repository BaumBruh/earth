package behrz.xylum.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import sun.tools.jconsole.Tab;

import java.util.ArrayList;
import java.util.List;

public class RulesCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("1")) {
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.DARK_GREEN + "XylumEarth rules" + ChatColor.DARK_GRAY + " [1/2]:");
                    player.sendMessage(ChatColor.GRAY + " 1. Use common sense");
                    player.sendMessage(ChatColor.GRAY + " 2. Respect everyone, staff and players alike.");
                    player.sendMessage(ChatColor.GRAY + " 3. Do not use any blacklisted modifications, Hacked Clients, Cheats, or exploits. Clientside mods that provide no unfair advantages are allowed.");
                    player.sendMessage(ChatColor.GRAY + "    3a. Baritone is prohibited.");
                    player.sendMessage(ChatColor.GRAY + "    3b. Xray packs/mods are prohibited.");
                    player.sendMessage(ChatColor.GRAY + " 4. Do not spam.");
                    player.sendMessage(ChatColor.GRAY + " 5. Do not be toxic. There is a line between trash talk and toxicity.");
                    player.sendMessage(ChatColor.GRAY + "    5a. Arguments are to be settled in messages.");
                    player.sendMessage(ChatColor.GRAY + " 6. Do not be racist, sexist, etc.");
                    player.sendMessage(ChatColor.GRAY + "    6a. Do not use any offensive words. Swears are fine.");
                    player.sendMessage(ChatColor.GRAY + " 7. Do not harass, cyberbully, or doxx players.");
                    player.sendMessage(ChatColor.GRAY + " 8. Do not massively grief the landscape.");
                    player.sendMessage(ChatColor.GRAY + "    8a. Any massive changes/projects, please receive permission from staff.");
                    player.sendMessage(ChatColor.GRAY + "    8b. Man-made islands must not be larger than 3x3 chunks/48x48 blocks.");
                    player.sendMessage(ChatColor.GRAY + " 9. Do not claim block.");
                    player.sendMessage(ChatColor.GRAY + " 10. Do not grief towns, players, nations, etc.");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.RED + "Do /rules 2 to read the next page.");
                    player.sendMessage(ChatColor.RED + "All rules are subject to change.");
                    player.sendMessage(" ");
                } else if (args[0].equalsIgnoreCase("2")) {
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.DARK_GREEN + "XylumEarth rules" + ChatColor.DARK_GRAY + " [2/2]:");
                    player.sendMessage(ChatColor.GRAY + " 11. Do not block waterways, pathways, railroads, etc unless in war, or there is a way to get through (monetezizing these is allowed)");
                    player.sendMessage(ChatColor.GRAY + " 12. Do not purposely cause lag to the server. If you do, you will be removed.");
                    player.sendMessage(ChatColor.GRAY + " 13. You are allowed 1 alt. Any more will be banned.");
                    player.sendMessage(ChatColor.GRAY + "    13a. Do not ban evade with alts.");
                    player.sendMessage(ChatColor.GRAY + " 14. Do not advertise other servers.");
                    player.sendMessage(ChatColor.GRAY + " 15. Do not dupe.");
                    player.sendMessage(ChatColor.GRAY + " 16. Do not exploit or find loophiles in rules for unfair advantages.");
                    player.sendMessage(ChatColor.GRAY + " 17. Stealing and scamming is allowed.");
                    player.sendMessage(ChatColor.GRAY + "    17a. Do not steal from privated chests.");
                    player.sendMessage(ChatColor.GRAY + " 18. You may only kill players in the wilderness or if your town has PVP on.");
                    player.sendMessage(ChatColor.GRAY + " 19. Do not push afk players outside of a town to kill them.");
                    player.sendMessage(ChatColor.GRAY + " 20. Do not use lava buckets on players if PVP is disabled.");
                    player.sendMessage(ChatColor.GRAY + " 21. If you are a donator, do not use /fly while in PVP.");
                    player.sendMessage(ChatColor.GRAY + " 22. Do not spawn kill or setup a trap that kills players.");
                    player.sendMessage(ChatColor.GRAY + "    22a. Spawn traps are allowed, provided they do no harm to players.");
                    player.sendMessage(ChatColor.GRAY + " 23. Do not build any hate flags, signs, or monuments.");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.RED + "Do /rules 1 to read the previous page.");
                    player.sendMessage(ChatColor.RED + "All rules are subject to change.");
                    player.sendMessage(" ");

                } else {
                    player.sendMessage(ChatColor.RED + "Rules only has 2 pages. /rules 1,2");
                }

            } else {
                player.sendMessage(" ");
                player.sendMessage(ChatColor.DARK_GREEN + "XylumEarth rules" + ChatColor.DARK_GRAY + " [1/2]:");
                player.sendMessage(ChatColor.GRAY + " 1. Use common sense");
                player.sendMessage(ChatColor.GRAY + " 2. Respect everyone, staff and players alike.");
                player.sendMessage(ChatColor.GRAY + " 3. Do not use any blacklisted modifications, Hacked Clients, Cheats, or exploits. Clientside mods that provide no unfair advantages are allowed.");
                player.sendMessage(ChatColor.GRAY + "    3a. Baritone is prohibited.");
                player.sendMessage(ChatColor.GRAY + "    3b. Xray packs/mods are prohibited.");
                player.sendMessage(ChatColor.GRAY + " 4. Do not spam.");
                player.sendMessage(ChatColor.GRAY + " 5. Do not be toxic. There is a line between trash talk and toxicity.");
                player.sendMessage(ChatColor.GRAY + "    5a. Arguments are to be settled in messages.");
                player.sendMessage(ChatColor.GRAY + " 6. Do not be racist, sexist, etc.");
                player.sendMessage(ChatColor.GRAY + "    6a. Do not use any offensive words. Swears are fine.");
                player.sendMessage(ChatColor.GRAY + " 7. Do not harass, cyberbully, or doxx players.");
                player.sendMessage(ChatColor.GRAY + " 8. Do not massively grief the landscape.");
                player.sendMessage(ChatColor.GRAY + "    8a. Any massive changes/projects, please receive permission from staff.");
                player.sendMessage(ChatColor.GRAY + "    8b. Man-made islands must not be larger than 3x3 chunks/48x48 blocks.");
                player.sendMessage(ChatColor.GRAY + " 9. Do not claim block.");
                player.sendMessage(ChatColor.GRAY + " 10. Do not grief towns, players, nations, etc.");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.RED + "Do /rules 2 to read the next page.");
                player.sendMessage(ChatColor.RED + "All rules are subject to change.");
                player.sendMessage(" ");
            }
        } else
            System.out.println("Only players can use /rules.");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            ArrayList<String> arguments = new ArrayList<>();
            arguments.add("1");
            arguments.add("2");

            return arguments;
        }
        return null;
    }
}
