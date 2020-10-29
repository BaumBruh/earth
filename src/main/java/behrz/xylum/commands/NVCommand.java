package behrz.xylum.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NVCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (!player.hasPermission("nightvision.use")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eOnly VIP and above can use /nv."));
                return true;
            }
            if (args.length == 0) {
                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7&oNight vision disabled"));
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    return true;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7&oNight vision enabled"));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9000000, 2));
                return true;
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null || !target.isOnline()) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eCould not find player" + target.getDisplayName() + "&e."));
                    return true;
                }
                if (!player.hasPermission("nightvision.player")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eOnly staff can toggle the night vision of another player."));
                    return true;
                }
                if (target.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    target.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eYour night vision was disabled."));
                    target.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    player.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eDisabled night vision for" + target.getDisplayName() + "&e."));
                    return true;
                }
                target.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eYour night vision was enabled.".replaceAll("%player%", player.getDisplayName())));
                target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9000000, 2));
                player.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eEnabled night vision for" + target.getDisplayName() + "&e."));
                return true;
            }
            if (args.length != 1 && args.length != 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Nightvision] &eCorrect usage is /nv [playername]"));
                return true;
            }
        }
        return false;
    }
}
