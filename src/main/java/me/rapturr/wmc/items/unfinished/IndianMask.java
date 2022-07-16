package me.rapturr.wmc.items.unfinished;

import me.rapturr.wmc.helpers.CustomGameProfile;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Material;
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

import java.util.UUID;

public class IndianMask extends WesternItem {
    public IndianMask(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        itemStack.setItemMeta(new CustomGameProfile(UUID.randomUUID(), "INDIAN_MASK", new String[]{
                "ewogICJ0aW1lc3RhbXAiIDogMTYyODM1MTQ5MzI1OSwKICAicHJvZmlsZUlkIiA6ICIwYTUzMDU0MTM4YWI0YjIyOTVhMGNlZmJiMGU4MmFkYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQX0hpc2lybyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hZWI1NjQ0NDMyNmI1ZjQ2MTQ0OTAzOTc1MmYyNjIyN2EyNGIwNjc5YWMwNzc4YWM2OTBhNTZmOTZhMGMyMWNhIgogICAgfQogIH0KfQ==",
        "Me2um1Og9ZY/POGc8JTFzaF5JT+D7uceNuaTuQLdI2P2pDfsxdY7e6ktlQJQiZO4gatC1Nk6DloFf5Whb7mh8icwfHDKA17xNWfnV0wJQ+fl7nplSh+Ac60O+EFg+RMPLtouyOalsNIw+1mGYAu95Ao49Xejo5yFA1/ObJKhskz7gdkWqPbKMXG3mT5GuGDsqFBlZfBYWCO8gfdsey034xHxb6zFyXI5ZD3BMcBDQpq4O87w9BIayBYL4QKvn3vkusyj19mzfZq7ocvgPoL6L8V3QO+LHRsWekdG3jBizvoNdqY/e6DzZ4YDclPXNdeu3PehZnAfFefohwES8pVlToqAPQob/IZwE05OGgk2KDiEi7SsksipaMw6VhxlwBFd6hxXxvoE69Tp7HzsEc/B8EiZdYtKwjcZJXBRQWilubxWQtJZnmTh/GBGBOwfiTZxDuxn2f7L3ZrKGlXcVaHn6bT5wgjjPr12GnshQILphebrvWY2NM9dif/eGJCCf6AiwrCZiiWhTIPd4utPP2LqmaJL3WxKBXl/rl9YFL5CXInbNkDJD3qMWIHGL1kbt6u/wBYJqanvqNS3reG9nZxmPFUB/ml6GF3u+jyA1RWqDC2p+1fWec2v2qJtVZCfer5XQvmR2H4jKtx2MIApL24tRC8NtCrjihA5VIsCvp4TFMA="}).getCustomSkullMeta(itemStack));
    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {

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
