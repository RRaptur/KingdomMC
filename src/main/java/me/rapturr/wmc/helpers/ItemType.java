package me.rapturr.wmc.helpers;

import org.bukkit.ChatColor;

public enum ItemType {

    SWORD("Sword"),
    AXE("Axe"),
    WAND("Wand"),
    BOOK("Spell Book"),
    BOW("Bow"),
    PISTOL("Pistol"),
    RIFLE("Rifle"),
    MISC("Miscellaneous"),
    CONSUMABLE("Consumable"),
    NONE("");

    private final String string;

    public String getString() {
        return ChatColor.DARK_GRAY + string;
    }

    ItemType(String string) {
        this.string = string;
    }
}
