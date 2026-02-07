package com.abbas.bedWarws2023Gentrator.Manager;

import com.abbas.bedWarws2023Gentrator.BedWars2023Generator;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class LuckyBoostManager {
    private final BedWars2023Generator plugin;
    private final Map<UUID, ActiveBoost> activeBoosts;
    private final Random random;

    public LuckyBoostManager(BedWars2023Generator plugin) {
        this.plugin = plugin;
        this.activeBoosts = new HashMap<>();
        this.random = new Random();
    }

    /**
     * Check if player should receive a lucky boost
     * @param player The player to check
     * @return true if boost was applied
     */
    public boolean tryApplyLuckyBoost(Player player) {
        // Don't apply if player already has an active boost
        if (hasActiveBoost(player)) {
            return false;
        }

        double chance = plugin.getConfig().getDouble("lucky-boost.chance", 0.05); // 5% default

        if (random.nextDouble() < chance) {
            applyRandomBoost(player);
            return true;
        }

        return false;
    }

    /**
     * Apply a random boost to the player
     */
    private void applyRandomBoost(Player player) {
        BoostType boostType = getRandomBoostType();
        int duration = plugin.getConfig().getInt("lucky-boost.duration", 60); // 60 seconds default

        ActiveBoost boost = new ActiveBoost(boostType, System.currentTimeMillis() + (duration * 1000L));
        activeBoosts.put(player.getUniqueId(), boost);

        // Send visual notification
        sendBoostNotification(player, boostType, duration);

        // Play sound effect
        if (plugin.getConfig().getBoolean("lucky-boost.sound-enabled", true)) {
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.5f);
        }

        // Schedule boost expiration
        scheduleBoostExpiration(player, duration);
    }

    /**
     * Get a random boost type based on configured weights
     */
    private BoostType getRandomBoostType() {
        double rand = random.nextDouble() * 100;
        double cumulative = 0;

        // Check each boost type with its weight
        for (BoostType type : BoostType.values()) {
            double weight = plugin.getConfig().getDouble("lucky-boost.types." + type.getConfigKey() + ".weight", 25.0);
            cumulative += weight;
            if (rand < cumulative) {
                return type;
            }
        }

        return BoostType.DOUBLE; // Fallback
    }

    /**
     * Send title and subtitle notification to player
     */
    private void sendBoostNotification(Player player, BoostType boostType, int duration) {
        String title = plugin.getConfig().getString("lucky-boost.types." + boostType.getConfigKey() + ".title",
                "§6§lYOU GOT LUCKY!");
        String subtitle = plugin.getConfig().getString("lucky-boost.types." + boostType.getConfigKey() + ".subtitle",
                "§bYou will receive " + boostType.getDisplayName() + " §bthis game!");

        title = BedWars2023Generator.colorize(title);
        subtitle = BedWars2023Generator.colorize(subtitle.replace("{duration}", String.valueOf(duration)));

        player.sendTitle(title, subtitle);

        // Also send chat message
        String chatMessage = plugin.getConfig().getString("lucky-boost.types." + boostType.getConfigKey() + ".chat-message",
                "§a§l✦ §6Lucky Boost! §a§l✦ §7You received " + boostType.getDisplayName() + " §7for §e" + duration + "s§7!");
        player.sendMessage(BedWars2023Generator.colorize(chatMessage.replace("{duration}", String.valueOf(duration))));
    }

    /**
     * Schedule the boost to expire after duration
     */
    private void scheduleBoostExpiration(Player player, int duration) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (activeBoosts.containsKey(player.getUniqueId())) {
                    activeBoosts.remove(player.getUniqueId());

                    String expireMessage = plugin.getConfig().getString("lucky-boost.expire-message",
                            "§c§l✦ §6Your Lucky Boost has expired! §c§l✦");
                    player.sendMessage(BedWars2023Generator.colorize(expireMessage));

                    if (plugin.getConfig().getBoolean("lucky-boost.sound-enabled", true)) {
                        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 0.7f, 1.0f);
                    }
                }
            }
        }.runTaskLater(plugin, duration * 20L); // Convert seconds to ticks
    }

    /**
     * Check if player has an active boost
     */
    public boolean hasActiveBoost(Player player) {
        ActiveBoost boost = activeBoosts.get(player.getUniqueId());
        if (boost != null) {
            if (System.currentTimeMillis() > boost.expiresAt) {
                activeBoosts.remove(player.getUniqueId());
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Get the multiplier for a player's current boost
     */
    public double getMultiplier(Player player) {
        ActiveBoost boost = activeBoosts.get(player.getUniqueId());
        if (boost != null && System.currentTimeMillis() <= boost.expiresAt) {
            return boost.boostType.getMultiplier();
        }
        return 1.0; // No boost = 1x multiplier
    }

    /**
     * Get the active boost type for a player
     */
    public BoostType getActiveBoostType(Player player) {
        ActiveBoost boost = activeBoosts.get(player.getUniqueId());
        if (boost != null && System.currentTimeMillis() <= boost.expiresAt) {
            return boost.boostType;
        }
        return null;
    }

    /**
     * Clear all active boosts (used when plugin disables or game ends)
     */
    public void clearAllBoosts() {
        activeBoosts.clear();
    }

    /**
     * Remove boost from a specific player
     */
    public void removeBoost(Player player) {
        activeBoosts.remove(player.getUniqueId());
    }

    /**
     * Enum for different boost types
     */
    public enum BoostType {
        DOUBLE(2.0, "§b§lDOUBLE EXP", "double-exp"),
        TRIPLE(3.0, "§d§lTRIPLE EXP", "triple-exp"),
        MEGA(5.0, "§6§lMEGA EXP", "mega-exp"),
        ULTIMATE(10.0, "§c§lULTIMATE EXP", "ultimate-exp");

        private final double multiplier;
        private final String displayName;
        private final String configKey;

        BoostType(double multiplier, String displayName, String configKey) {
            this.multiplier = multiplier;
            this.displayName = displayName;
            this.configKey = configKey;
        }

        public double getMultiplier() {
            return multiplier;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getConfigKey() {
            return configKey;
        }
    }

    /**
     * Class to store active boost information
     */
    private static class ActiveBoost {
        private final BoostType boostType;
        private final long expiresAt;

        public ActiveBoost(BoostType boostType, long expiresAt) {
            this.boostType = boostType;
            this.expiresAt = expiresAt;
        }
    }
}