package com.abbas.bedWarws2023Gentrator.Manager;

import com.abbas.bedWarws2023Gentrator.BedWars2023Generator;
import com.abbas.bedWarws2023Gentrator.configuration.ConfigPath;
import com.abbas.bedWarws2023Gentrator.configuration.Messages;
import com.tomkeuper.bedwars.api.configuration.ConfigManager;
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
    private final Map<UUID, Double> activeBoosts = new HashMap<>();
    private final Random random = new Random();

    public LuckyBoostManager(BedWars2023Generator plugin) {
        this.plugin = plugin;
    }

    public void tryApplyLuckyBoost(Player player) {
        ConfigManager config = plugin.getBedWarsConfigManager();
        if (!config.getBoolean(ConfigPath.LUCKY_BOOST_ENABLED)) return;

        double chance = config.getDouble(ConfigPath.LUCKY_BOOST_CHANCE);
        if (random.nextDouble() > chance) return;

        applyRandomBoost(player);
    }

    private void applyRandomBoost(Player player) {
        ConfigManager config = plugin.getBedWarsConfigManager();

        double weightDouble = config.getDouble(ConfigPath.WEIGHT_DOUBLE);
        double weightTriple = config.getDouble(ConfigPath.WEIGHT_TRIPLE);
        double weightMega = config.getDouble(ConfigPath.WEIGHT_MEGA);
        double weightUltimate = config.getDouble(ConfigPath.WEIGHT_ULTIMATE);
        double totalWeight = weightDouble + weightTriple + weightMega + weightUltimate;

        double r = random.nextDouble() * totalWeight;
        double multiplier;
        String title, subtitle, chat;

        if (r < weightDouble) {
            multiplier = 2.0;
            title = Language.getMsg(player, Messages.DOUBLE_TITLE);
            subtitle = Language.getMsg(player, Messages.DOUBLE_SUBTITLE);
            chat = Language.getMsg(player, Messages.DOUBLE_CHAT);
        } else if (r < weightDouble + weightTriple) {
            multiplier = 3.0;
            title = Language.getMsg(player, Messages.TRIPLE_TITLE);
            subtitle = Language.getMsg(player, Messages.TRIPLE_SUBTITLE);
            chat = Language.getMsg(player, Messages.TRIPLE_CHAT);
        } else if (r < weightDouble + weightTriple + weightMega) {
            multiplier = 5.0;
            title = Language.getMsg(player, Messages.MEGA_TITLE);
            subtitle = Language.getMsg(player, Messages.MEGA_SUBTITLE);
            chat = Language.getMsg(player, Messages.MEGA_CHAT);
        } else {
            multiplier = 10.0;
            title = Language.getMsg(player, Messages.ULTIMATE_TITLE);
            subtitle = Language.getMsg(player, Messages.ULTIMATE_SUBTITLE);
            chat = Language.getMsg(player, Messages.ULTIMATE_CHAT);
        }

        activeBoosts.put(player.getUniqueId(), multiplier);

        int duration = config.getInt(ConfigPath.LUCKY_BOOST_DURATION);

        if (title != null) player.sendTitle(BedWars2023Generator.colorize(title), BedWars2023Generator.colorize(subtitle != null ? subtitle : ""));
        if (chat != null) player.sendMessage(BedWars2023Generator.colorize(chat.replace("{duration}", String.valueOf(duration))));

        if (config.getBoolean(ConfigPath.LUCKY_BOOST_SOUND)) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf("ENTITY_PLAYER_LEVELUP"), 1f, 1f);
            } catch (Exception ignored) {
                try {
                    player.playSound(player.getLocation(), Sound.valueOf("LEVEL_UP"), 1f, 1f);
                } catch (Exception ignored2) {}
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                activeBoosts.remove(player.getUniqueId());
                if (player.isOnline()) {
                    String expireMsg = Language.getMsg(player, Messages.LUCKY_BOOST_EXPIRE);
                    if (expireMsg != null) player.sendMessage(BedWars2023Generator.colorize(expireMsg));
                }
            }
        }.runTaskLater(plugin, duration * 20L);
    }

    public double getMultiplier(Player player) {
        return activeBoosts.getOrDefault(player.getUniqueId(), 1.0);
    }

    public void clearAllBoosts() {
        activeBoosts.clear();
    }
}
