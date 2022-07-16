package me.rapturr.wmc.projectiles.arrows;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.EntityUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class WesternArrow {

    private final String arrowID;
    private Arrow arrow;
    private Vector velocity;

    public WesternArrow(String arrowID) {
        this.arrowID = arrowID;
    }

    public void spawnArrow(Location loc, Vector velocity, float var1, float var2, ProjectileSource shooter) {

        arrow = loc.getWorld().spawnArrow(loc, velocity, var1, var2);
        arrow.setShooter(shooter);
        this.velocity = velocity;

        EntityUtils.storeStringInEntity(arrow, "is-western", "true");
        EntityUtils.storeStringInEntity(arrow, "arrow-id", arrowID);

        onArrowSpawn(arrow, shooter);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow == null)
                    this.cancel();
                if (arrow.isOnGround()) {
                    arrow.remove();
                }
                if (arrow.isDead()) {
                    this.cancel();
                    onArrowRemove(arrow, shooter);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, 1);
    }

    public void addParticleTrail(Particle particle, int var1, int var2, float var3, float var4, Particle.DustOptions dustOptions, int period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead())
                    this.cancel();
                if (arrow == null)
                    this.cancel();
                else {
                    arrow.getWorld().spawnParticle(particle, arrow.getLocation(), var1, var2, var3, var4, dustOptions);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, period);
    }

    public void addParticleTrail(Particle particle, int var1, int var2, float var3, float var4, int period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead())
                    this.cancel();
                if (arrow == null)
                    this.cancel();
                else {
                    arrow.getWorld().spawnParticle(particle, arrow.getLocation(), var1, var2, var3, var4);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, period);
    }

    public abstract void onArrowSpawn(Arrow arrow, ProjectileSource shooter);

    public abstract void onArrowHit(Arrow arrow, ProjectileSource shooter, Entity entity, EntityDamageByEntityEvent event);

    public abstract void onArrowRemove(Arrow arrow, ProjectileSource shooter);


    public Arrow getArrow() {
        return arrow;
    }

    public Vector getVelocity() {return velocity;}
}

