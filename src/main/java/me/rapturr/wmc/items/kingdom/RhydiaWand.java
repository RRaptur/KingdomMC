package me.rapturr.wmc.items.kingdom;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.*;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.projectiles.arrows.ShortbowArrow;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class RhydiaWand extends WesternItem {
    public RhydiaWand(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        ItemstackUtils.storeIntegerInItemStack(itemStack, 0, "spell");
    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {
        World world = player.getWorld();
        world.spawnParticle(Particle.CLOUD, player.getLocation(), 1, 0.3, 0.3, 0.3, 0);
        generateParticleAroundPlayer(player);
        world.spawnParticle(Particle.DRIP_LAVA, player.getLocation(), 20, 5, 5, 5, 0);
        world.setTime(world.getTime() + 100L);
        int i = Utilities.createRandomInt(0, 1000);
        if (i < 20) {
            world.strikeLightningEffect(player.getLocation());
            world.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2, 1);

        }
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
        }
        for (Entity entity: player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity livingEntity && entity != player) {
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 0, true, true, false));
            }
        }

    }

    @Override
    public void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event) {
    }

    @Override
    public void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event) {
    }

    @Override
    public void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        castSpell(player, itemStack);
    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        int i = ItemstackUtils.getIntegerFromItemStack(itemStack, "spell");
        if (i > 6) {
            ItemstackUtils.storeIntegerInItemStack(itemStack, 0, "spell");
        } else ItemstackUtils.storeIntegerInItemStack(itemStack, i +1, "spell");

        switchSpell(player, itemStack);
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

    private void castSpell(Player player, ItemStack itemStack) {
        int i = ItemstackUtils.getIntegerFromItemStack(itemStack, "spell");
        switch (i) {
            case 0:
                fireballSpell(player);
                break;
            case 1:
                smiteSpell(player);
                break;
            case 2:
                witherSkullSpell(player);
                break;
            case 3:
                particleSpell(player);
                break;
            case 4:
                tntSpell(player);
                break;
            case 5:
                volleySpell(player);
                break;
            case 6:
                sonicBoomSpell(player);
                break;
            case 7:
                laserSpell(player);
                break;
        }
    }

    private void switchSpell(Player player, ItemStack itemStack) {
       int i = ItemstackUtils.getIntegerFromItemStack(itemStack, "spell");
       switch (i) {
           case 0:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Fireball");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 1:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Smite");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 2:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Wither Skull");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 3:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Spark");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 4:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: TNT");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 5:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Arrow Voley");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 6:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Sonic Boom");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
           case 7:
               Utilities.sendActionBarMessage(player, ChatColor.GREEN + "Spell: Laser");
               player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 2,1);
               break;
       }
    }

    private void fireballSpell(Player player) {
        Fireball fireball = player.getWorld().spawn(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(2)), Fireball.class);
        fireball.setIsIncendiary(true);
        fireball.setDirection(Utilities.rotateVector(player.getEyeLocation().getDirection(),  Utilities.createRandomInt(0, 3)));
        fireball.setVelocity(player.getEyeLocation().getDirection().multiply(1.5));
    }
    private void smiteSpell(Player player) {
        World world = player.getWorld();
        Location loc = Objects.requireNonNull(player.getTargetBlockExact(500)).getLocation();
        world.strikeLightning(loc);
    }
    private void witherSkullSpell(Player player) {
        WitherSkull witherSkull = player.getWorld().spawn(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(2)), WitherSkull.class);
        witherSkull.setCharged(true);
        witherSkull.setDirection(Utilities.rotateVector(player.getEyeLocation().getDirection(), Utilities.createRandomInt(0, 2)));
        witherSkull.setVelocity(player.getEyeLocation().getDirection().multiply(4));
    }
    private void particleSpell(Player player) {
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 3, 1);
        for (float i = 0; i < 80; i+=0.5) {
            player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i)), 0);
            for (Entity entity : player.getWorld().getNearbyEntities(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i)),0.5, 0.5, 0.5)) {
                if (entity instanceof LivingEntity && entity != player) {
                    ((LivingEntity) entity).damage(4, player);
                }
            }
        }
    }
    private void tntSpell(Player player) {
        TNTPrimed tntPrimed = player.getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);
        tntPrimed.setVelocity(player.getEyeLocation().getDirection().multiply(3));
        tntPrimed.setFuseTicks(3*20);
    }
    private void volleySpell(Player player) {
        for (int i = 0; i < Utilities.createRandomInt(20, 30); i++) {
            new ShortbowArrow("SHORTBOW_ARROW").spawnArrow(player.getEyeLocation(), player.getEyeLocation().getDirection(), 2, 3, player);
        }
    }
    private void sonicBoomSpell(Player player) {
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
    }
    private void laserSpell(Player player) {
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
    float i = 0f;
    public void generateParticleAroundPlayer(Player player) {
        Location loc = player.getEyeLocation().add(0, 0.3, 0);
        World world = player.getWorld();

        world.spawnParticle(Particle.FLAME, loc.getX() + Math.cos(i) / 2, loc.getY(), loc.getZ() + Math.sin(i) / 2, 1, 0, 0, 0, 0);
        i += 1;

        if (i >= 360f)
        {
            i = 0.0f;
        }
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
}
