package com.ankoki.skjade.listeners;

import com.ankoki.skjade.SkJade;
import com.ankoki.skjade.utils.Config;
import com.ankoki.skjade.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (Config.VERSION_ALERTS && (player.hasPermission("skjade.notify") || player.isOp()) && !SkJade.isLatest()) {
            player.sendMessage("§8[§6Sk§aJade§8] §cYou are running an outdated version of §6Sk§aJade§c!");
            TextComponent github =
                    new TextComponent(Utils.coloured("           &a[&cClick me to download the latest version!&a]"));
            github.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder("Click me to go to the latest release!")
                            .color(ChatColor.DARK_RED)
                            .italic(true)
                            .create()));
            github.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                    "https://www.github.com/Ankoki-Dev/SkJade/releases/latest"));
            player.spigot().sendMessage(github);
        }
    }
}
