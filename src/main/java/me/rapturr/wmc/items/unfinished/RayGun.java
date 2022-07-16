package me.rapturr.wmc.items.unfinished;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.Cooldown;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.WesternExplosion;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RayGun extends WesternItem {
    public RayGun(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    Cooldown cooldown = new Cooldown();

    @Override
    public void onitemstackcreate(ItemStack itemStack) {

    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {

    }

    @Override
    public void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event) {

    }

    @Override
    public void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event) {

    }

    @Override
    public void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        if (!cooldown.hasCooldownTimeLeft(player)) {
            cooldown.setCooldown(player, 3000L);
            player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 4, 2);
            for (float i = 0; i <=80; i+=2) {
                Location loc = player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i));
                loc.getWorld().spawnParticle(Particle.SONIC_BOOM, loc, 0);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        loc.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc, 0);

                    }
                }.runTaskLater(WMC.getInstance(), 20);
                if (!loc.getBlock().isPassable()) {
                    impact(player, loc);
                    break;
                }
                if (i == 80) {
                    impact(player, loc);
                }

            }
        } else player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }

    private void impact(Player player, Location loc) {
        new BukkitRunnable() {
            @Override
            public void run() {
                WesternExplosion.createExplosion(loc);
                player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc, 10);
            }
        }.runTaskLater(WMC.getInstance(), 15);
    }

    @Override
    public void rightClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {

    }
    @Override
    public void hitByEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {

    }
    @Override
    public void bowShootAction(Player player, EntityShootBowEvent event, ItemStack itemStack) {

    }

    @Override
    public void onRodAction(Player player, ItemStack itemStack, PlayerFishEvent event) {

    }
}
