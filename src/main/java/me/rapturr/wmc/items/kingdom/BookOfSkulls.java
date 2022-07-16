package me.rapturr.wmc.items.kingdom;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.ItemstackUtils;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BookOfSkulls extends WesternItem {
    public BookOfSkulls(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

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
        if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
            player.getInventory().setItemInOffHand(new ItemStack(Material.FIRE_CHARGE));
            player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.swingOffHand();
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1);
                    Fireball fireball = player.getWorld().spawn(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(2)), Fireball.class);
                    fireball.setIsIncendiary(false);
                    fireball.setFireTicks(0);
                    fireball.setVelocity(player.getEyeLocation().getDirection().multiply(3));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (fireball.isDead()) {
                                this.cancel();
                            } else fireball.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, fireball.getLocation(), 4, 0.2, 0.2, 0.2, 0);

                        }
                    }.runTaskTimer(WMC.getInstance(), 0, 0);
                    player.getInventory().setItemInOffHand(null);
                }
            }.runTaskLater(WMC.getInstance(), 10);
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
}
