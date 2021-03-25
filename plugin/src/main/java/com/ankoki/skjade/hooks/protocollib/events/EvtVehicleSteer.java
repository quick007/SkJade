package com.ankoki.skjade.hooks.protocollib.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import com.ankoki.skjade.hooks.protocollib.bukkitevents.VehicleSteerEvent;

public class EvtVehicleSteer extends SimpleEvent {

    static {
        Skript.registerEvent("Vehicle Steer", EvtVehicleSteer.class, VehicleSteerEvent.class,
                "vehicle (steer|move)");
    }
}
