/*
package me.rapturr.wmc.bot;


import com.mojang.authlib.GameProfile;
import me.rapturr.wmc.helpers.CustomGameProfile;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.protocol.EnumProtocolDirection;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCOld {

    private final ServerPlayer npc;
    private final String npcName;
    private final String[] skin;
    private final GameProfile gameProfile;
    private final int npcID;
    DataWatcher watcher;
    PlayerConnection connection;

    public static PlayerConnection getPlayerConnection(Player player) {
        return ((CraftPlayer) player).getHandle().b;
    }

    public void setLocation(Location loc) {
        getNpc().b(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    public void addPacket() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerConnection playerConnection = getPlayerConnection(p);
            playerConnection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc));
            playerConnection.a(new PacketPlayOutNamedEntitySpawn(npc));
            updatePlayerRotation();
            overlayPacket();

        }
    }

    private void updatePlayerRotation() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = getPlayerConnection(p);
            //connection.a(new PacketPlayOutEntity.PacketPlayOutEntityLook(getNpcID(), (byte)((int)(npc.getBukkitYaw() * 256.0F / 360.0F)), (byte)((int)(npc.getBukkitEntity().getLocation().getPitch() * 256.0F / 360.0F)), false));
            connection.a(new PacketPlayOutEntityHeadRotation(npc, (byte)(npc.getBukkitYaw() * 256.0F / 360.0F)));
        }
    }

    public void removeVisually() {
        removePacket();
        removeTab();
    }



    public void PunchPacket() {
        animationPacket(0);
    }

    public void takeDamagePacket() {
        animationPacket(1);
    }

    public void criticalEffect() {
        animationPacket(4);
    }

    public void removePacket() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            getPlayerConnection(p).a(new PacketPlayOutEntityDestroy(getNpcID()));
        }
    }

    public void removeTab() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            getPlayerConnection(p).a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, npc));
        }
    }

    public void teleportPacket(double x, double y, double z, float yaw, float pitch) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            npc.b(x, y, z, yaw, pitch);
            getPlayerConnection(p).a(new PacketPlayOutEntityTeleport(npc));
            getPlayerConnection(p).a(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.getBukkitYaw() * 256F / 360F)));
        }
    }

    public NPCOld(World world, String npcName, String[] skin) {
        this.npcName = npcName;
        this.skin = skin;
        this.gameProfile = new CustomGameProfile(UUID.randomUUID(), npcName, npcName);

        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) world).getHandle();

        npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile);
        watcher = npc.ai();
        this.npcID = npc.ae();
        connection = new PlayerConnection(nmsServer, new NetworkManager(EnumProtocolDirection.b), npc);

    }

    private void overlayPacket() {

        byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40; // each of the overlays (cape, jacket, sleeves, pants, hat)
        watcher.b(new DataWatcherObject<>(17, DataWatcherRegistry.a), b); // To find value use wiki.vg

        for (Player p : Bukkit.getOnlinePlayers()) {
            getPlayerConnection(p).a(new PacketPlayOutEntityMetadata(getNpcID(), watcher, false));
        }
    }


    private void animationPacket(int value) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            getPlayerConnection(p).a(new PacketPlayOutAnimation(npc, value));
        }
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public int getNpcID() {
        return npcID;
    }

    public EntityPlayer getNpc() {
        return npc;
    }
}

 */