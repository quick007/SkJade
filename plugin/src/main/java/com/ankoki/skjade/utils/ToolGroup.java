package com.ankoki.skjade.utils;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ToolGroup {
    SWORDS(Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD),
    AXES(Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_SWORD),
    PICKAXES(Material.WOODEN_PICKAXE,
            Material.STONE_PICKAXE,
            Material.IRON_PICKAXE,
            Material.GOLDEN_PICKAXE,
            Material.DIAMOND_AXE),
    HELMETS(Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.IRON_HELMET,
            Material.GOLDEN_HELMET,
            Material.DIAMOND_HELMET),
    CHESTPLATES(Material.LEATHER_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.GOLDEN_HELMET,
            Material.DIAMOND_CHESTPLATE),
    LEGGINGS(Material.LEATHER_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_LEGGINGS),
    BOOTS(Material.LEATHER_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.DIAMOND_BOOTS),
    BOWS(Material.BOW,
            Material.CROSSBOW),
    TRIDENTS(Material.TRIDENT);

    private final List<Material> allMaterials;

    ToolGroup(Material... materials) {
        this.allMaterials = Arrays.asList(materials);
    }

    public List<Material> getAllMaterials() {
        return allMaterials;
    }
}
