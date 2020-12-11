package com.github.polarbehrz.earth.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent ev){
        Player player = ev.getPlayer();
        ev.setQuitMessage(ChatColor.RED + " " + ChatColor.ITALIC + player.getDisplayName() + ChatColor.RED + " " + ChatColor.ITALIC + "left the server.");
    }
}
