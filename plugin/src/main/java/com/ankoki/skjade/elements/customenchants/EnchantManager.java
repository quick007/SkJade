package com.ankoki.skjade.elements.customenchants;

import com.ankoki.skjade.SkJade;
import com.ankoki.skjade.utils.CustomEnchantment;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
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

    public static boolean isCustomEnchant(String name) {
        return ALL_ENCHANTMENTS.get(name) != null;
    }

    public static ItemStack addCustomEnchant(CustomEnchantment enchant, int level, ItemStack item) {
        if (enchant == null || item == null) return null;
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(SkJade.getInstance(), enchant.getName());
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Math.max(1, level));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack removeCustomEnchant(CustomEnchantment enchant, ItemStack item) {
        if (enchant == null || item == null) return null;
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(SkJade.getInstance(), enchant.getName());
        meta.getPersistentDataContainer().remove(key);
        item.setItemMeta(meta);
        return item;
    }


    public static boolean hasCustomEnchant(String name, ItemStack item) {
        if (name == null || item == null) return false;
        return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SkJade.getInstance(), name), PersistentDataType.INTEGER);
    }

    public static int levelOfCustomEnchant(CustomEnchantment enchant, ItemStack item) {
        if (enchant == null || item == null) return 0;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        int level = 0;
        try {
            level = container.get(new NamespacedKey(SkJade.getInstance(), enchant.getName()), PersistentDataType.INTEGER);
        } catch (Exception ignored) {}
        return level;
    }

    public static boolean hasCustomEnchant(CustomEnchantment enchantment, ItemStack item) {
        if (enchantment == null || item == null) return false;
        return hasCustomEnchant(enchantment.getName(), item);
    }

    public static ItemStack setEnchantLevel(CustomEnchantment enchantment, int level, ItemStack item) {
        return addCustomEnchant(enchantment, level, item);
    }


}
