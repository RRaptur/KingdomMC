package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class WesternExplosion implements Listener {

    WMC plugin;

    public WesternExplosion(WMC plugin) {
        this.plugin = plugin;
    }

    public static void createExplosion(Location loc) {
        TNTPrimed primed = loc.getWorld().spawn(loc, TNTPrimed.class);
        primed.setFuseTicks(0);
        primed.setInvulnerable(true);
        primed.setCustomName("WMC_Explosion");
    }

    @EventHandler
    private void onCustomExplosion(EntityExplodeEvent event) {
        List<Block> crackedBlocks = new ArrayList<>();
        List<Block> blockList = event.blockList();
        event.setYield(0);
        Entity entity = event.getEntity();
        if (entity instanceof TNTPrimed) {
            if (entity.getCustomName().contains("WMC_Explosion")) {
                for (Block b : blockList) {
                    if (b.getType() == Material.CRACKED_STONE_BRICKS) {
                        crackedBlocks.add(b);
                    }
                }
                blockList.clear();
                for (Block b : crackedBlocks) {
                    b.breakNaturally();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            b.setType(Material.CRACKED_STONE_BRICKS);
                        }
                    }.runTaskLater(plugin, 20*10);
                }
            }
        }
    }
}