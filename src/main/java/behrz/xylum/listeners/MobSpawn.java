package behrz.xylum.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MobSpawn implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void mobSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();
        World world = event.getLocation().getWorld();
        Location loc = event.getLocation();

        switch (world.getName()) {
            case "MarsRelease":
                switch (spawned.getType()) {
                    case SKELETON:
                        if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.ILLUSIONER);
                            return;
                        } else if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.EVOKER);
                            return;
                        } else if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.MAGMA_CUBE);
                            return;
                        } else if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.WITHER_SKELETON);
                            return;
                        }

                        Skeleton skeleton = (Skeleton) spawned;
                        goldSkeleton(skeleton);

                    case ZOMBIE:
                        if (chance(10)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.PILLAGER);
                            return;
                        } else if (chance(10)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.VINDICATOR);
                            return;
                        }

                        Zombie zombie = (Zombie) spawned;
                        goldZombie(zombie);

                    case ZOMBIE_VILLAGER:
                        spawned.remove();
                        world.spawnEntity(loc, EntityType.RAVAGER);
                        return;

                    case WANDERING_TRADER:
                        spawned.remove();
                        world.spawnEntity(loc, EntityType.RAVAGER);
                        return;

                    case CREEPER:
                        if (chance(30)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.BLAZE);
                            return;
                        }
                }
            case "MoonRelease":
                switch (spawned.getType()) {
                    case SKELETON:
                        if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.ILLUSIONER);
                            return;
                        } else if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.EVOKER);
                            return;
                        } else if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.MAGMA_CUBE);
                            return;
                        } else if (chance((int) 0.5)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.WITHER_SKELETON);
                            return;
                        }

                        Skeleton skeleton = (Skeleton) spawned;
                        goldSkeleton(skeleton);

                    case ZOMBIE:
                        if (chance(10)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.PILLAGER);
                            return;
                        } else if (chance(10)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.VINDICATOR);
                            return;
                        }

                        Zombie zombie = (Zombie) spawned;
                        goldZombie(zombie);

                    case ZOMBIE_VILLAGER:
                        spawned.remove();
                        world.spawnEntity(loc, EntityType.RAVAGER);
                        return;

                    case WANDERING_TRADER:
                        spawned.remove();
                        world.spawnEntity(loc, EntityType.RAVAGER);
                        return;

                    case CREEPER:
                        if (chance(30)) {
                            spawned.remove();
                            world.spawnEntity(loc, EntityType.BLAZE);
                            return;
                        }
                }
        }
        return;
    }

    public boolean chance(int outOf100) {
        return random.nextInt(100) < outOf100;
    }

    public boolean goldSkeleton(Skeleton skeleton) {
        if (skeleton.getEquipment().getHelmet().equals(new ItemStack(Material.GOLDEN_HELMET))) {
            skeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        }

        if (skeleton.getEquipment().getChestplate().equals(new ItemStack(Material.GOLDEN_CHESTPLATE))) {
            skeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        }

        if (skeleton.getEquipment().getLeggings().equals(new ItemStack(Material.GOLDEN_LEGGINGS))) {
            skeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        }

        if (skeleton.getEquipment().getBoots().equals(new ItemStack(Material.GOLDEN_BOOTS))) {
            skeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_BOOTS));
        }
        return true;
    }

    public boolean goldZombie(Zombie zombie) {
        if (zombie.getEquipment().getHelmet().equals(new ItemStack(Material.GOLDEN_HELMET))) {
            zombie.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        }

        if (zombie.getEquipment().getChestplate().equals(new ItemStack(Material.GOLDEN_CHESTPLATE))) {
            zombie.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        }

        if (zombie.getEquipment().getLeggings().equals(new ItemStack(Material.GOLDEN_LEGGINGS))) {
            zombie.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        }

        if (zombie.getEquipment().getBoots().equals(new ItemStack(Material.GOLDEN_BOOTS))) {
            zombie.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_BOOTS));
        }
        return true;
    }
}
