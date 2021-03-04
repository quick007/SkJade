package com.ankoki.skjade.elements.customenchants.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.ankoki.skjade.utils.CustomEnchantment;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprCustomEnchant extends SimpleExpression<CustomEnchantment> {

    static {

    }

    @Nullable
    @Override
    protected CustomEnchantment[] get(Event e) {
        return new CustomEnchantment[0];
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends CustomEnchantment> getReturnType() {
        return null;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return false;
    }
}
