package com.ankoki.skjade.elements.customenchants.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.ankoki.skjade.elements.customenchants.EnchantManager;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Register Custom Enchant")
@Description("Registers a new custom enchantment with the given name.")
@Examples("register the custom enchant \"jackhammer\"")
@Since("insert version")
public class EffRegisterCustomEnchant extends Effect {

    static {
        Skript.registerEffect(EffRegisterCustomEnchant.class,
                "register [(a|the)] [new] custom enchant[ment] [(called|named)] %string%");
    }

    private Expression<String> name;

    @Override
    protected void execute(Event e) {
        if (name == null) return;
        String n = name.getSingle(e);
        if (n == null) return;
        EnchantManager.registerEnchantment(n);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "register the custom enchant " + name.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }
}
