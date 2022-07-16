package me.rapturr.wmc.projectiles.arrows;


import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class ShortbowArrow extends WesternArrow {

    public ShortbowArrow(String arrowID) {
        super(arrowID);
    }

    @Override
    public void onArrowSpawn(Arrow arrow, ProjectileSource shooter) {
        /*
        addParticleTrail(Particle.SMOKE_LARGE, 0, 0, 0, 0, 0);
        addParticleTrail(Particle.REDSTONE, 0, 0, 0, 0, new Particle.DustOptions(Color.PURPLE, 2), 5);
         */
    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event) {
        if (shooter instanceof Player) {
            Player player = (Player) shooter;
            player.playSound(entity.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
            float damage = 4f;
            event.setDamage(damage);
        }
        entity.playEffect(EntityEffect.VILLAGER_HEART);
    }

    @Override
    public void onArrowRemove(Arrow arrow, ProjectileSource shooter) {

    }
}
