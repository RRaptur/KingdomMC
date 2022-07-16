package me.rapturr.wmc.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WingSuitListener implements Listener {

    @EventHandler
    private void onFly(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isGliding() && player.isSneaking() && player.getLocation().getPitch() <= -45) {
            player.setVelocity(player.getEyeLocation().getDirection().multiply(1.5f));
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 4);
        }
    }
}
