package me.rapturr.wmc.inventory.admin;

import me.rapturr.wmc.inventory.WesternInventory;
import me.rapturr.wmc.items.materials.Materials;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WesternMaterialInventory extends WesternInventory {
    public WesternMaterialInventory(String title, int size) {
        super(title, size);


        setItemStack(Materials.GOLD.getItem(), 0);
        setItemStack(Materials.RAW_GOLD.getItem(), 1);
        setItemStack(Materials.GOLD_NUGGET.getItem(), 2);
        setItemStack(Materials.IRON.getItem(), 3);
        setItemStack(Materials.RAW_IRON.getItem(), 4);
        setItemStack(Materials.SILVER.getItem(), 5);
        setItemStack(Materials.STRANGE_CRYSTAL.getItem(), 6);
        setItemStack(Materials.BRONZE.getItem(), 7);
        setItemStack(Materials.AGED_BRONZE_PLATING.getItem(), 8);
        setItemStack(Materials.CACTUS.getItem(), 9);
        setItemStack(Materials.FEATHERS.getItem(), 10);
        setItemStack(Materials.RARE_FEATHER.getItem(), 11);
        setItemStack(Materials.ANIMAL_PAW.getItem(), 12);
        setItemStack(Materials.COD_LIVER_OIL.getItem(), 13);
        setItemStack(Materials.GARLIC.getItem(), 14);
        setItemStack(Materials.GUNPOWDER.getItem(), 15);
        setItemStack(Materials.QUARTZ.getItem(), 16);
        setItemStack(Materials.HONEY_COMB.getItem(), 17);
        setItemStack(Materials.SUGAR.getItem(), 18);
        setItemStack(Materials.SUGAR_CANE.getItem(), 19);
        setItemStack(Materials.WHEAT.getItem(), 20);
        setItemStack(Materials.CARROT.getItem(), 21);
        setItemStack(Materials.EGG.getItem(), 22);
        setItemStack(Materials.STRANGE_EGG.getItem(), 23);
        setItemStack(Materials.BONE.getItem(), 24);
        setItemStack(Materials.FOSSIL.getItem(), 25);
        setItemStack(Materials.CANDLE.getItem(), 26);
        setItemStack(Materials.PICKLE.getItem(), 27);
        setItemStack(Materials.ANIMAL_FAT.getItem(), 28);
        setItemStack(Materials.STRING.getItem(), 29);
        setItemStack(Materials.LORE_BOOK.getItem(), 30);
    }

    @Override
    public void onInventoryOpen(Player player, Inventory inventory, InventoryOpenEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_BARREL_OPEN, 1, 1);

    }

    @Override
    public void onInventoryClose(Player player, Inventory inventory, InventoryCloseEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_BARREL_CLOSE, 1, 1);

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
            player.getInventory().addItem(itemStack);
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
