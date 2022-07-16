package me.rapturr.wmc.inventory.stations;

import me.rapturr.wmc.inventory.WesternInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;

public class CraftingInventory extends WesternInventory {
    public CraftingInventory(String title, int size) {
        super(title, size);
    }

    @Override
    public void onInventoryOpen(Player player, Inventory inventory, InventoryOpenEvent event) {

    }

    @Override
    public void onInventoryClose(Player player, Inventory inventory, InventoryCloseEvent event) {

    }

    @Override
    public void onInventoryDrag(Player player, Inventory inventory, InventoryDragEvent event) {

    }

    @Override
    public void onInventoryClick(Player player, Inventory inventory, InventoryClickEvent event) {

    }

    @Override
    public void onInventoryItemSource(Inventory inventory, InventoryMoveItemEvent event) {

    }

    @Override
    public void onInventoryItemDestination(Inventory inventory, InventoryMoveItemEvent event) {

    }

    private void checkRecipe(Inventory inventory) {

    }
}
