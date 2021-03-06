package com.ankoki.skjade.elements.customenchants.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.ankoki.skjade.elements.customenchants.EnchantManager;
import com.ankoki.skjade.utils.CustomEnchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Custom Enchant Item")
@Description("Adds a custom enchantment to an item.")
@Examples("add the custom enchant \"jackhammer\" 3 to player's tool")
@Since("insert version")
public class EffCustomEnchantItem extends Effect {

    static {
        Skript.registerEffect(EffCustomEnchantItem.class,
                "add %customenchant% [[with [the]] level] %number% to %itemstack%");
    }

    private Expression<CustomEnchantment> enchantment;
    private Expression<Number> level;
    private Expression<ItemStack> item;

    @Override
    protected void execute(Event e) {
        if (enchantment == null || item == null || level == null) return;
        CustomEnchantment enchant = enchantment.getSingle(e);
        int lev = level.getSingle(e).intValue();
        ItemStack i = item.getSingle(e);
        if (i == null || enchant == null) return;
        if (i.getType().isBlock()) return;
        item.change(e, new ItemStack[]{EnchantManager.addCustomEnchant(enchant, lev, i)}, ChangeMode.SET);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "add " + enchantment.toString(e, debug) + " level " + level.toString(e, debug) + " to " + item.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        enchantment = (Expression<CustomEnchantment>) exprs[0];
        level = (Expression<Number>) exprs[1];
        item = (Expression<ItemStack>) exprs[2];
        return true;
    }
}
