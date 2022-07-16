package me.rapturr.wmc.helpers;

import org.bukkit.ChatColor;


public enum ItemRarity {

    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.YELLOW),
    RARE(ChatColor.AQUA),
    EPIC(ChatColor.LIGHT_PURPLE),
    SPECIAL(ChatColor.RED);


    private final ChatColor color;

    ItemRarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return this.color;
    }

}
