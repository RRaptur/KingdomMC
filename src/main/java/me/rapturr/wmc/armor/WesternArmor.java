package me.rapturr.wmc.armor;

import me.rapturr.wmc.helpers.ItemRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class WesternArmor {

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

    String displayName;
    String lore;
    Material material;
    boolean isShiny;
    ItemRarity itemRarity;

    public WesternArmor(String displayName, String lore, Material material, boolean isShiny, ItemRarity itemRarity) {
        this.displayName = displayName;
        this.lore = lore;
        this.material = material;
        this.isShiny = isShiny;
        this.itemRarity = itemRarity;

    }

    public abstract void onArmorCreate(ItemStack itemStack);

    public abstract void activeEffect(Player player, ItemStack itemStack);

    public ItemStack createWesternArmor() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(itemRarity.getColor() + displayName);
        meta.addItemFlags(ItemFlag.HIDE_DYE, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
        meta.setUnbreakable(true);

        if (isShiny)
            meta.addEnchant(Enchantment.LUCK, 1, true);

        //call action
        onArmorCreate(itemStack);

        //return here
        return itemStack;
    }


}
