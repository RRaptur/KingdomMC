package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemstackUtils {

    private static NamespacedKey createNamespacedkey(String key) {
        return new NamespacedKey(WMC.getInstance(), key);
    }

    public static void storeStringInItemStack(ItemStack item, String string, String key) {
        if (item != null) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.getPersistentDataContainer().set(createNamespacedkey(key), PersistentDataType.STRING, string);
                item.setItemMeta(meta);
            }
        }
    }

    public static void storeIntegerInItemStack(ItemStack item, Integer integer, String key) {
        if (item != null) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                meta.getPersistentDataContainer().set(createNamespacedkey(key), PersistentDataType.INTEGER, integer);
                item.setItemMeta(meta);
            }
        }
    }

    public static boolean getBooleanFromItem(ItemStack itemStack, String key) {
        return Boolean.getBoolean(getStringFromItemStack(itemStack, key));
    }

    public static String getStringFromItemStack(ItemStack itemStack, String key) {
        return (String) getObjectFromItemStack(itemStack, PersistentDataType.STRING, key);
    }

    public static Float getFloatFromItemStack(ItemStack itemStack, String key) {
        return (Float) getObjectFromItemStack(itemStack, PersistentDataType.FLOAT, key);
    }

    public static Integer getIntegerFromItemStack(ItemStack itemStack, String key) {
        return (Integer) getObjectFromItemStack(itemStack, PersistentDataType.INTEGER, key);
    }

    public static Object getObjectFromItemStack(ItemStack itemStack, PersistentDataType<?, ?> persistentDataType, String key) {
        if (itemStack == null) {
            return null;
        }else if (!itemStack.hasItemMeta()) {
            return null;
        } else {
            ItemMeta itemMeta = itemStack.getItemMeta();
            assert itemMeta != null;
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.get(createNamespacedkey(key), persistentDataType);
        }
    }
}
