package me.rapturr.wmc.bot;

import com.mojang.authlib.GameProfile;
import me.rapturr.wmc.helpers.CustomGameProfile;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPC {



    public NPC(String name, Location loc, Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer sp = craftPlayer.getHandle();

        MinecraftServer server = sp.getServer();
        ServerLevel level = sp.getLevel();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Test");




    }

    /*
    SynchedEntityData watcher = new SynchedEntityData(serverPlayer);
        watcher.define(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);
        connection.send(new ClientboundSetEntityDataPacket(serverPlayer.getId(), watcher, true));
     */
}
