package me.rapturr.wmc.items.unfinished;

import com.comphenix.protocol.PacketType;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.ItemstackUtils;
import me.rapturr.wmc.helpers.Utilities;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
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
import org.bukkit.util.EulerAngle;

public class ArmorstandPreviewer extends WesternItem {
    public ArmorstandPreviewer(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    ArmorStand armorStand;

    private int getValue(ItemStack itemStack) {
        return ItemstackUtils.getIntegerFromItemStack(itemStack, "value");

    }

    private void xyz(ItemStack itemStack, Player player) {
        int value = getValue(itemStack);
        switch (value) {
            case 0: //x
                player.sendMessage(ChatColor.GREEN + "X");
                break;
            case 1: //y
                player.sendMessage(ChatColor.GREEN + "Y");
                break;
            case 2: //z
                player.sendMessage(ChatColor.GREEN + "Z");
                break;

        }
    }



    private void setValue(ItemStack itemStack, ArmorStand armorStand, Player player) {
        int value = getValue(itemStack);
        if (armorStand != null) {
            EulerAngle angle = armorStand.getRightArmPose();
            switch (value) {
                case 0: //x
                        armorStand.setRightArmPose(new EulerAngle(angle.getX() + 0.1f, angle.getY(), angle.getZ()));
                        break;
                case 1: //y
                        armorStand.setRightArmPose(new EulerAngle(angle.getX(), angle.getY() + 0.1f, angle.getZ()));
                        break;
                case 2: //z
                        armorStand.setRightArmPose(new EulerAngle(angle.getX(), angle.getY(), angle.getZ() + 0.1f));
                        break;
            }
            Utilities.sendActionBarMessage(player, ChatColor.GREEN + "X: " + angle.getX() + " Y: " + angle.getY() + " Z: " + angle.getZ());
        }


    }

    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        Bukkit.broadcastMessage("CREATED!");
        ItemstackUtils.storeIntegerInItemStack(itemStack, 0, "value");
    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {
        if (player.isSneaking()) {
            setValue(itemStack, armorStand, player);
        }
    }

    @Override
    public void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event) {

    }

    @Override
    public void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event) {

    }

    @Override
    public void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        //Switch value
        int value = getValue(itemStack);
        if (value == 2) {
            ItemstackUtils.storeIntegerInItemStack(itemStack, 0, "value");
        } else ItemstackUtils.storeIntegerInItemStack(itemStack, value + 1, "value");
        xyz(itemStack, player);
    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {
        armorStand = player.getWorld().spawn(block.getLocation().add(0, 1, 0), ArmorStand.class);
        armorStand.setArms(true);
        armorStand.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void rightClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

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
}
