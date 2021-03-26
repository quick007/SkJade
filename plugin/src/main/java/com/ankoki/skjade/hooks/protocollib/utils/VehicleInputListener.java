package com.ankoki.skjade.hooks.protocollib.utils;

import com.ankoki.skjade.SkJade;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;

public class VehicleInputListener extends PacketAdapter {

    static {
        ProtocolLibrary.getProtocolManager().addPacketListener(new VehicleInputListener(SkJade.getInstance(),  ListenerPriority.HIGHEST, PacketType.Play.Client.STEER_VEHICLE));
    }

    public VehicleInputListener(Plugin plugin, ListenerPriority listenerPriority, PacketType... types) {
        super(plugin, listenerPriority, types);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.getPacketType().equals(PacketType.Play.Client.STEER_VEHICLE)){
            PacketContainer packet = event.getPacket();
            float leftMost = packet.getFloat().read(0);
            float forwardMost = packet.getFloat().read(1);
            byte space = packet.getBytes().read(0);
        }
    }
}
