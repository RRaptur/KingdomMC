package me.rapturr.wmc.items.kingdom;

import me.rapturr.wmc.helpers.Cooldown;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.C;

import java.util.Collection;

public class SparkWand extends WesternItem {
    public SparkWand(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
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
        if (!cooldown.hasCooldownTimeLeft(player)) {
            particleSpell(player);
            cooldown.setCooldown(player, 1000L);
        }
    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        if (!cooldown.hasCooldownTimeLeft(player)) {
            player.setVelocity(player.getEyeLocation().getDirection().multiply(1.2).add(new Vector(0, 0.8, 0)));
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 2, 1);
            player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 4, 0, 0, 0, 0);
            cooldown.setCooldown(player, 5000L);
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

    private void particleSpell(Player player) {
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 1);
        for (float i = 0; i < 80; i+=0.5) {
            player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i)), 0);
            for (Entity entity : player.getWorld().getNearbyEntities(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i)),0.5, 0.5, 0.5)) {
                if (entity instanceof LivingEntity && entity != player) {
                    ((LivingEntity) entity).damage(3, player);
                }
            }
        }
    }
}
