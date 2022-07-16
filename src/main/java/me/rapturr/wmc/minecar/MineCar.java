package me.rapturr.wmc.minecar;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.rapturr.wmc.WMC;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class MineCar implements Listener {


    public static void minecartEvent(WMC plugin) {
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();
        pm.addPacketListener(new PacketAdapter(plugin, ListenerPriority.HIGH, PacketType.Play.Client.STEER_VEHICLE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE) {
                    PacketContainer packet = event.getPacket();
                    Player player = event.getPlayer();

                    String packetValue = packet.getModifier().read(1).toString();

                    if (player.isInsideVehicle()) {

                        if (player.getVehicle() instanceof Minecart) {


                            Minecart minecart = (Minecart) player.getVehicle();
                            Vector v = player.getLocation().getDirection();




                            double x = Math.round(v.getX());
                            double z = Math.round(v.getZ());

                            Vector finalV = new Vector(x, 0, z);

                            Block b = minecart.getLocation().add(finalV).getBlock();

                            ((Minecart) player.getVehicle()).setDerailedVelocityMod(new Vector(1,  0, 1));
                            minecart.setSlowWhenEmpty(true);


                            // minecart.getLocation().setYaw(player.getLocation().getYaw());
                            if (packetValue.equalsIgnoreCase("0.98") && !b.isPassable()) {
                                minecart.setVelocity(finalV.multiply(1D).add(new Vector(0, 0.4, 0)));


                            }

                            if (packetValue.equalsIgnoreCase("-0.98") && !b.isPassable()) {
                                minecart.setVelocity(finalV.multiply(-1D).add(new Vector(0, 0.4, 0)));


                            }
                            if (packetValue.equalsIgnoreCase("0.98") && b.isPassable()) {
                                minecart.setVelocity(finalV.multiply(1D).add(new Vector(0, -1, 0)));


                            }

                            if (packetValue.equalsIgnoreCase("-0.98") && b.isPassable()) {
                                minecart.setVelocity(finalV.multiply(-1D).add(new Vector(0, -1, 0)));


                            }
                            if (packetValue.equalsIgnoreCase("0.0")) {
                                minecart.setVelocity(new Vector(0, 0, 0));
                            }
                        }
                    }
                }

            }
        });
    }
}
