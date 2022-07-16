package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class CustomArrow {

    private final Location loc;
    private final Arrow arrow;
    private final Vector vector;
    private final int var1;
    private final int var2;
    private final ProjectileSource shooter;
    private final String arrowName;
    private final WMC plugin = WMC.getInstance();

    public CustomArrow(Location loc, Vector vector, int var1, int var2, ProjectileSource shooter, String arrowName) {
        this.loc = loc;
        this.vector = vector;
        this.var1 = var1;
        this.var2 = var2;
        this.shooter = shooter;
        this.arrowName = arrowName;
        this.arrow = Objects.requireNonNull(loc.getWorld()).spawnArrow(loc, vector, var1, var2);
        arrow.setShooter(shooter);
        arrow.setCustomName(arrowName);


        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isOnGround()) {
                    arrow.remove();
                    this.cancel();
                }
                if (arrow.isDead()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public void addParticleTrail(Particle particle, int i, int v, float var1, float var2, float var3) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!arrow.isDead()) {
                    Objects.requireNonNull(loc.getWorld()).spawnParticle(particle, arrow.getLocation(), i, v, var1, var2, var3);
                } else this.cancel();
            }
        }.runTaskTimer(plugin, 0, 1);

    }

    public void addColoredParticleTrail(Particle particle, float var1, float var2, float var3, Particle.DustOptions dustOptions) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!arrow.isDead()) {
                    Objects.requireNonNull(loc.getWorld()).spawnParticle(particle, arrow.getLocation(), 0, 0.001f, var1, var2, var3, dustOptions);
                } else this.cancel();
            }
        }.runTaskTimer(plugin, 0, 1);

    }

    public Location getLoc() {
        return loc;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public Vector getVector() {
        return vector;
    }

    public int getVar1() {
        return var1;
    }

    public int getVar2() {
        return var2;
    }

    public ProjectileSource getShooter() {
        return shooter;
    }

    public String getArrowName() {
        return arrowName;
    }
}

