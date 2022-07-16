package me.rapturr.wmc.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {

    private final Map<UUID, Long> cooldown = new HashMap<>();

    public long getCooldown(Player player) {
        return this.cooldown.get(player.getUniqueId());
    }

    public boolean hasCooldownTimeLeft(Player player) {
        if (cooldown.containsKey(player.getUniqueId())) {
            if (getCooldown(player) > System.currentTimeMillis()) {
                return true;
            } else return false;
        } else return false;
    }

    public void setCooldown(Player player, Long amount) {
        cooldown.put(player.getUniqueId(), amount + System.currentTimeMillis());
    }

    public String getCooldownMessage(Player player) {
        int cooldownInt = (int) ((getCooldown(player) - System.currentTimeMillis()) / 1000);
        String weaponName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        return weaponName + ChatColor.RED + " is on cooldown for " + cooldownInt + "s!";
    }
}
