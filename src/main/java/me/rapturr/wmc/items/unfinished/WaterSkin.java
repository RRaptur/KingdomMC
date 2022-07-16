package me.rapturr.wmc.items.unfinished;

import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.ItemstackUtils;
import me.rapturr.wmc.helpers.Utilities;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.water.WesternWater;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;

public class WaterSkin extends WesternItem {
    public WaterSkin(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        setAmount(itemStack, 0);
    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {
        Utilities.sendActionBarMessage(player, ChatColor.BLUE + "Water " + ItemstackUtils.getIntegerFromItemStack(itemStack, "water") + "/64");
    }

    @Override
    public void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event) {

    }

    @Override
    public void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event) {

    }

    @Override
    public void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        if (player.isInWater()) {
            refill(player, itemStack);
        } else use(player, itemStack);
    }

    @Override
    public void rightClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {
        if (player.isInWater()) {
            refill(player, itemStack);
        } else use(player, itemStack);
    }

    @Override
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {

    }
    @Override
    public void hitByEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {

    }
    @Override
    public void bowShootAction(Player player, EntityShootBowEvent event, ItemStack itemStack) {

    }

    @Override
    public void onRodAction(Player player, ItemStack itemStack, PlayerFishEvent event) {

    }

    private void setAmountVisually(ItemStack itemStack, int amount) {
        BundleMeta bundleMeta = (BundleMeta) itemStack.getItemMeta();
        assert bundleMeta != null;
        if (bundleMeta.hasItems()) {
            bundleMeta.setItems(null);
        }
        if (amount > 64) {
            bundleMeta.addItem(new ItemStack(Material.DIRT, 64));
        }
        if (amount > 0) {
            bundleMeta.addItem(new ItemStack(Material.DIRT, amount));
        }
        itemStack.setItemMeta(bundleMeta);
    }

    private void setAmount(ItemStack itemStack, int amount) {
        if (amount > 64) {
            ItemstackUtils.storeIntegerInItemStack(itemStack, 64, "water");
            //setAmountVisually(itemStack, 64);
        }
        if (amount < 0) {
            ItemstackUtils.storeIntegerInItemStack(itemStack, 0, "water");

            //setAmountVisually(itemStack, 0);

        }
        else {
            ItemstackUtils.storeIntegerInItemStack(itemStack, amount, "water");
            //setAmountVisually(itemStack, amount);
        }
    }

    private void use(Player player, ItemStack itemStack) {
        if (WesternWater.getWater(player) != 100 && ItemstackUtils.getIntegerFromItemStack(itemStack, "water") > 0) {
            int sum = WesternWater.getWater(player) + ItemstackUtils.getIntegerFromItemStack(itemStack, "water");
            player.sendMessage(String.valueOf(sum));
            if (sum > 100) {
                WesternWater.addWater(player, 100);
                setAmount(itemStack, sum - 100);
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
            } else {
                WesternWater.addWater(player, sum);
                setAmount(itemStack, 0);
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
            }
        }
    }

    private void refill(Player player, ItemStack itemStack) {
        setAmount(itemStack, 64);
        player.playSound(player.getLocation(), Sound.ITEM_BUCKET_FILL, 1, 1);
    }




}
