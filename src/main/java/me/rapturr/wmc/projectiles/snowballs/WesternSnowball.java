package me.rapturr.wmc.projectiles.snowballs;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.EntityUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class WesternSnowball {

    private final String snowballID;
    private Snowball snowball;
    private Vector velocity;

    public WesternSnowball(String snowballID) {
        this.snowballID = snowballID;
    }

    public void spawnSnowball(Location loc, Vector velocity, ProjectileSource shooter) {

        snowball = loc.getWorld().spawn(loc, Snowball.class);
        snowball.setShooter(shooter);
        snowball.setVelocity(velocity);
        this.velocity = velocity;

        EntityUtils.storeStringInEntity(snowball, "is-western", "true");
        EntityUtils.storeStringInEntity(snowball, "snowball-id", snowballID);

        onSnowballSpawn(snowball, shooter);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (snowball == null)
                    this.cancel();

                if (snowball.isDead()) {
                    this.cancel();
                    onSnowballRemove(snowball, shooter);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, 1);
    }

    public void addParticleTrail(Particle particle, int var1, int var2, float var3, float var4, Particle.DustOptions dustOptions, int period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (snowball.isDead())
                    this.cancel();
                if (snowball == null)
                    this.cancel();
                else {
                    snowball.getWorld().spawnParticle(particle, snowball.getLocation(), var1, var2, var3, var4, dustOptions);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, period);
    }

    public void addParticleTrail(Particle particle, int var1, int var2, float var3, float var4, int period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (snowball.isDead())
                    this.cancel();
                if (snowball == null)
                    this.cancel();
                else {
                    snowball.getWorld().spawnParticle(particle, snowball.getLocation(), var1, var2, var3, var4);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, period);
    }


    public abstract void onSnowballSpawn(Snowball snowball, ProjectileSource shooter);

    public abstract void onSnowballHit(Snowball snowball, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event);

    public abstract void onSnowballRemove(Snowball snowball, ProjectileSource shooter);


    public Snowball getSnowball() {
        return snowball;
    }

    public Vector getVelocity() {return velocity;}
}
