package me.rapturr.wmc.helpers;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class AttributeHelpers {

    /*
     *  ALL THESE VALUES ARE HANDLED IN DOUBLES!
     *
     *  MAX_HEALTH - 1 == 0.5 HEART
     *  KNOCKBACK_RESISTANCE - 0.4 == YOU GET 40% LESS KNOCKBACK
     *  MOVEMENT_SPEED - 0.1 == 100% SPEED
     *  ATTACK_DAMAGE - NO EXPL
     *  ARMOR - MAX 20
     *  ARMOR_TOUGHNESS - PROPS NOT
     *  ATTACK_KNOCKBACK - MAX 5.0
     *  ATTACK_SPEED - MAX 20?
     *
     *
     *
     *
     * STORED TAGS:
     *
     * Armor type - Helmet, Chestplate, Leggings, Boots
     * isArmor
     *
     *
     *
     * */

    public static double getAttribute(Player player, Attribute attribute) {

        return Objects.requireNonNull(player.getAttribute(attribute)).getValue();
    }


    public static AttributeModifier createAttributeModifier(Attribute attribute, double value, EquipmentSlot equipmentSlot) {
        return new AttributeModifier(UUID.randomUUID(), attribute.name(), value, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
    }

    public static void addAttribute(ItemStack itemStack, Attribute attribute, double value, EquipmentSlot equipmentSlot) {
        if (itemStack.hasItemMeta())
            return;
        else {
            Objects.requireNonNull(itemStack.getItemMeta()).addAttributeModifier(attribute, createAttributeModifier(attribute, value, equipmentSlot));
        }
    }





}
