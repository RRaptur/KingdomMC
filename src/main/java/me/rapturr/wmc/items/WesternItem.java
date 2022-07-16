package me.rapturr.wmc.items;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.ItemstackUtils;
import me.rapturr.wmc.helpers.WeaponAttributes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public abstract class WesternItem {

    private final String displayName;
    private final String itemID;
    private final boolean isStackable;
    private final boolean isShiny;
    private final Material mat;
    private final String desc;
    private final ItemType type;
    private final ItemRarity rarity;
    private final boolean hasActiveEffect;

    public WesternItem(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        this.displayName = displayName;
        this.itemID = itemID;
        this.isStackable = isStackable;
        this.isShiny = isShiny;
        this.mat = mat;
        this.desc = desc;
        this.type = type;
        this.rarity = rarity;
        this.hasActiveEffect = hasActiveEffect;

    }

    public void enforceStackability(ItemStack item) {
        if (!isStackable) {
            ItemstackUtils.storeStringInItemStack(item, UUID.randomUUID().toString(), "stackable");
        }
    }

    public void enforceActiveEffect(ItemStack itemStack) {
        if (hasActiveEffect) {
            ItemstackUtils.storeStringInItemStack(itemStack, "true", "active-effect");
        }
    }

    public void setItemID(ItemStack item) {
        ItemstackUtils.storeStringInItemStack(item, itemID, "item-id");
    }

    public List<String> createLore() {

        List<String> lore = new ArrayList<>();
        Collection<String> descCollection = Arrays.asList(desc.split("\n"));
        Iterator<String> iterator = descCollection.iterator();
        lore.add(ChatColor.DARK_GRAY + type.getString());
        lore.add("");
        while (iterator.hasNext()) {
            String item = iterator.next();
            item = ChatColor.GRAY + item;
            lore.add(item);
        }
        return lore;

    }

    public ItemStack createItemStack(String itemID) {
        //Get westernItem from hashMap.
        Bukkit.getConsoleSender().sendMessage(UUID.randomUUID().toString());

        WesternItem item = WMC.westernItems.get(itemID);

        //Create itemstack and meta from WesternItem
        ItemStack newItemStack = new ItemStack(item.getMat());
        ItemMeta meta = newItemStack.getItemMeta();
        assert meta != null;
        meta.setUnbreakable(true);
        //Customise item here:
        if (isShiny) {
            meta.addEnchant(Enchantment.LUCK, 1, true);
        }


        meta.setDisplayName(rarity.getColor() + item.getDisplayName());
        //Hide unnecessary tags:
        meta.addItemFlags(ItemFlag.HIDE_DYE, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(createLore());

        //update item:
        newItemStack.setItemMeta(meta);

        //add other tags:
        item.enforceStackability(newItemStack);
        item.enforceActiveEffect(newItemStack);
        setWesternItem(newItemStack);
        item.setItemID(newItemStack);


        item.onitemstackcreate(newItemStack);



        //stats

        return newItemStack;

    }

    public void changeMaterial(ItemStack item, Material mat) {
        if (item != null) {
            item.setType(mat);
        }
    }

    public void addWeaponAttributes(ItemStack itemStack, WeaponAttributes attributes, boolean isRanged) {
        attributes.addAttributes(itemStack, isRanged);
    }

    public Material getMat() {
        return mat;
    }

    public String getItemID() {
        return itemID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean hasActiveEffect() {
        return hasActiveEffect;
    }

    public abstract void onitemstackcreate(ItemStack itemStack);

    public abstract void activeEffect(Player player, ItemStack itemStack);

    public abstract void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event); //working on

    public abstract void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event);

    public abstract void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event);

    public abstract void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block);

    public abstract void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event);

    public abstract void rightClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block);

    public abstract void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item);

    public abstract void hitByEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item);

    public abstract void bowShootAction(Player player, EntityShootBowEvent event, ItemStack itemStack);

    public abstract void onRodAction(Player player, ItemStack itemStack, PlayerFishEvent event);

    public static boolean isWesternItem(ItemStack item) {
        return ItemstackUtils.getStringFromItemStack(item, "is-western") != null;
    }

    public static void setWesternItem(ItemStack item) {
        ItemstackUtils.storeStringInItemStack(item, "true", "is-western");
    }

    public static WesternItem getWesternItem(ItemStack item) {
        return !isWesternItem(item) ? null : WMC.westernItems.get(ItemstackUtils.getStringFromItemStack(item, "item-id"));
    }

    public static ItemStack getItemStack(WesternItems westernItems) {
        String itemId = westernItems.toString();
        return WMC.westernItems.get(itemId).createItemStack(itemId);
    }
}
