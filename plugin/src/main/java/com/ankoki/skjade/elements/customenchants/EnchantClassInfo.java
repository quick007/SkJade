package com.ankoki.skjade.elements.customenchants;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import com.ankoki.skjade.utils.CustomEnchantment;

public class EnchantClassInfo {

    static {
        Classes.registerClass(new ClassInfo<>(CustomEnchantment.class, "customenchant")
                .user("customenchant(ment)?s?")
                .name("Custom Enchant")
                .description("A custom enchantment.")
                .since("insert version"));
    }
}
