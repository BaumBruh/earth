package com.github.polarbehrz.earth.listeners;

import com.github.polarbehrz.earth.Earth;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;

public class PlayerDeath implements Listener {

    @EventHandler
    public void deathLogger(PlayerDeathEvent event) {
        Earth.getPlugin().getLogger().warning(event.getEntity().getLocation().toString());
        Earth.getPlugin().getLogger().warning( event.getEntity().getName() + "'s inventory:");
        Earth.getPlugin().getLogger().warning(Arrays.toString(event.getEntity().getInventory().getContents()));
    }
}
