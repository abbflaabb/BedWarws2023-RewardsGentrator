package com.abbas.bedWarws2023Gentrator.Manager;

import com.abbas.bedWarws2023Gentrator.BedWars2023Generator;
import com.abbas.bedWarws2023Gentrator.configuration.Messages;
import com.tomkeuper.bedwars.api.language.Language;
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
     */
    public boolean tryApplyLuckyBoost(Player player) {
        Language lang = Language.getPlayerLanguage(player);

        if (!lang.getBoolean(Messages.LUCKY_BOOST_ENABLED)) {
            return false;
        }

        if (hasActiveBoost(player)) {
            return false;
        }

        double chance = lang.getDouble(Messages.LUCKY_BOOST_CHANCE);
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
        BoostType boostType = getRandomBoostType(player);
        Language lang = Language.getPlayerLanguage(player);
        int duration = lang.getInt(Messages.LUCKY_BOOST_DURATION);

        ActiveBoost boost = new ActiveBoost(boostType, System.currentTimeMillis() + (duration * 1000L));
        activeBoosts.put(player.getUniqueId(), boost);

        sendBoostNotification(player, boostType, duration);

        if (lang.getBoolean(Messages.LUCKY_BOOST_SOUND)) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf("LEVEL_UP"), 1.0f, 1.5f);
            } catch (IllegalArgumentException e) {
                player.playSound(player.getLocation(), Sound.valueOf("ENTITY_PLAYER_LEVELUP"), 1.0f, 1.5f);
            }
        }

        scheduleBoostExpiration(player, duration);
    }

    /**
     * Get a random boost type based on weights from language system
     */
    private BoostType getRandomBoostType(Player player) {
        double rand = random.nextDouble() * 100;
        double cumulative = 0;

        Language lang = Language.getPlayerLanguage(player);

        for (BoostType type : BoostType.values()) {
            double weight = lang.getDouble(type.getWeightKey());
            cumulative += weight;
            if (rand < cumulative) {
                return type;
            }
        }

        return BoostType.DOUBLE;
    }

    /**
     * Send notification to player
     */
    private void sendBoostNotification(Player player, BoostType boostType, int duration) {
        Language lang = Language.getPlayerLanguage(player);

        String title = lang.m(boostType.getTitleKey());
        String subtitle = lang.m(boostType.getSubtitleKey());
        String chatMessage = lang.m(boostType.getChatKey());

        if (title != null && !title.isEmpty()) {
            player.sendTitle(
                    BedWars2023Generator.colorize(title),
                    BedWars2023Generator.colorize(subtitle.replace("{duration}", String.valueOf(duration)))
            );
        }

        if (chatMessage != null && !chatMessage.isEmpty()) {
            player.sendMessage(BedWars2023Generator.colorize(
                    chatMessage.replace("{duration}", String.valueOf(duration))
            ));
        }
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

                    Language lang = Language.getPlayerLanguage(player);
                    String expireMessage = lang.m(Messages.LUCKY_BOOST_EXPIRE);

                    if (expireMessage != null && !expireMessage.isEmpty()) {
                        player.sendMessage(BedWars2023Generator.colorize(expireMessage));
                    }

                    if (lang.getBoolean(Messages.LUCKY_BOOST_SOUND)) {
                        try {
                            player.playSound(player.getLocation(), Sound.valueOf("ITEM_BREAK"), 0.7f, 1.0f);
                        } catch (IllegalArgumentException e) {
                            player.playSound(player.getLocation(), Sound.valueOf("ENTITY_ITEM_BREAK"), 0.7f, 1.0f);
                        }
                    }
                }
            }
        }.runTaskLater(plugin, duration * 20L);
    }

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

    public double getMultiplier(Player player) {
        ActiveBoost boost = activeBoosts.get(player.getUniqueId());
        if (boost != null && System.currentTimeMillis() <= boost.expiresAt) {
            return boost.boostType.getMultiplier();
        }
        return 1.0;
    }

    public BoostType getActiveBoostType(Player player) {
        ActiveBoost boost = activeBoosts.get(player.getUniqueId());
        if (boost != null && System.currentTimeMillis() <= boost.expiresAt) {
            return boost.boostType;
        }
        return null;
    }

    public void clearAllBoosts() {
        activeBoosts.clear();
    }

    public void removeBoost(Player player) {
        activeBoosts.remove(player.getUniqueId());
    }

    public enum BoostType {
        DOUBLE(2.0, Messages.WEIGHT_DOUBLE, Messages.DOUBLE_TITLE, Messages.DOUBLE_SUBTITLE, Messages.DOUBLE_CHAT),
        TRIPLE(3.0, Messages.WEIGHT_TRIPLE, Messages.TRIPLE_TITLE, Messages.TRIPLE_SUBTITLE, Messages.TRIPLE_CHAT),
        MEGA(5.0, Messages.WEIGHT_MEGA, Messages.MEGA_TITLE, Messages.MEGA_SUBTITLE, Messages.MEGA_CHAT),
        ULTIMATE(10.0, Messages.WEIGHT_ULTIMATE, Messages.ULTIMATE_TITLE, Messages.ULTIMATE_SUBTITLE, Messages.ULTIMATE_CHAT);

        private final double multiplier;
        private final String weightKey;
        private final String titleKey;
        private final String subtitleKey;
        private final String chatKey;

        BoostType(double multiplier, String weightKey, String titleKey, String subtitleKey, String chatKey) {
            this.multiplier = multiplier;
            this.weightKey = weightKey;
            this.titleKey = titleKey;
            this.subtitleKey = subtitleKey;
            this.chatKey = chatKey;
        }

        public double getMultiplier() {
            return multiplier;
        }

        public String getWeightKey() {
            return weightKey;
        }

        public String getTitleKey() {
            return titleKey;
        }

        public String getSubtitleKey() {
            return subtitleKey;
        }

        public String getChatKey() {
            return chatKey;
        }

        public String getDisplayName() {
            return name().replace("_", " ");
        }
    }

    private static class ActiveBoost {
        private final BoostType boostType;
        private final long expiresAt;

        public ActiveBoost(BoostType boostType, long expiresAt) {
            this.boostType = boostType;
            this.expiresAt = expiresAt;
        }
    }
}