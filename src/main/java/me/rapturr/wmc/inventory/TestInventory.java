package me.rapturr.wmc.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;

public class TestInventory extends WesternInventory {
    public TestInventory(String title, int size) {
        super(title, size);

    }

    @Override
    public void onInventoryOpen(Player player, Inventory inventory, InventoryOpenEvent event) {
       player.sendMessage("OPEN");

    }

    @Override
    public void onInventoryClose(Player player, Inventory inventory, InventoryCloseEvent event) {
        player.sendMessage("CLOSE");


    }

    @Override
    public void onInventoryDrag(Player player, Inventory inventory, InventoryDragEvent event) {
        player.sendMessage("DRAG");


    }

    @Override
    public void onInventoryClick(Player player, Inventory inventory, InventoryClickEvent event) {
        player.sendMessage("CLICK");


    }

    @Override
    public void onInventoryItemSource(Inventory inventory, InventoryMoveItemEvent event) {

    }

    @Override
    public void onInventoryItemDestination(Inventory inventory, InventoryMoveItemEvent event) {

    }
}
