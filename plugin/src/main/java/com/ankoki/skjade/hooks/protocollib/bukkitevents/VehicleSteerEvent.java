package com.ankoki.skjade.hooks.protocollib.bukkitevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class VehicleSteerEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();
    public VehicleSteerEvent(){};

    public static HandlerList getHandlersList() {
        return handlerList;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
