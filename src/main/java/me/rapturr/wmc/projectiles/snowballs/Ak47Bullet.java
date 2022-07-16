package me.rapturr.wmc.projectiles.snowballs;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

public class Ak47Bullet extends WesternSnowball {
    public Ak47Bullet(String snowballID) {
        super(snowballID);
    }

    @Override
    public void onSnowballSpawn(Snowball snowball, ProjectileSource shooter) {
        addParticleTrail(Particle.SMOKE_NORMAL, 0, 0, 0, 0, 1);
        snowball.setItem(new ItemStack(Material.ACACIA_BUTTON));
        snowball.setVisualFire(true);
    }

    @Override
    public void onSnowballHit(Snowball snowball, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event) {
        event.setDamage(EntityDamageEvent.DamageModifier.MAGIC, 4);

    }

    @Override
    public void onSnowballRemove(Snowball snowball, ProjectileSource shooter) {

    }
}
