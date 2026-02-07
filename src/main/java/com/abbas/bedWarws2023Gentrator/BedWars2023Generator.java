package com.abbas.bedWarws2023Gentrator;

import com.tomkeuper.bedwars.api.BedWars;
import com.tomkeuper.bedwars.api.events.player.PlayerXpGainEvent;
import com.tomkeuper.bedwars.api.levels.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BedWars2023Generator extends JavaPlugin {
    private static BedWars api;
    private FileConfiguration config;
    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("BedWars2023") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "BedWars2023 plugin not found! Disabling BedWarsRewardsGenerator.");
            Bukkit.getPluginManager().disablePlugin(this);

        } else {
            api = (BedWars)((RegisteredServiceProvider<?>) Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(BedWars.class))).getProvider();
            this.config = this.getConfig();
            this.saveDefaultConfig();
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BedWars2023 plugin found! Enabling BedWarsRewardsGenerator.");
            // Register event listeners and commands here
            registerEvents();
        }
        // Plugin startup logic

    }
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new RewardsGenerator(this), this);
    }

    public void giveXp(Player player ,int amount) {
        if (api != null) {
            Level playerLevel = api.getLevelsUtil();
            if (playerLevel != null) {
                playerLevel.addXp(player, amount, PlayerXpGainEvent.XpSource.OTHER);
                String messages = config.getString("messages.xp-gain");
                if (messages != null) {
                    messages = messages
                            .replace("{amount}", String.valueOf(amount))
                            .replace("{source}", PlayerXpGainEvent.XpSource.OTHER.name())
                            .replace("_", " ")
                            .toLowerCase();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
                }
            }
        }
    }
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public void onDisable() {
        if (api != null) {
            api = null;
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "BedWars API reference cleared.");
        }
    }
}
