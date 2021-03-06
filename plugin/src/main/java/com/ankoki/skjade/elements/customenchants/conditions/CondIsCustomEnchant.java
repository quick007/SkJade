package com.ankoki.skjade.elements.customenchants.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.ankoki.skjade.elements.customenchants.EnchantManager;

@Name("Is Custom Enchant")
@Description("Checks if a string is the name of a custom enchantment.")
@Examples("if \"jackhammer\" is a custom enchant")
@Since("insert version")
public class CondIsCustomEnchant extends PropertyCondition<String> {

    static {
        register(CondIsCustomEnchant.class, "a[n] [valid] [skjade] custom enchant[ment]", "strings");
    }

    @Override
    public boolean check(String s) {
        return EnchantManager.isCustomEnchant(s);
    }

    @Override
    protected String getPropertyName() {
        return "custom enchant";
    }
}
