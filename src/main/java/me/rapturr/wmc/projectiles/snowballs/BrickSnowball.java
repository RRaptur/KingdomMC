package me.rapturr.wmc.projectiles.snowballs;

import me.rapturr.wmc.helpers.Utilities;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.items.WesternItems;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class BrickSnowball extends WesternSnowball {
    public BrickSnowball(String snowballID) {
        super(snowballID);
    }

    @Override
    public void onSnowballSpawn(Snowball snowball, ProjectileSource shooter) {
        snowball.setItem(new ItemStack(Material.BRICK));
        snowball.getWorld().playSound(snowball.getLocation(), Sound.BLOCK_DEEPSLATE_BRICKS_FALL, 3, 1);
        addParticleTrail(Particle.WHITE_ASH, 0, 0, 0, 0, 3);
    }

    @Override
    public void onSnowballHit(Snowball snowball, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event) {
        event.setDamage(2);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 4*20, 0, false, false, false));
        }
    }

    @Override
    public void onSnowballRemove(Snowball snowball, ProjectileSource shooter) {
        World world = snowball.getWorld();
        Location location = snowball.getLocation();
        world.playSound(location, Sound.BLOCK_DEEPSLATE_BRICKS_HIT, 3, 1);
        world.dropItem(location, WesternItem.getItemStack(WesternItems.BRICK));
        Block block = location.getBlock();
        if (block.getType() == Material.GLASS) {
            block.breakNaturally(new ItemStack(Material.GLASS));
            world.playSound(location, Sound.BLOCK_GLASS_BREAK, 2, 1);
            world.spawnParticle(Particle.SNOWBALL, location, 15, 0.4, 0.4, 0.4);
        }
    }
}
