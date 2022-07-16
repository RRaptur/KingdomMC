package me.rapturr.wmc.inventory.admin;

import me.rapturr.wmc.inventory.WesternInventory;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.items.WesternItems;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KingdomItems extends WesternInventory {
    public KingdomItems(String title, int size) {
        super(title, size);
        setItemStack(getItemStack(WesternItems.TOMAHAWK), 0);
        setItemStack(getItemStack(WesternItems.BONEMERANG), 1);
        setItemStack(getItemStack(WesternItems.SPARK_WAND), 2);
        setItemStack(getItemStack(WesternItems.SCYTHE), 3);
        setItemStack(getItemStack(WesternItems.ANDURIL), 4);
        setItemStack(getItemStack(WesternItems.PERUN), 5);
        setItemStack(getItemStack(WesternItems.BOOK_OF_SKULLS), 6);
        setItemStack(getItemStack(WesternItems.ARTEMIS), 7);
        setItemStack(getItemStack(WesternItems.BRICK), 8);
        setItemStack(getItemStack(WesternItems.CRIMSON_WAND), 9);
        setItemStack(getItemStack(WesternItems.WARDENS_GRACE), 10);
        setItemStack(getItemStack(WesternItems.RHYDIA_WAND), 11);
        setItemStack(getItemStack(WesternItems.DICE), 12);
    }
    @Override
    public void onInventoryOpen(Player player, Inventory inventory, InventoryOpenEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 0.5f);
    }

    @Override
    public void onInventoryClose(Player player, Inventory inventory, InventoryCloseEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1, 0.5f);
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
