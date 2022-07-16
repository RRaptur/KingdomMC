package me.rapturr.wmc.commands;

import me.rapturr.wmc.WMC;
import me.rapturr.wmc.bot.NPC;
import me.rapturr.wmc.helpers.EntityUtils;
import me.rapturr.wmc.inventory.TestInventory;
import me.rapturr.wmc.inventory.admin.KingdomItems;
import me.rapturr.wmc.inventory.admin.WesternItemInventory;
import me.rapturr.wmc.inventory.admin.WesternMaterialInventory;
import me.rapturr.wmc.inventory.stations.CraftingInventory;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.Objects;

public class WMCCommand implements CommandExecutor {

    WMC plugin;

    boolean isCrawling;

    public WMCCommand(WMC plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players may use that command!");
            return true;
        }
        if (label.equalsIgnoreCase("wmc")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You may not run this command!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Arguments: materials, items, walker");
                return true;
            }
            if (args.length > 0) {
                //subcommands
                if (args[0].equalsIgnoreCase("materials")) {
                    new WesternMaterialInventory("Western Materials", 54).openInventory(player);
                    player.sendMessage(ChatColor.GRAY + "Opening gui...");
                    return true;
                }
                if (args[0].equalsIgnoreCase("items")) {
                    new WesternItemInventory("Western Items", 54).openInventory(player);
                    player.sendMessage(ChatColor.GRAY + "Opening gui...");
                    return true;
                }
                if (args[0].equalsIgnoreCase("crafting")) {
                    new CraftingInventory("Craft Items", 45).openInventory(player);
                    player.sendMessage(ChatColor.GRAY + "Opening crafting menu...");
                    return true;
                }
                if (args[0].equalsIgnoreCase("kingdom")) {
                    new KingdomItems("Kingdom Items", 54).openInventory(player);
                    player.sendMessage(ChatColor.GRAY + "Opening gui...");
                    return true;
                }
                if (args[0].equalsIgnoreCase("test")) {
                    new TestInventory("Title", 54).openInventory(player);
                    player.sendMessage(ChatColor.GRAY + "Opening gui...");
                    return true;
                }
                if (args[0].equalsIgnoreCase("npc")) {
                    NPC npc = new NPC("Rapturr", player.getLocation(), player);
                    return true;
                }
                if (args[0].equalsIgnoreCase("doublejump")) {
                    if (Objects.equals(EntityUtils.getStringFromEntity(player, "has-double-jump"), "true")) {
                        EntityUtils.storeStringInEntity(player, "has-double-jump", "false");
                        EntityUtils.storeStringInEntity(player, "double-jump", "false");
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.RED + "Double jump disabled!");
                    }
                    //(Objects.equals(EntityUtils.getStringFromEntity(player, "has-double-jump"), "false"))
                    else {
                        EntityUtils.storeStringInEntity(player, "has-double-jump", "true");
                        EntityUtils.storeStringInEntity(player, "double-jump", "true");
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.GREEN + "Double jump enabled!");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("giant")) {
                    World world = player.getWorld();
                    Location loc = player.getLocation();
                    Giant giant = world.spawn(loc, Giant.class);
                    Husk brain = world.spawn(loc, Husk.class);
                    brain.setInvisible(true);
                    brain.setInvulnerable(true);
                    giant.addPassenger(brain);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (giant.isDead()) {
                                brain.remove();
                                this.cancel();
                            } else {
                                for(Entity e : giant.getNearbyEntities(5, 5, 5)) {
                                    if (e instanceof LivingEntity) {
                                        ((LivingEntity) e).damage(2);
                                    }
                                }
                            }
                        }
                    }.runTaskTimer(WMC.getInstance(), 0, 0);
                    return true;
                }
            }
        }
        return false;
    }
}
