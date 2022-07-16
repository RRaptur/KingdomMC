package me.rapturr.wmc.inventory;

import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.items.WesternItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class WesternInventory implements InventoryHolder {

    private final String title;
    private final int size;
    Inventory inventory;

    public WesternInventory(String title, int size) {
        this.title = title;
        this.size = size;
        inventory = Bukkit.createInventory(this, size, title);

    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inventory);
    }

    public void setItemStack(ItemStack item, int slot) {
        inventory.setItem(slot, item);
    }

    public ItemStack getItemStack(WesternItems westernItems) {
        return WesternItem.getItemStack(westernItems);
    }

    public static void removeItemStack(Inventory inventory, ItemStack itemStack, int amount) {
        ItemStack[] itemStacks = inventory.getContents();
        for (ItemStack i : itemStacks) {
            if (i != null) {
                if (i.isSimilar(itemStack)) {
                    if (i.getAmount() >= amount) {
                        i.setAmount(i.getAmount() - amount);
                        break;
                    }
                }
            }
        }
    }

    public static boolean hasEnoughItemStack(Inventory inventory, ItemStack itemStack, int amount) {
        ItemStack[] itemStacks = inventory.getContents();
        for (ItemStack i : itemStacks) {
            if (i != null) {
                if (i.isSimilar(itemStack)) {
                    return i.getAmount() >= amount;
                } else return false;
            } else return false;
        }
        return false;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public abstract void onInventoryOpen(Player player, Inventory inventory, InventoryOpenEvent event);

    public abstract void onInventoryClose(Player player, Inventory inventory, InventoryCloseEvent event);

    public abstract void onInventoryDrag(Player player, Inventory inventory, InventoryDragEvent event);

    public abstract void onInventoryClick(Player player, Inventory inventory, InventoryClickEvent event);

    public abstract void onInventoryItemSource(Inventory inventory, InventoryMoveItemEvent event);

    public abstract void onInventoryItemDestination(Inventory inventory, InventoryMoveItemEvent event);
}
