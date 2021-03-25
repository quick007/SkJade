package com.ankoki.skjade.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Name("Teleport with Passenger")
@Description("Teleports entities and their passengers.")
@Examples("teleport player and its passengers to arg-1")
@Since("insert version")
public class EffTeleportPassenger extends Effect {

    static {
        Skript.registerEffect(EffTeleportPassenger.class,
                "teleport %entities% (and|with) (its|their) passenger[s] to %location%");
    }

    private Expression<Entity> entity;
    private Expression<Location> location;

    @Override
    protected void execute(Event e) {
        if (entity == null || location == null) return;
        Entity[] ents = entity.getArray(e);
        Location loc = location.getSingle(e);
        if (loc == null || ents.length < 1) return;
        for (Entity ent : ents) {
            List<Entity> passengers = ent.getPassengers();
            ent.teleport(loc);
            if (passengers.isEmpty()) continue;
            for (Entity p : passengers) {
                ent.addPassenger(p);
            }
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "teleport " + entity.toString(e, debug) + " with its passengers to " + location.toString(e ,debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entity = (Expression<Entity>) exprs[0];
        location = (Expression<Location>) exprs[1];
        return true;
    }
}
