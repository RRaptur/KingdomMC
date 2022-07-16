package me.rapturr.wmc.items.kingdom;

import me.rapturr.wmc.helpers.AttributeHelpers;
import me.rapturr.wmc.helpers.Cooldown;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Scythe extends WesternItem {
    public Scythe(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    Cooldown cooldown = new Cooldown();

    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        AttributeHelpers.addAttribute(itemStack, Attribute.GENERIC_ATTACK_DAMAGE, 10, EquipmentSlot.HAND);
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

    }

    @Override
    public void rightClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {
        if (!cooldown.hasCooldownTimeLeft(player)) {
            if (entity instanceof LivingEntity livingEntity) {
                double health = livingEntity.getHealth();
                event.setDamage(EntityDamageEvent.DamageModifier.BASE, health * 0.3);
                Location l = livingEntity.getLocation().add(0,1.5, 0);
                livingEntity.getWorld().spawnParticle(Particle.BLOCK_DUST, l, 30, 0.2, 0.2, 0.2, Material.REDSTONE_BLOCK.createBlockData());
                livingEntity.getWorld().playSound(livingEntity.getLocation(), Sound.ENTITY_CAT_HISS, 2, 4);
                cooldown.setCooldown(player, 4000L);
            }
        }
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
