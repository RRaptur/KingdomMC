package me.rapturr.wmc.items.weapons;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.ItemstackUtils;
import me.rapturr.wmc.helpers.Utilities;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.projectiles.arrows.ShortbowArrow;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Shortbow extends WesternItem {

    public Shortbow(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }


    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        ItemstackUtils.storeStringInItemStack(itemStack, "false", "boolean");
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
        useShortbow(player);
    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        if (ItemstackUtils.getStringFromItemStack(itemStack, "boolean").equalsIgnoreCase("false")) {
            laserBeam(player);
        }
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


    private void useShortbow(Player player) {
        new ShortbowArrow("SHORTBOW_ARROW").spawnArrow(player.getEyeLocation(), player.getEyeLocation().getDirection(), 2, 3, player);
        new ShortbowArrow("SHORTBOW_ARROW").spawnArrow(player.getEyeLocation(), Utilities.rotateVector(player.getEyeLocation().getDirection(), 0.1), 2, 3, player);
        new ShortbowArrow("SHORTBOW_ARROW").spawnArrow(player.getEyeLocation(), Utilities.rotateVector(player.getEyeLocation().getDirection(), -0.1), 2, 3, player);


        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);

    }

    private void laserBeam(Player player) {
        ItemstackUtils.storeStringInItemStack(player.getInventory().getItemInMainHand(), "true", "boolean");
        new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = player.getLocation().add(0, 0.5, 0);

                if (player.isSneaking()) {
                    for(float i = 0; i < 80; i+=0.5) {
                        player.playSound(loc, Sound.ENTITY_TNT_PRIMED, 0.1f, 10f);
                        player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().add(player.getLocation().getDirection().multiply(i)), 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 2));
                        for (Entity e : player.getWorld().getNearbyEntities(loc.add(loc.getDirection().multiply(i)), 0.5, 0.5, 0.5)) {
                            if (e instanceof LivingEntity && e != player) {
                                ((LivingEntity) e).damage(5);
                                e.setFireTicks(60);
                            }
                        }
                    }
                } else {
                    this.cancel();
                    ItemstackUtils.storeStringInItemStack(player.getInventory().getItemInMainHand(), "false", "boolean");
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, 2);

    }
}
