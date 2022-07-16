package me.rapturr.wmc.items.kingdom;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
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
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Bonemerang extends WesternItem {
    boolean isReloaded = true;

    public Bonemerang(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, Boolean hasActiveEffect) {
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
        throwBonermang(player);
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

    private void throwBonermang(Player player) {

        if (isReloaded) {

            ArmorStand as = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
            Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(10));

            player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 7);

            as.setArms(true);
            as.setGravity(false);
            as.setVisible(false);
            as.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
            as.setRightArmPose(new EulerAngle(0, Math.toRadians(120), 0));

            Vector vector = destination.subtract(player.getLocation()).toVector();

            isReloaded = false;

            new BukkitRunnable() {

                int distance = 40;
                int i = 0;

                @Override
                public void run() {

                    EulerAngle rot = as.getRightArmPose();
                    as.setRightArmPose(rot.add(0, Math.toRadians(35), 0));

                    if (i >= distance) {
                        as.teleport(as.getLocation().subtract(vector.normalize()));
                        if (i >= distance * 2) {
                            as.remove();
                            isReloaded = true;
                            cancel();
                        }
                    } else {
                        as.teleport(as.getLocation().add(vector.normalize()));
                    }

                    i++;

                    for (Entity entity : as.getNearbyEntities(0.5, 0.5, 0.5)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.damage(10, player);
                        }
                    }

                    if (!as.getLocation().add(0, 1, 0).getBlock().isPassable()) {
                        as.remove();
                        isReloaded = true;
                        cancel();
                        as.getWorld().spawnParticle(Particle.CLOUD, as.getLocation().add(0, 1.5, 0), 10, 0.2, 0.2, 0.2, 0);
                    }
                }


            }.runTaskTimer(WMC.getInstance(), 1L, 1L);
        } else player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 0.5f, 1);
    }
}
