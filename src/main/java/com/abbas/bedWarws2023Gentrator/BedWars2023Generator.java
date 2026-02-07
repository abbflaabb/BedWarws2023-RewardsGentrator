package com.abbas.bedWarws2023Gentrator;

import com.abbas.bedWarws2023Gentrator.Manager.LuckyBoostManager;
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
    private LuckyBoostManager luckyBoostManager;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("BedWars2023") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "BedWars2023 plugin not found! Disabling BedWarsRewardsGenerator.");
            Bukkit.getPluginManager().disablePlugin(this);

        } else {
            api = (BedWars) ((RegisteredServiceProvider<?>) Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(BedWars.class))).getProvider();
            this.config = this.getConfig();
            this.saveDefaultConfig();
            this.luckyBoostManager = new LuckyBoostManager(this);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BedWars2023 plugin found! Enabling BedWarsRewardsGenerator.");

            registerEvents();

        }
    }
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new RewardsGenerator(this), this);
    }

    public void giveXp(Player player, int amount) {
        if (api != null) {
            Level playerLevel = api.getLevelsUtil();
            if (playerLevel != null) {
                // Apply lucky boost multiplier
                double multiplier = luckyBoostManager.getMultiplier(player);
                int finalAmount = (int) Math.round(amount * multiplier);

                playerLevel.addXp(player, finalAmount, PlayerXpGainEvent.XpSource.OTHER);

                String messages = config.getString("messages.xp-gain");
                if (messages != null) {
                    // Add boost info if active
                    String boostInfo = "";
                    if (multiplier > 1.0) {
                        LuckyBoostManager.BoostType boostType = luckyBoostManager.getActiveBoostType(player);
                        if (boostType != null) {
                            boostInfo = " §7(" + boostType.getDisplayName() + "§7)";
                        }
                    }

                    messages = messages
                            .replace("{amount}", String.valueOf(finalAmount))
                            .replace("{base_amount}", String.valueOf(amount))
                            .replace("{multiplier}", String.format("%.1fx", multiplier))
                            .replace("{boost}", boostInfo)
                            .replace("{source}", PlayerXpGainEvent.XpSource.DIAMOND.name() + PlayerXpGainEvent.XpSource.EMERALD.name())
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
        if (luckyBoostManager != null) {
            luckyBoostManager.clearAllBoosts();
        }
        if (api != null) {
            api = null;
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "BedWars API reference cleared.");
        }
    }
    public LuckyBoostManager getLuckyBoostManager() {
        return luckyBoostManager;
    }

}
