package behrz.xylum.listeners;

import behrz.xylum.Earth;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class World extends BukkitRunnable {

    Earth plugin;

    public World(Earth plugin) { this.plugin = plugin; }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {

            switch (player.getWorld().getName()) {
                case "MarsRelease":

                    if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
                        return;
                    }

                    if (!player.getActivePotionEffects().contains(PotionEffectType.REGENERATION)) {
                        if (!armorCheck(player.getEquipment().getHelmet().getType(), player.getEquipment().getChestplate().getType(), player.getEquipment().getLeggings().getType(), player.getEquipment().getBoots().getType())) {

                            player.sendMessage(ChatColor.RED + " " + ChatColor.UNDERLINE + "A spacesuit is required on Mars!");
                            spacesuitEffects(player);
                        }
                    }


                case "MoonRelease":

                    if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                        if (!armorCheck(getHelmet(player), getChestplate(player), getLeggings(player), getBoots(player))) {

                            player.sendMessage(ChatColor.RED + " " + ChatColor.UNDERLINE + "A spacesuit is required on the Moon!");
                            spacesuitEffects(player);
                        }
                    }

            }
        }
    }

    private void spacesuitEffects(Player player) {
        player.addPotionEffect(PotionEffectType.WITHER.createEffect(20,5));
        player.damage(6);
    }

    private Material getHelmet(Player player) {
        try {
            if (player.getEquipment().getHelmet() == null) {
                return Material.AIR;
            } else {
                return player.getEquipment().getHelmet().getType();
            }
        } catch (NullPointerException e) {
            return Material.AIR;
        }

    }

    private Material getChestplate(Player player) {
        try {
            if (player.getEquipment().getChestplate() == null) {
                return Material.AIR;
            } else {
                return player.getEquipment().getChestplate().getType();
            }
        } catch (NullPointerException e) {
            return Material.AIR;
        }
    }

    private Material getLeggings(Player player) {
        try {
            if (player.getEquipment().getLeggings() == null) {
                return Material.AIR;
            } else {
                return player.getEquipment().getLeggings().getType();
            }
        } catch (NullPointerException e) {
            return Material.AIR;
        }
    }

    private Material getBoots(Player player) {
        try {
            if (player.getEquipment().getBoots() == null) {
                return Material.AIR;
            } else {
                return player.getEquipment().getBoots().getType();
            }
        } catch (NullPointerException e) {
            return Material.AIR;
        }
    }

    private boolean armorCheck(Material helmet, Material chestplate, Material leggings, Material boots) {
        boolean helmetCheck = false;
        boolean chestplateCheck  = false;
        boolean leggingsCheck = false;
        boolean bootsCheck = false;

        if (helmet == Material.IRON_HELMET ||
                helmet == Material.NETHERITE_HELMET ||
                helmet == Material.GOLDEN_HELMET ||
                helmet == Material.LEATHER_HELMET) {
            helmetCheck = true;
        }

        if (chestplate == Material.IRON_CHESTPLATE ||
                chestplate == Material.NETHERITE_CHESTPLATE ||
                chestplate == Material.GOLDEN_CHESTPLATE ||
                helmet == Material.LEATHER_CHESTPLATE) {
            chestplateCheck = true;
        }

        if (leggings == Material.IRON_LEGGINGS ||
                leggings == Material.NETHERITE_LEGGINGS ||
                leggings == Material.GOLDEN_LEGGINGS ||
                helmet == Material.LEATHER_LEGGINGS) {
            leggingsCheck = true;
        }

        if (boots == Material.IRON_BOOTS ||
                boots == Material.NETHERITE_BOOTS ||
                boots == Material.GOLDEN_BOOTS ||
                boots == Material.LEATHER_BOOTS) {
            bootsCheck = true;
        }

        if (!helmetCheck || !chestplateCheck || !leggingsCheck || !bootsCheck) {
            return false;
        } else {
            return true;
        }
    }
}
