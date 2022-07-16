package me.rapturr.wmc.listeners;

import me.rapturr.wmc.helpers.EntityUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class MobListeners implements Listener {

    @EventHandler
    private void onMobHit(EntityDamageByEntityEvent event) {
        Entity target = event.getEntity();
        Entity damager = event.getDamager();

        if (EntityUtils.isWesternEntity(target)) {
            return;
        } else {
            Objects.requireNonNull(EntityUtils.getWesternMob(target)).onDamaged(target, damager, event);
        }

        if (EntityUtils.isWesternEntity(damager)) {
            return;
        } else {
            Objects.requireNonNull(EntityUtils.getWesternMob(damager)).onHit(target, damager, event);
        }
    }

    @EventHandler
    private void onMobDeath(EntityDeathEvent event) {
        if (EntityUtils.isWesternEntity(event.getEntity())) {
            return;
        } else {
            Objects.requireNonNull(EntityUtils.getWesternMob(event.getEntity())).onDeath(event.getEntity(), event);
        }
    }
}
