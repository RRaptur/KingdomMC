package me.rapturr.wmc.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players may use this command!");
            return true;
        }
        if (label.equalsIgnoreCase("spawn")) {
            sender.sendMessage(ChatColor.RED + "There is no spawn!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            return true;
        }
        return false;
    }
}
