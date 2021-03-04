package com.ankoki.skjade.utils;

public class CustomEnchantment {

    private final String name;
    private int rarity;
    private int maxLevel;
    private boolean tableAllowed;
    private ToolGroup[] acceptedTools;

    public CustomEnchantment(String name) {
        this.name = name;
    }

    public CustomEnchantment(String name, int rarity, int maxLevel, boolean tableAllowed, ToolGroup... acceptedTools) {
        this.name = name;
        this.rarity = Math.min(rarity, 100);
        this.maxLevel = maxLevel;
        this.tableAllowed = tableAllowed;
        this.acceptedTools = acceptedTools;
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

    public boolean isTableAllowed() {
        return tableAllowed;
    }

    public ToolGroup[] getAcceptedTools() {
        return acceptedTools;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setMaxLevel(int rarity) {
        this.maxLevel = Math.min(rarity, 100);
    }

    public void setTableAllowed(boolean tableAllowed) {
        this.tableAllowed = tableAllowed;
    }

    public void setAcceptedTools(ToolGroup[] acceptedTools) {
        this.acceptedTools = acceptedTools;
    }
}
