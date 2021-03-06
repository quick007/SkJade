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

@Name("Remove Custom Enchant")
@Description("Removes a/all custom enchantment(s) from a tool.")
@Examples("remove all the custom enchants from player's tool")
@Since("insert version")
public class EffRemoveCustomEnchant extends Effect {

    static {
        Skript.registerEffect(EffRemoveCustomEnchant.class,
                "remove (1¦all [[[of] the] custom enchant[ment]s|%-customenchant%) from %itemstack%");
    }

    private Expression<CustomEnchantment> enchantment;
    private Expression<ItemStack> itemStack;

    @Override
    protected void execute(Event e) {
        if (itemStack == null) return;
        ItemStack item = itemStack.getSingle(e);
        if (item == null) return;
        if (enchantment == null) {
            itemStack.change(e, new ItemStack[]{EnchantManager.removeAllCustomEnchants(item)}, ChangeMode.SET);
        } else {
            CustomEnchantment enchant = enchantment.getSingle(e);
            if (enchant == null) return;
            itemStack.change(e, new ItemStack[]{EnchantManager.removeCustomEnchant(enchant, item)}, ChangeMode.SET);
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "remove " + (enchantment == null ? "all of the custom enchants" : enchantment.toString(e, debug)) + " from " + itemStack.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        enchantment = parseResult.mark == 1 ? null : (Expression<CustomEnchantment>) exprs[0];
        itemStack = parseResult.mark == 1 ? (Expression<ItemStack>) exprs[0] : (Expression<ItemStack>) exprs[1];
        return true;
    }
}
