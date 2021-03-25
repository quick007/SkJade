package com.ankoki.skjade.hooks.protocollib.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

@Name("Destroy Entity")
@Description("Destroys entities from players.")
@Examples("destroy player's target from player")
@RequiredPlugins("ProtocolLib")
@Since("insert version")
public class EffDestroyEntity extends Effect {

    static {
        Skript.registerEffect(EffDestroyEntity.class,
                "destroy %entities% (for|from) %players%");
    }

    private Expression<Entity> entities;
    private Expression<Player> players;

    @Override
    protected void execute(Event e) {
        Entity[] es = entities.getArray(e);
        Player[] ps = players.getArray(e);
        if (es.length < 1 || ps.length < 1) return;
        int[] ids = new int[es.length - 1];
        int i = 0;
        for (Entity ent : es) {
            ids[i] = ent.getEntityId();
            i++;
        }
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntegers().write(0, ids.length - 1);
        packet.getIntegerArrays().write(1, ids);
        for (Player p : ps) {
            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
            } catch (InvocationTargetException ignored) {}
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "destroy " + entities.toString(e, debug) + " for " + players.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }
}
