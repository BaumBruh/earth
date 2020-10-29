package behrz.xylum;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AltCommand implements CommandExecutor {
    private Xylum plugin;

    public AltCommand(Xylum plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("altdetector.alt")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.config.getAltCmdNoPerm()));
            return true;
        }
        List<String> playerList = new ArrayList<>();
        if (args.length == 0) {
            for (Player player : this.plugin.getServer().getOnlinePlayers()) {
                if (!player.hasPermission("altdetector.exempt"))
                    playerList.add(player.getName());
            }
            Collections.sort(playerList, String.CASE_INSENSITIVE_ORDER);
        } else if (args.length == 1) {
            Player player = Bukkit.getServer().getPlayerExact(args[0]);
            if (player == null) {
                handleOfflinePlayer(sender, args[0]);
                return true;
            }
            if (!player.hasPermission("altdetector.exempt"))
                playerList.add(player.getName());
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.config.getAltCmdParamError()));
            return true;
        }
        boolean altsFound = false;
        for (String name : playerList) {
            Player player = Bukkit.getServer().getPlayerExact(name);
            String ip = player.getAddress().getAddress().getHostAddress();
            String uuid = player.getUniqueId().toString();
            altsFound |= outputAlts(sender, name, ip, uuid);
        }
        if (!altsFound)
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.config.getAltCmdNoAlts()));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(this.plugin.config.getAltCmdPlayerNoAlts(), new Object[]{args[0]})));
            }
        return true;
    }

    private void handleOfflinePlayer(CommandSender sender, String playerName) {
        AltData.PlayerDataType playerData = this.plugin.altData.lookupOfflinePlayer(playerName);
        if (playerData == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(this.plugin.config.getAltCmdPlayerNotFound(), new Object[] { playerName })));
        } else {
            boolean altsFound = outputAlts(sender, playerData.name, playerData.ip, playerData.uuid);
            if (!altsFound)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(this.plugin.config.getAltCmdPlayerNoAlts(), new Object[] { playerData.name })));
        }
    }

    private boolean outputAlts(CommandSender sender, String name, String ip, String uuid) {
        String altString = this.plugin.altData.getFormattedAltString(name,
                ip,
                uuid,
                this.plugin.config.getAltCmdPlayer(),
                this.plugin.config.getAltCmdPlayerList(),
                this.plugin.config.getAltCmdPlayerSeparator());
        if (altString != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', altString));
            return true;
        }
        return false;
    }
}
