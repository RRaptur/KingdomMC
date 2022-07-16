package me.rapturr.wmc.projectiles.snowballs;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

public class WhipProjectile extends WesternSnowball {
    public WhipProjectile(String snowballID) {
        super(snowballID);
    }

    @Override
    public void onSnowballSpawn(Snowball snowball, ProjectileSource shooter) {
        getSnowball().setItem(new ItemStack(Material.AIR));
        addParticleTrail(Particle.SMOKE_LARGE, 0, 0, 0, 0, 0);
    }

    @Override
    public void onSnowballHit(Snowball snowball, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event) {
       event.setDamage(5);
       entity.playEffect(EntityEffect.THORNS_HURT);
    }

    @Override
    public void onSnowballRemove(Snowball snowball, ProjectileSource shooter) {

    }
}
