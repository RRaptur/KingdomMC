package me.rapturr.wmc.listeners;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.Cooldown;
import me.rapturr.wmc.helpers.EntityUtils;
import me.rapturr.wmc.helpers.ItemstackUtils;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.Objects;

public class OnPlayerInteract implements Listener {


    @EventHandler
    private void interact(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();

        if (WesternItem.isWesternItem(mainHand)) {
            event.setCancelled(true);
            player.updateInventory();
            useWesternItem(event, mainHand);
        }
        spit(event, mainHand, player);



    }

    private void spit(PlayerInteractEvent event, ItemStack mainHand, Player player) {
        if (event.getAction() == Action.LEFT_CLICK_AIR && mainHand.getType() == Material.AIR) {
            if (player.isSneaking()) {
                if (!c.hasCooldownTimeLeft(player)) {
                    c.setCooldown(player, 5000L);
                    LlamaSpit llamaSpit = player.getWorld().spawn(player.getEyeLocation().subtract(0, 0.3, 0), LlamaSpit.class);
                    llamaSpit.setVelocity(player.getEyeLocation().getDirection().multiply(1.2f));
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1, 1f);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!llamaSpit.isDead()) {
                                Location l = llamaSpit.getLocation();
                                for (Entity e : llamaSpit.getWorld().getNearbyEntities(l, 0.5, 0.5, 0.5)) {
                                    if (e instanceof LivingEntity && e != player) {
                                        ((LivingEntity) e).damage(1, player);
                                        if (e instanceof Player) {
                                            ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3*20, 3, true, true, false));
                                        }
                                    }
                                }

                            } else this.cancel();
                        }
                    }.runTaskTimer(WMC.getInstance(), 0, 0);
                }
            }
        }
    }

    Cooldown c = new Cooldown();

    @EventHandler
    private void bowShoot(EntityShootBowEvent event) {
        final LivingEntity entity = event.getEntity();
        if (!(entity instanceof Player))
            return;
        else {
            Player player = (Player) entity;
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            if (!WesternItem.isWesternItem(mainHand))
                return;
            else {
                WesternItem.getWesternItem(mainHand).bowShootAction(player, event, mainHand);
            }
        }
    }

    @EventHandler
    private void playerHit(EntityDamageByEntityEvent event) {
        final Entity damager = event.getDamager();
        final Entity entity = event.getEntity();
        //PLAYER
        if (damager instanceof Player) {
            Player player = (Player) damager;
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            if (WesternItem.isWesternItem(mainHand))
                WesternItem.getWesternItem(mainHand).hitEntityAction(player, event, event.getEntity(), mainHand);
        }
        if(entity instanceof Player) {
            Player player = (Player) entity;
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            if (WesternItem.isWesternItem(mainHand))
                WesternItem.getWesternItem(mainHand).hitByEntityAction(player, event, damager, mainHand);
        }
        //ARROW
        if (damager instanceof Arrow) {
            Arrow arrow = (Arrow) damager;
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                if (EntityUtils.getStringFromEntity(arrow, "is-western") != null) {
                    WMC.westernArrows.get(EntityUtils.getStringFromEntity(arrow, "arrow-id")).onArrowHit(arrow, player, event.getEntity(), event);
                }
            }
        }
        //SNOWBALL
        if (damager instanceof Snowball) {
            Snowball snowball = (Snowball) damager;
            if (snowball.getShooter() instanceof Player) {
                Player player = (Player) snowball.getShooter();
                if (EntityUtils.getStringFromEntity(snowball, "is-western") != null) {
                    WMC.westernSnowballs.get(EntityUtils.getStringFromEntity(snowball, "snowball-id")).onSnowballHit(snowball, player, entity, event);
                }
            }
        }

        /*
        if (General.isWesternEntity(entity)) {
            WMC.westernMobs.get(General.getStringFromEntity(entity, "mob-id")).onDamaged(entity, damager, event);
        }

        if (General.isWesternEntity(damager)) {
            WMC.westernMobs.get(General.getStringFromEntity(damager, "mob-id")).onHit(entity, damager, event);
        }
         */


        else return;
    }

    @EventHandler
    private void onRodCast(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!WesternItem.isWesternItem(item)) {
            return;
        } else {
            WesternItem.getWesternItem(item).onRodAction(player, item, event);
        }
    }

    /*
    @EventHandler
    public void cancelLeash(PlayerLeashEntityEvent event) {
        event.setCancelled(true);
    }
     */

    private void useWesternItem(PlayerInteractEvent event, ItemStack item) {
        Player player = event.getPlayer();

        WesternItem westernItem = WesternItem.getWesternItem(item);
        assert westernItem != null;

        switch (event.getAction()) {

            case LEFT_CLICK_AIR:
                westernItem.leftClickAirAction(player, item, event);
                break;

            case LEFT_CLICK_BLOCK:
                westernItem.leftClickBlockAction(player, item, event, event.getClickedBlock());
                break;
            case RIGHT_CLICK_AIR:
                westernItem.rightClickAirAction(player, item, event);
                break;

            case RIGHT_CLICK_BLOCK:
                westernItem.rightClickBlockAction(player, item, event, event.getClickedBlock());
                break;


        }
    }

    @EventHandler
    private void onPickUpItem(EntityPickupItemEvent event) {
        final LivingEntity entity = event.getEntity();
        final Item item = event.getItem();
        final ItemStack itemStack = item.getItemStack();
        if (entity instanceof Player) {
            if (WesternItem.isWesternItem(itemStack)) {
                Objects.requireNonNull(WesternItem.getWesternItem(itemStack)).onPickUpAction((Player) entity, item, itemStack, event);
            }
        }

    }

    @EventHandler
    private void onDropItem(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        final Item item = event.getItemDrop();
        final ItemStack itemStack = item.getItemStack();
        if (WesternItem.isWesternItem(itemStack) ) {
            Objects.requireNonNull(WesternItem.getWesternItem(itemStack)).onDropAction(player, itemStack, event);
        }
    }

    /*
    @EventHandler
    private void onArmorStandInteract(PlayerArmorStandManipulateEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission("armorstand.edit"))
            return;
        else {
            event.setCancelled(true);
            player.updateInventory();
        }
    }
     */

    public static void activeListener() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    activeEffect(player);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, 0);
    }

    private static void activeEffect(Player player) {
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (WesternItem.isWesternItem(mainHand)) {
            if (ItemstackUtils.getStringFromItemStack(mainHand, "active-effect") != null) {
                Objects.requireNonNull(WesternItem.getWesternItem(mainHand)).activeEffect(player, mainHand);
            }
        }

    }

    private boolean isOnGround(Player player) {
        Location loc = player.getLocation().subtract(0, 1, 0);
        Block block = loc.getBlock();
        return !block.isPassable();

    }
    @EventHandler
    private void doubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (Objects.equals(EntityUtils.getStringFromEntity(player, "has-double-jump"), "true")) {
            if (Objects.equals(EntityUtils.getStringFromEntity(player, "double-jump"), "true")) {
                event.setCancelled(true);
                EntityUtils.storeStringInEntity(player, "double-jump", "false");
                player.setAllowFlight(false);
                player.setVelocity(player.getEyeLocation().getDirection().add(new Vector(0, 0.6, 0)));
                player.playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1f, 1f);
                player.spawnParticle(Particle.CLOUD, player.getLocation().subtract(0, 1, 0), 1, 0, 0, 0, 0);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (((Entity) player).isOnGround()) {
                            player.setAllowFlight(true);
                            this.cancel();
                            EntityUtils.storeStringInEntity(player, "double-jump", "true");
                        }
                    }
                }.runTaskTimer(WMC.getInstance(), 0, 0);
            }
        }
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        EntityUtils.storeStringInEntity(player, "double-jump", "false");
        EntityUtils.storeStringInEntity(player, "has-double-jump", "false");
    }
}

