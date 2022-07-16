package me.rapturr.wmc.helpers;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WeaponAttributes {

    private final float attackDamage;
    private final float attackSpeed;
    private final float attackKnockback;
    private final float armor;
    private final float maxHealth;
    private final float movementSpeed;
    private final float knockbackResistance;
    private EquipmentSlot equipmentSlot;

    public WeaponAttributes(float attackDamage, float attackSpeed, float attackKnockback, float armor, float maxHealth, float movementSpeed, float knockbackResistance, EquipmentSlot equipmentSlot) {
        this.attackDamage = attackDamage; //No max
        this.attackSpeed = attackSpeed; //No max but 20 ticks per second so max 20CPS so max 20
        this.attackKnockback = attackKnockback; //max == 5.0
        this.armor = armor; //Max == 30
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed; //0.1 == 100% so *1000
        this.knockbackResistance = knockbackResistance; //1.0 == 100% so *100
        this.equipmentSlot = equipmentSlot;

    }

    public List<String> addWeaponInfo() {
        List<String> weaponInfo = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    if (attackDamage > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Attack Damage" + ChatColor.RED + " +" + (int) attackDamage);
                        continue;
                    }
                    if (attackDamage < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Attack Damage" + ChatColor.RED + " " + (int) attackDamage);
                        continue;
                    }

                    else continue;
                case 1:
                    if (attackSpeed > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Attack Speed" + ChatColor.RED + " +" + attackSpeed);
                        continue;
                    }
                    if (attackSpeed < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Attack Speed" + ChatColor.RED + " " + attackSpeed);
                        continue;
                    }

                    else continue;
                case 2:
                    if (attackKnockback > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Attack Knockback" + ChatColor.RED + " +" + attackKnockback);
                        continue;
                    }
                    if (attackKnockback < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Attack Knockback" + ChatColor.RED + " " + attackKnockback);
                        continue;
                    }

                    else continue;
                case 3:
                    if (armor > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Armor" + ChatColor.BLUE + " +" + (int) armor);
                        continue;
                    }
                    if (armor < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Armor" + ChatColor.BLUE + " " + (int) armor);
                        continue;
                    }

                    else continue;
                case 4:
                    if (maxHealth > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Health" + ChatColor.BLUE + " +" + (int) maxHealth);
                        continue;
                    }
                    if (maxHealth < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Health" + ChatColor.BLUE + " " + (int) maxHealth);
                        continue;
                    }

                    else continue;
                case 5:
                    if (movementSpeed > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Movement Speed" + ChatColor.WHITE + " +" + (int) movementSpeed + "%");
                        continue;
                    }
                    if (movementSpeed < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Movement Speed" + ChatColor.WHITE + " " + (int) movementSpeed + "%");
                        continue;
                    }

                    else continue;
                case 6:
                    if (knockbackResistance > 0) {
                        weaponInfo.add(ChatColor.GRAY + "Knockback Resistance" + ChatColor.BLUE + " +" + (int) knockbackResistance + "%");
                        continue;
                    }
                    if (knockbackResistance < 0) {
                        weaponInfo.add(ChatColor.GRAY + "Knockback Resistance" + ChatColor.BLUE + " " + (int) knockbackResistance + "%");
                        continue;
                    }

                    else continue;
            }
        }
        return weaponInfo;
    }

    public void addAttributes(ItemStack itemStack, boolean isRanged) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;

        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, Utilities.addAttribute("GENERIC_ATTACK_SPEED", attackSpeed, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));

        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, Utilities.addAttribute("GENERIC_ARMOR", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, Utilities.addAttribute("GENERIC_MAX_HEALTH", maxHealth, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, Utilities.addAttribute("GENERIC_MOVEMENT_SPEED", movementSpeed / 1000, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, Utilities.addAttribute("GENERIC_KNOCKBACK_RESISTANCE", knockbackResistance / 100, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));

        if (!isRanged) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, Utilities.addAttribute("GENERIC_ATTACK_DAMAGE", attackDamage, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, Utilities.addAttribute("GENERIC_ATTACK_KNOCKBACK", attackKnockback, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        } else {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, Utilities.addAttribute("GENERIC_ATTACK_DAMAGE", 0f, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, Utilities.addAttribute("GENERIC_ATTACK_KNOCKBACK", 0f, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        }

        itemStack.setItemMeta(itemMeta);
    }


    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getAttackKnockback() {
        return attackKnockback;
    }

    public float getArmor() {
        return armor;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
