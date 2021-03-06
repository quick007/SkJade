package com.ankoki.skjade.elements.customenchants.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.ankoki.skjade.elements.customenchants.EnchantManager;
import com.ankoki.skjade.utils.CustomEnchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Level of Custom Enchant")
@Description("The level of a custom enchant on a tool. 0 if the enchant isn't on it.")
@Examples("broadcast \"%the level of the custom enchant \"\"jackhammer\"\" on player's tool%\"")
@Since("insert version")
public class ExprCustomEnchantLevel extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprCustomEnchantLevel.class, Number.class, ExpressionType.SIMPLE,
                "[the] level of %customenchant% on %itemstack%");
    }

    private Expression<CustomEnchantment> enchantment;
    private Expression<ItemStack> item;

    @Nullable
    @Override
    protected Number[] get(Event e) {
        if (enchantment == null || item == null) return new Number[]{0};
        CustomEnchantment enchant = enchantment.getSingle(e);
        ItemStack i = item.getSingle(e);
        if (enchant == null || i == null) return new Number[]{0};
        return new Number[]{EnchantManager.levelOfCustomEnchant(enchant, i)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the level of " + enchantment.toString(e, debug) + " on " + item.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        enchantment = (Expression<CustomEnchantment>) exprs[0];
        item = (Expression<ItemStack>) exprs[1];
        return true;
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.SET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
        if (delta == null || delta.length < 1 || enchantment == null || item == null ||(mode != ChangeMode.ADD && mode != ChangeMode.REMOVE && mode != ChangeMode.SET)) return;
        CustomEnchantment enchant = enchantment.getSingle(e);
        ItemStack i = item.getSingle(e);
        int currentLevel = EnchantManager.levelOfCustomEnchant(enchant, i);
        int level = (int) delta[0];
        switch (mode) {
            case ADD:
                item.change(e, new ItemStack[]{EnchantManager.setEnchantLevel(enchant, currentLevel + level, i)}, ChangeMode.SET);
                break;
            case REMOVE:
                item.change(e, new ItemStack[]{EnchantManager.setEnchantLevel(enchant, currentLevel - level, i)}, ChangeMode.SET);
                break;
            case SET:
                item.change(e, new ItemStack[]{EnchantManager.setEnchantLevel(enchant, level, i)}, ChangeMode.SET);
        }
    }
}
