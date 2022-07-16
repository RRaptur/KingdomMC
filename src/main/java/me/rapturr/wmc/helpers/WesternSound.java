package me.rapturr.wmc.helpers;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public enum WesternSound {

    PISTOL_SHOOT(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 6, 0.5f),
    PISTOL_RELOAD(Sound.ITEM_SPYGLASS_USE, 6, 1),
    RELOAD_CANCEL(Sound.ENTITY_ZOMBIE_HURT, 1, 1);


    ;

    private final Sound sound;
    private final float volume;
    private final float pitch;


    WesternSound(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;

    }

    public void playPlayerSound(Player player, Location location) {
        player.playSound(location, getSound(), getVolume(), getPitch());
    }

    public void playWorldSound(World world, Location location) {
        world.playSound(location, getSound(), getVolume(), getPitch());
    }

    public Sound getSound() {
        return sound;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }


}
