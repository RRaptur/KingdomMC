package me.rapturr.wmc.projectiles.snowballs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class GrapplingHookSnowball extends WesternSnowball {
    public GrapplingHookSnowball(String snowballID) {
        super(snowballID);
    }

    @Override
    public void onSnowballSpawn(Snowball snowball, ProjectileSource shooter) {
        addParticleTrail(Particle.CRIT, 0, 1, 0, 0, 0);
        getSnowball().setItem(new ItemStack(Material.NETHERITE_PICKAXE));

    }

    @Override
    public void onSnowballHit(Snowball snowball, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event) {
        event.setDamage(0);
    }

    @Override
    public void onSnowballRemove(Snowball snowball, ProjectileSource shooter) {

        if (shooter instanceof Player) {

            Player player = (Player) shooter;


            Snowball mount = player.launchProjectile(Snowball.class);
            mount.addPassenger(player);
            mount.setInvulnerable(true);
            mount.setItem(new ItemStack(Material.SADDLE));
            player.playSound(player.getEyeLocation(), Sound.ENTITY_HORSE_SADDLE, 1, 1);

            Location l1 = player.getLocation();
            Location l2 = snowball.getLocation();

            Vector v = new Vector(l2.getX() - l1.getX(), 1.0D, l2.getZ() - l1.getZ());

            mount.setVelocity(getVelocity());

        }
    }
}
