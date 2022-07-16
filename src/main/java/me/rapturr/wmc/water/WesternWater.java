package me.rapturr.wmc.water;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.helpers.EntityUtils;
import me.rapturr.wmc.helpers.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WesternWater {


   public static boolean hasWater(Player player) {
        return EntityUtils.getIntegerFromEntity(player, "water") != null;
   }

   public static void setWater(Player player, int amount) {
       EntityUtils.storeIntegerInEntity(player, "water", amount);
   }

   public static int getWater(Player player) {
        return EntityUtils.getIntegerFromEntity(player, "water");
   }

   public static void addWater(Player player, int amount) {
       int sum = getWater(player) + amount;
       setWater(player, Math.min(sum, 100));
   }


    public static void removeWater(Player player, int amount) {
        int sum = getWater(player) - amount;
        setWater(player, Math.max(sum, 0));
    }

   public WesternWater() {
        createTask();
   }


   private void createTask() {
       new BukkitRunnable() {
           @Override
           public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPlayedBefore() || !hasWater(player)) {
                        setWater(player, 100);
                    }
                    if (player.getLevel() != getWater(player)) {
                        player.setExp(0f);
                        player.setLevel(getWater(player));
                    }
                    if (Utilities.createRandomInt(0, 100) <= 8) {
                        if (player.isSprinting()) {
                            removeWater(player, 4);
                        } else removeWater(player, 2);
                    }
                    if (getWater(player) <= 10) {
                        if (Utilities.createRandomInt(0, 100) < 33) {
                            player.damage(1);
                        }
                    }
                }
           }
       }.runTaskTimer(WMC.getInstance(), 10, 10);
   }
}
