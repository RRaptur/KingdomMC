package me.rapturr.wmc.listeners;

import me.rapturr.wmc.inventory.WesternInventory;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListeners implements Listener {

    private boolean isWesternInventory(Inventory inventory) {
        return (inventory.getHolder() instanceof WesternInventory);
    }

    private WesternInventory getWesternInventory(Inventory inventory) {
        return (WesternInventory) inventory.getHolder();
    }


    @EventHandler
    private void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getPlayer();
        if (isWesternInventory(inventory)) {
            getWesternInventory(inventory).onInventoryOpen(player, inventory, event);
        }
        if (inventory.getType() == InventoryType.SMITHING && !player.hasPermission("wmc.smithing")) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 1, 1);
            player.sendMessage(ChatColor.RED + "A magic spell has broken the smithing table!");
            event.setCancelled(true);
        }





    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getPlayer();

        if (isWesternInventory(inventory)) {
            getWesternInventory(inventory).onInventoryClose(player, inventory, event);
        }



    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();

       if (isWesternInventory(inventory) && event.getCurrentItem() != null) {
           getWesternInventory(inventory).onInventoryClick(player, inventory, event);
       }
    }

    @EventHandler
    private void onInventoryDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();

        if (isWesternInventory(inventory)) {
            getWesternInventory(inventory).onInventoryDrag(player, inventory, event);
        }
    }

    @EventHandler
    private void onInventoryMove(InventoryMoveItemEvent event) {
        Inventory source = event.getSource();
        Inventory destination = event.getDestination();

        if (isWesternInventory(source)) {
            getWesternInventory(source).onInventoryItemSource(source, event);
        }

        if (isWesternInventory(destination)) {
            getWesternInventory(destination).onInventoryItemDestination(destination, event);
        }
    }


    @EventHandler
    private void onArmorStandInteract(PlayerArmorStandManipulateEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission("wmc.armorstand"))
            return;
        else {
            event.setCancelled(true);
            player.updateInventory();
        }
    }
}
