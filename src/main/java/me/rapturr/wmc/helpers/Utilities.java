package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


public class Utilities {

    public static Vector rotateVector(Vector vector, double whatAngle) {
        double sin = Math.sin(whatAngle);
        double cos = Math.cos(whatAngle);
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;


        return vector.setX(x).setZ(z);
    }

    public static ItemStack getWesternItemStack(String itemID) {
        return WMC.westernItems.get(itemID).createItemStack(itemID);
    }

    public static void giveWesternWeapon(Player player, String itemID) {
        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(getWesternItemStack(itemID));
        } else {
            player.getWorld().dropItemNaturally(player.getLocation(), getWesternItemStack(itemID));
        }
    }

    public static int createRandomInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static AttributeModifier addAttribute(String name, Float amount, AttributeModifier.Operation operation, EquipmentSlot eS) {

        return new AttributeModifier(UUID.randomUUID(), name, amount, operation, eS);
    }

    public static void sendActionBarMessage(Player player, String string) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(string));
    }
}
