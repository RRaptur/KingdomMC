package me.rapturr.wmc.armorstand;

import dev.geco.gsit.api.GSitAPI;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

public class WesternArmorStand {

    String armorStandId;
    ArmorStand armorStand;

    public WesternArmorStand(String armorStandId) {
        this.armorStandId = armorStandId;
    }

    public void spawnArmorStand(World world, Location location) {
        armorStand = world.spawn(location, ArmorStand.class);
    }
}
