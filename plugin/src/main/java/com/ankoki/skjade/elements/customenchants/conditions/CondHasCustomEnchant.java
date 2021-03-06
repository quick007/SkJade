package com.ankoki.skjade.elements.customenchants.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.ankoki.skjade.elements.customenchants.EnchantManager;
import com.ankoki.skjade.utils.CustomEnchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Is Custom Enchant")
@Description("Checks if a string is the name of a custom enchantment.")
@Examples("if \"jackhammer\" is a custom enchant")
@Since("insert version")
public class CondHasCustomEnchant extends Condition {

    static {
        Skript.registerCondition(CondHasCustomEnchant.class,
                "%itemstack% has (1¦any [skjade] custom enchant[ment]|%-customenchants%)");
    }

    private Expression<CustomEnchantment> enchantment;
    private Expression<ItemStack> itemStack;

    @Override
    public boolean check(Event e) {
        ItemStack item = itemStack.getSingle(e);
        if (item == null) return false;
        if (enchantment == null) {
            return EnchantManager.hasCustomEnchant(item);
        }
        CustomEnchantment enchant = enchantment.getSingle(e);
        if (enchant == null) return false;
        return EnchantManager.hasCustomEnchant(enchant, item);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return itemStack.toString(e, debug) + " has " + (enchantment == null ? "any custom enchant" : enchantment.toString(e, debug));
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        enchantment = parseResult.mark == 1 ? null : (Expression<CustomEnchantment>) exprs[0];
        itemStack = parseResult.mark == 1 ? (Expression<ItemStack>) exprs[0] : (Expression<ItemStack>) exprs[1];
        return true;
    }
}