package me.rapturr.wmc.items.kingdom;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.Utilities;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class Anduril extends WesternItem {
    public Anduril(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }


    @Override
    public void onitemstackcreate(ItemStack itemStack) {

    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2, false, false, false));

    }

    @Override
    public void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event) {
    }

    @Override
    public void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event) {

    }

    @Override
    public void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 1, 1);
        player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, player.getLocation().add(0, 1.5, 0).add(player.getEyeLocation().getDirection().multiply(2)), 1, 0, 0, 0, 0);
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
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.getWorld().spawnParticle(Particle.BLOCK_DUST, livingEntity.getLocation().add(0, 1.5, 0), 30, 0.2, 0.2, 0.2, Material.REDSTONE_BLOCK.createBlockData());
        }
    }

    @Override
    public void hitByEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {
        int i = Utilities.createRandomInt(0, 10) ;
            if (i <= 1) {
                event.setDamage(0);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE,2, 1.5f);
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(1.4)), 10, 0.2, 0.2, 0.2, 1);
            }
    }

    @Override
    public void bowShootAction(Player player, EntityShootBowEvent event, ItemStack itemStack) {

    }

    @Override
    public void onRodAction(Player player, ItemStack itemStack, PlayerFishEvent event) {

    }

    private void test(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE,2, 1.5f);
        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(1.4)), 10, 0.2, 0.2, 0.2, 3);

    }
}
