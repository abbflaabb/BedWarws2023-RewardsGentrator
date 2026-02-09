package com.abbas.bedWarws2023Gentrator;

import com.abbas.bedWarws2023Gentrator.Manager.LuckyBoostManager;
import com.abbas.bedWarws2023Gentrator.configuration.ConfigPath;
import com.abbas.bedWarws2023Gentrator.configuration.Messages;
import com.tomkeuper.bedwars.api.BedWars;
import com.tomkeuper.bedwars.api.configuration.ConfigManager;
import com.tomkeuper.bedwars.api.events.player.PlayerXpGainEvent;
import com.tomkeuper.bedwars.api.language.Language;
import com.tomkeuper.bedwars.api.levels.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class BedWars2023Generator extends JavaPlugin {
    private static BedWars api;
    private LuckyBoostManager luckyBoostManager;
    private ConfigManager configManager;
    private String name;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("BedWars2023") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "BedWars2023 plugin not found! Disabling BedWarsRewardsGenerator.");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            api = (BedWars) ((RegisteredServiceProvider<?>) Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(BedWars.class))).getProvider();

            createDirectories();
            this.luckyBoostManager = new LuckyBoostManager(this);
            this.configManager = new ConfigManager(this,"config.yml", getPath());
            setupConfig();
            // Setup all settings and messages using the language system
            Messages.setupMessages();

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BedWars2023 plugin found! Enabling BedWarsRewardsGenerator.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "ConfigPath: " + getPath() + "/config.yml");
            registerEvents();
        }
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new RewardsGenerator(this), this);
    }

    public void giveXp(Player player, int amount, String source) {
        if (api != null) {
            Level playerLevel = api.getLevelsUtil();
            if (playerLevel != null) {
                double multiplier = luckyBoostManager.getMultiplier(player);
                int finalAmount = (int) Math.round(amount * multiplier);

                playerLevel.addXp(player, finalAmount, PlayerXpGainEvent.XpSource.OTHER);

                String message = Language.getMsg(player, Messages.XP_GAIN);
                if (message != null && !message.isEmpty()) {
                    message = message
                            .replace("{amount}", String.valueOf(finalAmount))
                            .replace("{base_amount}", String.valueOf(amount))
                            .replace("{multiplier}", String.format("%.1fx", multiplier))
                            .replace("{source}", source);
                    player.sendMessage(colorize(message));
                }
            }
        }
    }

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Create necessary directories for the addon if they don't exist
     * This ensures that the config file can be saved properly
     */
    private void createDirectories() {
        File configDir = new File(getPath());
        if (!configDir.exists()) {
            if (configDir.mkdirs()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Created directories for BedWars2023Generator addon.");
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Failed to create directories for BedWars2023Generator addon. Please check the error and report it to the developer.");
            }
        }
    }
    // for Setup the Main Config
    private void setupConfig() {
        YamlConfiguration yml = configManager.getYml();
        yml.addDefault(ConfigPath.XP_DIAMOND_ENABLED, true);
        yml.addDefault(ConfigPath.XP_DIAMOND_AMOUNT, 25);
        yml.addDefault(ConfigPath.XP_EMERALD_ENABLED, true);
        yml.addDefault(ConfigPath.XP_EMERALD_AMOUNT, 60);
        yml.addDefault(ConfigPath.LUCKY_BOOST_ENABLED, true);
        yml.addDefault(ConfigPath.LUCKY_BOOST_CHANCE, 0.05);
        yml.addDefault(ConfigPath.LUCKY_BOOST_DURATION, 120);// default 2 minutes
        yml.addDefault(ConfigPath.LUCKY_BOOST_SOUND, true);
        yml.addDefault(ConfigPath.LUCKY_ON_START, true);
        yml.addDefault(ConfigPath.LUCKY_ON_COLLECT, false);
        yml.addDefault(ConfigPath.LUCKY_ON_KILL, true);
        yml.addDefault(ConfigPath.LUCKY_ON_LEVEL, false);
        yml.addDefault(ConfigPath.WEIGHT_DOUBLE, 50.0);
        yml.addDefault(ConfigPath.WEIGHT_TRIPLE, 30.0);
        yml.addDefault(ConfigPath.WEIGHT_MEGA, 15.0);
        yml.addDefault(ConfigPath.WEIGHT_ULTIMATE, 5.0);
        yml.options().copyDefaults(true);
        configManager.save();
    }

    @Override
    public void onDisable() {
        if (luckyBoostManager != null) {
            luckyBoostManager.clearAllBoosts();
        }
        api = null;
    }

    public LuckyBoostManager getLuckyBoostManager() {
        return luckyBoostManager;
    }

    public static BedWars getApi() {
        return api;
    }
    public String getPath() {
        return "plugins/BedWars2023/Addons/BedWars2023Generator";
    }


    public ConfigManager getBedWarsConfigManager() {
        return configManager;
    }
}
