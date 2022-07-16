package me.rapturr.wmc.mobs;

import me.rapturr.wmc.helpers.EntityUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;


public abstract class WesternMob {

    EntityType entityType;
    String mobID;
    LivingEntity entity;

    public WesternMob(EntityType entityType, String mobID) {
        this.entityType = entityType;
        this.mobID = mobID;


    }

    public void spawnWesternMob(World world, Location loc) {
        assert entityType.getEntityClass() != null;
        entity = (LivingEntity) world.spawn(loc, entityType.getEntityClass());
        EntityUtils.storeStringInEntity(entity, "is-western", "true");
        EntityUtils.storeStringInEntity(entity, "mob-id", mobID);

        this.onSpawn(entity);


    }

    public abstract void onSpawn(LivingEntity entity);

    public abstract void onHit(Entity entity, Entity damager, EntityDamageByEntityEvent event);

    public abstract void onDamaged(Entity entity, Entity damager, EntityDamageByEntityEvent event);

    public abstract void onDeath(LivingEntity entity, EntityDeathEvent event);


}
