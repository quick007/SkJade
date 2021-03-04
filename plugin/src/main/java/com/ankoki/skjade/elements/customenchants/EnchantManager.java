package com.ankoki.skjade.elements.customenchants;

import com.ankoki.skjade.SkJade;
import com.ankoki.skjade.utils.CustomEnchantment;
import com.ankoki.skjade.utils.ToolGroup;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class EnchantManager {
    private EnchantManager(){}
    private static final Map<String, CustomEnchantment> ALL_ENCHANTMENTS = new HashMap<>();

    public static CustomEnchantment getEnchantment(String enchant) {
        return ALL_ENCHANTMENTS.get(enchant);
    }

    public static void registerEnchantment(String enchant, CustomEnchantment enchantment) {
        ALL_ENCHANTMENTS.put(enchant, enchantment);
    }

    public static void setRarity(String enchant, int rarity) {
        CustomEnchantment ench = ALL_ENCHANTMENTS.get(enchant);
        if (ench == null) return;
        ALL_ENCHANTMENTS.remove(enchant);
        ench.setRarity(rarity);
        ALL_ENCHANTMENTS.put(enchant, ench);
    }

    public static void setMaxLevel(String enchant, int maxLevel) {
        CustomEnchantment ench = ALL_ENCHANTMENTS.get(enchant);
        if (ench == null) return;
        ALL_ENCHANTMENTS.remove(enchant);
        ench.setMaxLevel(maxLevel);
        ALL_ENCHANTMENTS.put(enchant, ench);
    }

    public static void setTableAllowed(String enchant, boolean tableAllowed) {
        CustomEnchantment ench = ALL_ENCHANTMENTS.get(enchant);
        if (ench == null) return;
        ALL_ENCHANTMENTS.remove(enchant);
        ench.setTableAllowed(tableAllowed);
        ALL_ENCHANTMENTS.put(enchant, ench);
    }

    public static void setAcceptedTools(String enchant, ToolGroup[] acceptedTools) {
        CustomEnchantment ench = ALL_ENCHANTMENTS.get(enchant);
        if (ench == null) return;
        ALL_ENCHANTMENTS.remove(enchant);
        ench.setAcceptedTools(acceptedTools);
        ALL_ENCHANTMENTS.put(enchant, ench);
    }

    public static boolean isCustomEnchant(String name) {
        return ALL_ENCHANTMENTS.get(name) != null;
    }

    public static void addCustomEnchant(CustomEnchantment enchant, int level, ItemStack item) {
        if (enchant == null || item == null) return;
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(SkJade.getInstance(), enchant.getName());
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Math.min(level, enchant.getMaxLevel()));
    }

    public static void removeCustomEnchant(CustomEnchantment enchant, ItemStack item) {
        if (enchant == null || item == null) return;
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().remove(new NamespacedKey(SkJade.getInstance(), enchant.getName()));
    }

    public static boolean hasCustomEnchant(String name, ItemStack item) {
        if (name == null || item == null) return false;
        return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SkJade.getInstance(), name), PersistentDataType.INTEGER);
    }

    public static boolean hasCustomEnchant(CustomEnchantment enchantment, ItemStack item) {
        if (enchantment == null || item == null) return false;
        return hasCustomEnchant(enchantment.getName(), item);
    }
}
