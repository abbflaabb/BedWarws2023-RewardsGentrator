package com.abbas.bedWarws2023Gentrator.Manager;

import com.tomkeuper.bedwars.api.addon.Addon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class AddonManager extends Addon {
    private final Plugin plugin;

    public AddonManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Abbas";
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public String getVersion() {
        return "1.0v";
    }

    @Override
    public String getName() {
        return "BedWars2023Generator";
    }

    @Override
    public String getDescription() {
        return "An addon that rewards players with XP for collecting resources in BedWars2023.";
    }

    @Override
    public void load() {
        Bukkit.getPluginManager().enablePlugin(plugin);
    }

    @Override
    public void unload() {
        Bukkit.getPluginManager().disablePlugin(plugin);
    }
}
