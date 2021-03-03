package com.ankoki.skjade.utils;

public class CustomEnchantment {

    private final String name;
    private int rarity;
    private int maxLevel;

    public CustomEnchantment(String name) {
        this.name = name;
    }

    public CustomEnchantment(String name, int rarity, int maxLevel) {
        this.name = name;
        this.rarity = Math.min(rarity, 100);
        this.maxLevel = maxLevel;
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setMaxLevel(int rarity) {
        this.maxLevel = Math.min(rarity, 100);
    }
}
