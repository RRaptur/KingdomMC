package me.rapturr.wmc.helpers;






import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class CustomGameProfile extends GameProfile {

    public CustomGameProfile(UUID uuid, String name, String[] skin) {
        super(uuid, name);

        setSkin(skin);
    }

    public CustomGameProfile(UUID uuid, String name, String skinName) {
        super(uuid, name);

        setSkin(MojangAPI.getSkin(skinName));


    }

    public void setSkin(String[] skin) {
        if (skin != null) {
            this.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
        }

    }

    public SkullMeta getCustomSkullMeta(ItemStack itemstack)  {
        SkullMeta skullMeta = (SkullMeta) itemstack.getItemMeta();
        Field field;
        try
        {
            assert skullMeta != null;
            field = skullMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(skullMeta, this);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return skullMeta;
    }
}

