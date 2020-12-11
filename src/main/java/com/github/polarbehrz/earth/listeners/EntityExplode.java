package com.github.polarbehrz.earth.listeners;

import com.github.polarbehrz.earth.Earth;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode implements Listener {
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.ENDER_CRYSTAL) {
            event.setCancelled(true);
            Earth.getPlugin().getLogger().warning("An end crystal tried to explode at: " + event.getLocation().toString());
        }
    }
}
