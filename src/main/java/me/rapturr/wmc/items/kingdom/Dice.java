package me.rapturr.wmc.items.kingdom;

import com.comphenix.protocol.wrappers.Pair;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.rapturr.wmc.helpers.*;
import me.rapturr.wmc.items.WesternItem;
import net.minecraft.world.entity.player.ProfilePublicKey;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Dice extends WesternItem {
    public Dice(String itemID, String displayName, boolean isStackable, boolean isShiny, ItemRarity rarity, Material mat, String desc, ItemType type, boolean hasActiveEffect) {
        super(itemID, displayName, isStackable, isShiny, rarity, mat, desc, type, hasActiveEffect);
    }

    List<Pair<String, String>> skins = Arrays.asList(
            new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ2MjU0Mjg4NiwKICAicHJvZmlsZUlkIiA6ICI5OTdjZjFlMmY1NGQ0YzEyOWY2ZjU5ZTVlNjU1YjZmNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJpbzEyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2M4Njk5Y2MxMmQxMDQzZWM5YjE2ZTlkMzMzMGUzZjU4YmYwMjJkMDE3NjBlOTMwM2E0MjZjMzM3YmRjMGUyNWEiCiAgICB9CiAgfQp9", "ZMAtmM69pQqY7a21gwZQySR3yw9YUyVGEfImNtREbv+O5FnCam9YNMvoGjvCgFh47CMskpDZ7AZjgjjDfKs4CUkc9IZZ91nvd4ZXiYdC7sfDkloTTeaaaefMGem2fb7do3Bp+HXPjGCJsS9LTQ9HhuIuMs4Uq4BWmy8xLu8cWUt9gQkkfabpqm5GApotKPoSf3/Hc58BN7yi85Ax6m4XwMyAWEz0/lgkyBFXoeawUHLmuDV3o1tFPq9yQgJVLdQCBXl6LGIjzlX6Q4MoDs1dxj4fHc9Nweb+2MsYmP5cQ0Q8cJarjtYgBkLc92rjavslXWoYoQqMmefULa5ZYdT0kXsghzW2mdltdrtC7o75Xm0g7aiLRw0QRdw5mDGaiCA1Jwam5J6emzIjSq0x6NFs3N9z37TbAvCjFXSVF3MNYCDxCJEuUpCcgzXNEc2XsEM1vW2wEwA/fYMFpSzFqGEVr5vIL+aZNf1zdwIqwVyEDt+HZSF5SJjiujWS13sS7FopGE7qzgJVNDBwfyhSxLmUffWCEtAFq+UCad1AyAvye/6rMQjIulglJRh32Hl2sBkt6H0sIognANrJUZB5Dls63HrUmpd5NRaQYNqYXtbS7ZV0a1NSMilebbp2DIexGfLo31GcIPo8hTHEoqiTtOtE4Oi1gUi93ndHLO5KCF3IkK4="),
            new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ2MjgwNTg4MywKICAicHJvZmlsZUlkIiA6ICJkYmNlZjMyZjI5ZDc0Y2UzOTUzOWMwYjBhMTE1YjZiZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJyYW1waXJlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzViN2E4ODRmZWE3OTJiODNiMjdiMDY0YmU3ZTQzNjAyMzY4MWNmZTQyNTEwZDIyOTdkNDhkZmRkNzVmZmIyOTYiCiAgICB9CiAgfQp9", "jXMZ7PCVTEZ9tORqRn1HN3Jn1PtBbQhebhd38Dj0S74vfHB61TYqO3H3pq/5Zf4oIFpPW9xj8oUf/y2OATo5dEaOZogXeq1F9ueEvgFDFGOVpzjOoOV+Sex2X2mkT0T8qhyx8fvbrBDAJebjhcWvGxOzIp4B4Nlg4Lx/HEXRpP3U8xOE8P9pAvdoWL3R6cnhAfGGXMFRid5KXJ926VJIGAsGeEVuy1jpr1iYEMG2N22EWqyS9NBtdZ8XS36ea+zwfFASSgxjRgHhpD2XVjqGbcBKZKpfRcMD9Wi/92bSVx4mWqIPBbsuKpwNVMGlTeQnWCHiN75uMIQR3QZCUdGdSK7f9RWutI/amnTbTvjYmqQ4n4laZbDCi6P5mmMgtZ0aJk5PLJKf75iPyMl1ZGZ/2peYTXknBt9G/9boygnvDq/ugN9r56DaIC5vr6gHVVzIVXSEOaGJCSvc1zJ6ADc/oJlP6/A9e6h/sjnAPGhQZRQBi8l1829TUNqEy9ptdt+BUBuinuXIR8e6nUF/ChhUCe6hXCKT/679Ok+BdqdKI44gkX6UsSL9ehGnKCWYVzEaV4+7kA9em8OQZHViHlV/0dyBwpLol5wIf0/sUcujovl+NwvTfOWiNFRFiVpO8SfegyEENVHGkW5a4UWfI2WpDuHx6iPWw3wVY/r6rZ3Lf3Q="),
            new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ2MjgyNTIwMywKICAicHJvZmlsZUlkIiA6ICJiYjdjY2E3MTA0MzQ0NDEyOGQzMDg5ZTEzYmRmYWI1OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJsYXVyZW5jaW8zMDMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2RjZDg4ZDcxYWNkMjMzZDdhNzg3M2E0NTNhMDViYzRmM2YyZDE1YTRlMTJhOGUzZWE2YmE2ZTM4MjljZjk2YiIKICAgIH0KICB9Cn0=", "f/q9NCc21oxn/SiP92Wu6pQa8APOWBUtFCtDK1Cx0BadmDA/sgPWsvH+1y+x7EoBZNSWuLNWzzmlwc/VtnKLX3eBJZD1ETrFJP8ts+VTvz6Iuc3zwpH2Auv+Ax6zrsyUzJB+TGVSGI6ds8pWks/jgNImACsYsMQfEcAtSzslx299Gd4NQbf2vnthIfR5TOSZ5kEhQh53yFkHyTAsFakkSlQsJmfOfKJCfzMl9wpgRntZ4DmHZhvss9oI945cQ8t5sE/wRmbB20fDlviVSYJPVTZKQA2kRNinZDNLmnxaAmVqwhTA8462BMO1vszJSJPjdmAn8VW2unYsRGql2n9cCergKAjkEtfyITLBzbSC5rkjFE2Rinitiy1mqVjZyMKuAAd28F2vU1ulxko+0ap7bQrOWQPRkWb7UDx04tFtLqJ5YXwrnCDS08RN5eEDsenVPm01Pv52oCel+eU5LBYEINvxkoIiAmZE9Oy7Sx1OJxfsksjM+3q0pptdBg72EjIuMjEGh66Sg5J3/IZAyc18InjfEsEJwfcVLaX/+evfrH0yExyxWCCCHIc6PD+DygXH5tMYSgFlM3H9HjeDlozy7+LkncUU5m+VY1rLeprPheMAYYsJFgu5GRvaMDwJk8IXr5piJ6SZ2jGYlDy9HYLDftdkqXwG9CCmyRuCq1zCg+E="),
            new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ2Mjg0MjE2NiwKICAicHJvZmlsZUlkIiA6ICJmNWQwYjFhZTQxNmU0YTE5ODEyMTRmZGQzMWU3MzA1YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXRjaFRoZVdhdmUxMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMDRlMjNhODViNjcyODg5NjE3MjIzZmUxODZhYmFjNzk3OTI2ODg0MTIyMWU4ZmNiODQ1NTRiNWQzODYwOWViIgogICAgfQogIH0KfQ==", "j4tsw5Uad+NfWaNYaeLJknZj+uH4FL2hkWCdYviapvvaYUe5HaZK3IZVpLp200D5mlw+9BT5cwFG/CzPVocBYt2WyCfUN53oqx17U95hvt5+ps62eYuLhefmHor+b+AI4cGbFman1EKGClhsu0Y+/Q/MSMog+DxVAHY5C3mNFQ2UqmrlZfN7JphRz6v0gjLMO+ksM/F9bz8Jp0GZgYEvM8cdtRVcrYLtf/w4jTNsjqkuGI1YrTJqUI/F44cdXqNq+wqM+EppPNkKLIE6WfUXIYc7hpfd7t/I/N+ObsX4+vwY3yVKNwaLnPKwiMUpLVld4xKluy6XT+r8bDBLXc6I2Q3gFIytSURl4lu5UIhzN7SEIWI8sUZUudpn62jzWW4iSj5jHjR/3gB0r5gh2H7oW+e0VqqRXGJIeX5Vsw/5juEIv/SlLsPTTIcF6yEr6avn7UzDXoW+IrY0ED3fV4F46OTopqa9cMmzIL9vQW420LshFArqYHHXycj5Sx1wszHgQwh6bvMuum2/W8e4eFJCzjOC3Z4ugvVHVyCsKwxWO7wS4R50ByH3/u4d3SnuR4ppPYHXXpVsSm3d2GT4iw9wOARe8uBRvGxc+gAtUwkW+f5s0SyPgOxXv8RPPsPyLfdrVEeVkMR+8Enq4+hFNPN5R1bHbzLsiS8PPuhtUOIlGGs="),
            new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ2Mjg2MjY4NywKICAicHJvZmlsZUlkIiA6ICJmYzUwMjkzYTVkMGI0NzViYWYwNDJhNzIwMWJhMzBkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDVUNGTDE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIwMzVkOWM3NzBjNDI4MWVlMDhhMWM5OTBlNzVjYTEwOTJiZjY5MmU3ZTQ0MGQ1MmEwZjczNTM3YmUyZmU5NDAiCiAgICB9CiAgfQp9", "yg143/FPquAX3ivq0mMp6fnEW5thvRXKM/XVC9AS60RrEVEh/FiskrpcdHX7eeFnAhXKXKUCCoac/4YnJTQqDQmBEBVPk+unGNMUFABfGv06bInabQbG9qvomrWFCzlFTssK33pxrgB2R+ZuhCNKFmMmIPjMaRhwtIoSD8mTLt5Q/qTdMvuSjS4kdefLnBxVqCds5uD4CFWXN6qLnPiqPtTzUYQsHSJ32h9lLUXFwGrNOvAcr7fafq34Svfy6Jt59JBiewxsATa2X8WVabgpxVu5iEe12PBbyl3MZ5wSZ3SVJ5tmc3Wvho1t124BybCVDYNEG89pxRpNjIiZrpxgf5t29o3h+SnOciG1RpIdzNxvbm9VlCtLNywWImZvfPdUwJZxJKYmBKWh2Yb4r9z2gX2NJhQNTO8an9NaPC1nRV2XcUlheT9Rw6bEL9wNJ1FiiBNYA0xsEpUz0q5DfaUfDNaLuHGi+20zFloN57WCUXawGi1p7cDllaxt06i4DUOSHDrFF8wiByVhnx3AUEg+nb0njb8jwMeff1/rThGgTucVHpdfkMX6Ib/FnGGNhsUJM5+beCHMQOApTqqYs6V2bHWRbsjfu1R5yAHbHUiro7McZin3CYNJ5Thsu357dKp5e3U3y1t5z6Oy3EYj1ms53Hmyao4kb08xXfZZutV6iFA="),
            new Pair<>("ewogICJ0aW1lc3RhbXAiIDogMTYyNjQ2Mjg4MDI0MywKICAicHJvZmlsZUlkIiA6ICI0NWY3YTJlNjE3ODE0YjJjODAwODM5MmRmN2IzNWY0ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJfSnVzdERvSXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzMyZGVkMWI1YmQ3ZTBhNTIwNTBhYTZlY2E0NjU4ZmQ4YmI5ZDQ4YzNiYzUwYTAwOTk0NjQzYjEyOGEyM2RlMyIKICAgIH0KICB9Cn0=", "RrEHzS0aW1Haw2avg5pAur5EmaA+cF8bTJRuDi0/zpcccY4mE2YoPCOoJ2FGhfJEvhSnqXJxXEV9qnVcHK9QupYns0UmLp5NQIYf2Q5GihIJEksmGOg6ZT4ES+/Car7UOfzgD/1UKQEZkkcqvRgQ/c1xUH1w7FOVLu9OUPREXVK2M0/YzRCj+6AIBmi0lvyh33lhBw+3OaVAPRAojxIxNvNEWbN91N3NEktrNOPQa6qBtCFXpcEIO4xsHXqgsOJAdKns2hKijh+QJQl03l6vSFWchB+PAL7RCwcx/a0kwIE5nm/05L+/UArLRbmpaEvT2CEqEKge+6mItNwyPCeHd79uwf4Nyu+6Lj6zP3zgey+X3Ymi/xh96WDclwlohmAMdyutekpXm8fKfxb8dz6zFXDjcHXcKaYs14kQISHuNcVjZIYBeRgqZM3fE6ObT9xwNJj+Sl1LR4QbgV4wtztNZxAcN9vUF39YcbBLWJdK7Xh2fH+QCHgiRzGJAKrO3fZTUSLPw2N3JYNzhxQyiYWKXRHakSuONtnLyGpPmX40aY7fMFD0QyBlRxkwGPeJ3NGtdCWPMbRD/hBgJjdsR91NEXvqIgg/Knvku6OZCgR02YwlDt/tj1FSnu0wbDKaUQr5G/MrXgg9FCBi+VVHLYJdHoU4rBI9epdDuhWo4/aGK3c=")
    );

    @Override
    public void onitemstackcreate(ItemStack itemStack) {
        useDice(itemStack);
    }

    @Override
    public void activeEffect(Player player, ItemStack itemStack) {

    }

    @Override
    public void onDropAction(Player player, ItemStack itemStack, PlayerDropItemEvent event) {
        useDice(itemStack);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SKELETON_AMBIENT,1 , 1);
    }

    @Override
    public void onPickUpAction(Player player, Item item, ItemStack itemStack, EntityPickupItemEvent event) {

    }

    @Override
    public void leftClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void leftClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {
        event.setCancelled(true);
        player.updateInventory();
    }

    @Override
    public void rightClickAirAction(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void rightClickBlockAction(Player player, ItemStack itemStack, PlayerInteractEvent event, Block block) {

    }

    @Override
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {

    }
    @Override
    public void hitByEntityAction(Player player, EntityDamageByEntityEvent event, Entity entity, ItemStack item) {

    }
    @Override
    public void bowShootAction(Player player, EntityShootBowEvent event, ItemStack itemStack) {

    }

    @Override
    public void onRodAction(Player player, ItemStack itemStack, PlayerFishEvent event) {

    }

    private void useDice(ItemStack itemStack) {
        int random = Utilities.createRandomInt(0, 6);
        CustomGameProfile profile = new CustomGameProfile(UUID.randomUUID(), "", new String[]{skins.get(random).getFirst(), skins.get(random).getSecond()});
        //skin.setTextures((PlayerTextures) new Property("textures", skins.get(random).getFirst(), skins.get(random).getSecond()));
        //skin.setProperty(new ProfileProperty("textures", skins.get(random).getFirst(), skins.get(random).getSecond()));
        itemStack.setItemMeta(profile.getCustomSkullMeta(itemStack));
    }
}
