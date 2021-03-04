package com.ankoki.skjade.elements.customenchants.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.ankoki.skjade.elements.customenchants.EnchantManager;
import com.ankoki.skjade.utils.CustomEnchantment;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Custom Enchant")
@Description("Returns a custom enchant, will not be set if the custom enchant doesn't exist.")
@Examples("add the custom enchant \"jackhammer\" to player's tool")
@Since("insert version")
public class ExprCustomEnchant extends SimpleExpression<CustomEnchantment> {

    static {
        Skript.registerExpression(ExprCustomEnchant.class, CustomEnchantment.class, ExpressionType.SIMPLE,
                "[the] custom enchant[ment] %string%");
    }

    private Expression<String> enchantName;

    @Nullable
    @Override
    protected CustomEnchantment[] get(Event e) {
        if (enchantName == null) return null;
        String enchant = enchantName.getSingle(e);
        if (enchant == null) return null;
        return new CustomEnchantment[]{EnchantManager.getEnchantment(enchant)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends CustomEnchantment> getReturnType() {
        return CustomEnchantment.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the custom enchant " + enchantName.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        enchantName = (Expression<String>) exprs[0];
        return true;
    }
}
