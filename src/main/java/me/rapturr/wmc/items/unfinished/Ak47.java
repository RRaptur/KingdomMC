package me.rapturr.wmc.items.unfinished;

import me.rapturr.wmc.helpers.*;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.projectiles.snowballs.Ak47Bullet;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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

public class Ak47 extends WesternItem {
    public Ak47(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }


    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        ItemstackUtils.storeIntegerInItemStack(itemStack, 32, "bullets");
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
        reloadMechanic.reload(player);
        ;
    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {
        onShoot(player);
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

    Cooldown cooldown = new Cooldown();

    private void onShoot(Player player) {
        int bullets = ItemstackUtils.getIntegerFromItemStack(player.getInventory().getItemInMainHand(), "bullets");
        if (bullets > 0) {
            ItemstackUtils.storeIntegerInItemStack(player.getInventory().getItemInMainHand(), bullets - 1, "bullets");
            Ak47Bullet bullet = new Ak47Bullet("AK_BULLET");
            bullet.spawnSnowball(player.getEyeLocation(), player.getEyeLocation().getDirection().multiply(4f), player);
            bullet.getSnowball().getWorld().playSound(bullet.getSnowball().getLocation(), Sound.ENTITY_ITEM_BREAK, 0.5f, 4f);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + String.valueOf(bullets) + "/32"));


        } else {
            //Reload
            reloadMechanic.reload(player);

        }

    }

    ReloadMechanic reloadMechanic = new ReloadMechanic(2000L, WesternSound.PISTOL_RELOAD, WesternSound.PISTOL_RELOAD, WesternSound.RELOAD_CANCEL, 32);

}
