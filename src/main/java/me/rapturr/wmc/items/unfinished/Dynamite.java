package me.rapturr.wmc.items.unfinished;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.ItemRarity;
import me.rapturr.wmc.helpers.ItemType;
import me.rapturr.wmc.helpers.Utilities;
import me.rapturr.wmc.helpers.WesternExplosion;
import me.rapturr.wmc.items.WesternItem;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Candle;
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
import org.bukkit.scheduler.BukkitRunnable;

public class Dynamite extends WesternItem {
    public Dynamite(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    @Override
    public void onitemstackcreate(ItemStack itemStack) {

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
        useDynamite(block);
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

    private void useDynamite(Block block) {
        Block top = block.getRelative(BlockFace.UP);
        top.setType(Material.RED_CANDLE);

        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                i++;
                Candle candle = (Candle) top.getBlockData();
                if (candle.isLit()) {
                    this.cancel();
                    top.getWorld().playSound(top.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            WesternExplosion.createExplosion(top.getLocation());
                            top.setType(Material.AIR);
                        }
                    }.runTaskLater(WMC.getInstance(), Utilities.createRandomInt(3, 6) * 20L);
                }
                if (i >= 120) {
                    this.cancel();
                    top.setType(Material.AIR);
                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, 5);




    }
}
