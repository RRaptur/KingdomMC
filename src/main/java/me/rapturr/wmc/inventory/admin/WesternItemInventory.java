package me.rapturr.wmc.inventory.admin;

import me.rapturr.wmc.inventory.WesternInventory;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.items.WesternItems;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WesternItemInventory extends WesternInventory {
    public WesternItemInventory(String title, int size) {
        super(title, size);

        setItemStack(getItemStack(WesternItems.AK47), 0);
        setItemStack(getItemStack(WesternItems.BONEMERANG), 1);
        setItemStack(getItemStack(WesternItems.WHIP), 2);
        setItemStack(getItemStack(WesternItems.TOMAHAWK), 3);
        setItemStack(getItemStack(WesternItems.SHORT_BOW), 4);
        setItemStack(getItemStack(WesternItems.GRAPPLING_HOOK), 5);
        setItemStack(getItemStack(WesternItems.CRACKED_BOTTLE), 6);
        setItemStack(getItemStack(WesternItems.INDIAN_MASK), 7);
        setItemStack(getItemStack(WesternItems.SPACE_HELMET), 8);
        setItemStack(getItemStack(WesternItems.PICKLE_JAR), 9);
        setItemStack(getItemStack(WesternItems.SILK_LASSO), 10);
        setItemStack(getItemStack(WesternItems.REVOLVER), 11);
        setItemStack(getItemStack(WesternItems.MUSKET), 12);
        setItemStack(getItemStack(WesternItems.HORN), 13);
        setItemStack(getItemStack(WesternItems.TEST_ITEM), 14);
        setItemStack(getItemStack(WesternItems.WATER_SKIN), 15);
        setItemStack(getItemStack(WesternItems.SPEAR), 16);
        setItemStack(getItemStack(WesternItems.DYNAMITE), 17);
        setItemStack(getItemStack(WesternItems.RAY_GUN), 18);
        setItemStack(getItemStack(WesternItems.ARMORSTAND_EDITOR), 19);
    }

    @Override
    public void onInventoryOpen(Player player, Inventory inventory, InventoryOpenEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 0.5f);
    }

    @Override
    public void onInventoryClose(Player player, Inventory inventory, InventoryCloseEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 0.5f);
    }

    @Override
    public void onInventoryDrag(Player player, Inventory inventory, InventoryDragEvent event) {
        event.setCancelled(true);

    }

    @Override
    public void onInventoryClick(Player player, Inventory inventory, InventoryClickEvent event) {
        if (inventory != player.getInventory()) {
            event.setCancelled(true);
            ItemStack itemStack = event.getCurrentItem();
            WesternItem item = WesternItem.getWesternItem(itemStack);
            player.getInventory().addItem(item.createItemStack(item.getItemID()));
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
        }
    }

    @Override
    public void onInventoryItemSource(Inventory inventory, InventoryMoveItemEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void onInventoryItemDestination(Inventory inventory, InventoryMoveItemEvent event) {
        event.setCancelled(true);
    }
}
