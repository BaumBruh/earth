package behrz.xylum.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MobKill implements Listener {

    @EventHandler
    public void earthEvoker(EntityDeathEvent event) {
        event.getEntity().getLocation().getWorld().getName().equals("EarthRelease");
        event.getEntity().equals(EntityType.EVOKER);

        List<ItemStack> drops = event.getDrops();
        drops.clear();
        drops.add(new ItemStack(Material.EMERALD, 2));
        drops.add(new ItemStack(Material.ENDER_EYE, 1));

    }
}
