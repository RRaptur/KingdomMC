package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class ReloadMechanic {

    private final Cooldown cooldown = new Cooldown();
    private final Long reloadTime;
    private final WesternSound onReload;
    private final WesternSound onComplete;
    private final WesternSound onCancel;
    private final int bullets;


    public ReloadMechanic(Long reloadTime, WesternSound onReload, WesternSound onComplete, WesternSound onCancel, int bullets) {
        this.reloadTime = reloadTime;
        this.onReload = onReload;
        this.onComplete = onComplete;
        this.onCancel = onCancel;
        this.bullets = bullets;
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    public void reload(Player player) {
        final PlayerInventory inventory = player.getInventory();
        ItemStack oldItemStack = inventory.getItemInMainHand();
        int oldSlot = inventory.getHeldItemSlot();

        //reload
        cooldown.setCooldown(player, reloadTime);
        onReload.playPlayerSound(player, player.getLocation());
        Utilities.sendActionBarMessage(player, ChatColor.GRAY + "Reloading...");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (oldSlot != inventory.getHeldItemSlot()) {
                    Utilities.sendActionBarMessage(player, ChatColor.GRAY + "Stopped Reloading!");
                    onCancel.playPlayerSound(player, player.getLocation());
                    this.cancel();
                } else {
                    if (!cooldown.hasCooldownTimeLeft(player)) {
                        ItemstackUtils.storeIntegerInItemStack(oldItemStack, bullets, "bullets");
                        Utilities.sendActionBarMessage(player, ChatColor.GRAY + "Reloaded!");
                        onComplete.playPlayerSound(player, player.getLocation());
                        this.cancel();
                    }

                }
            }
        }.runTaskTimer(WMC.getInstance(), 0, 0);
    }


    public Long getReloadTime() {
        return reloadTime;
    }

    public WesternSound getOnReload() {
        return onReload;
    }

    public WesternSound getOnComplete() {
        return onComplete;
    }

    public WesternSound getOnCancel() {
        return onCancel;
    }

    public int getBullets() {
        return bullets;
    }
}
