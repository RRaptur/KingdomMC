package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.mobs.WesternMob;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

public class EntityUtils {

    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(WMC.getInstance(), key);
    }

    public static void storeStringInEntity(Entity entity, String key, String string) {
        entity.getPersistentDataContainer().set(createNamespacedKey(key), PersistentDataType.STRING, string);
    }

    public static void storeIntegerInEntity(Entity entity, String key, Integer integer) {
        entity.getPersistentDataContainer().set(createNamespacedKey(key), PersistentDataType.INTEGER, integer);
    }

    public static String getStringFromEntity(Entity entity, String key) {
        return (String) getObjectFromEntity(entity, PersistentDataType.STRING, key);
    }


    public static Integer getIntegerFromEntity(Entity entity, String key) {
        return (Integer) getObjectFromEntity(entity, PersistentDataType.INTEGER, key);
    }


    public static Object getObjectFromEntity(Entity entity, PersistentDataType<?, ?> persistentDataType, String key) {
        return entity.getPersistentDataContainer().get(createNamespacedKey(key), persistentDataType);
    }

    public static boolean isWesternEntity(Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return true;
        }
        return getStringFromEntity(entity, "is-western") == null;
    }

    public static WesternMob getWesternMob(Entity entity) {
        return isWesternEntity(entity) ? null : WMC.westernMobs.get(getStringFromEntity(entity, "mob-id"));
    }


}
