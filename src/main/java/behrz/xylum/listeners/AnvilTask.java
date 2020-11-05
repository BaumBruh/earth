package behrz.xylum.listeners;

import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class AnvilTask extends BukkitRunnable {
    private static HashMap<AnvilInventory, AnvilTask> anvilTasks = new HashMap<>();

    private AnvilInventory inv;

    private Player player;

    public AnvilTask(AnvilInventory inv, Player player, Plugin plugin) {
        this.inv = inv;
        this.player = player;
        anvilTasks.put(inv, this);
        runTaskTimer(plugin, 1L, 3L);
    }

    public void run() {
        if (this.inv.getViewers().size() == 0)
            cancel();
        AnvilColor.getTranslatedItem(this.player, this.inv, this);
    }

    public static AnvilTask getTask(AnvilInventory inv) {
        return anvilTasks.get(inv);
    }
}
