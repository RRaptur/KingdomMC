package me.rapturr.wmc.mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class IronRobot extends WesternMob {
    public IronRobot(EntityType entityType, String mobID) {
        super(entityType, mobID);
    }

    @Override
    public void onSpawn(LivingEntity entity) {

    }

    @Override
    public void onHit(Entity entity, Entity damager, EntityDamageByEntityEvent event) {

    }

    @Override
    public void onDamaged(Entity entity, Entity damager, EntityDamageByEntityEvent event) {

    }

    @Override
    public void onDeath(LivingEntity entity, EntityDeathEvent event) {

    }
}
