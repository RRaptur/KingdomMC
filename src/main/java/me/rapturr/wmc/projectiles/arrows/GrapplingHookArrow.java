package me.rapturr.wmc.projectiles.arrows;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

public class GrapplingHookArrow extends WesternArrow {
    public GrapplingHookArrow(String arrowID) {
        super(arrowID);
    }

    @Override
    public void onArrowSpawn(Arrow arrow, ProjectileSource shooter) {
       addParticleTrail(Particle.ASH, 0, 0,0,0, 2);
       getArrow().setCritical(true);

    }

    @Override
    public void onArrowHit(Arrow arrow, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event) {
        event.setDamage(0);


    }

    @Override
    public void onArrowRemove(Arrow arrow, ProjectileSource shooter) {
        if (shooter instanceof Player) {

            Player player = (Player) shooter;


            Snowball mount = player.launchProjectile(Snowball.class);
            mount.addPassenger(player);
            mount.setInvulnerable(true);
            mount.setItem(new ItemStack(Material.SADDLE));
            player.playSound(player.getEyeLocation(), Sound.ENTITY_HORSE_SADDLE, 1, 1);


            mount.setVelocity(getVelocity());

        }
    }
}
