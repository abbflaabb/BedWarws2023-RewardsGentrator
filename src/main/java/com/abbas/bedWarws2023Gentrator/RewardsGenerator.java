package com.abbas.bedWarws2023Gentrator;

import com.abbas.bedWarws2023Gentrator.configuration.ConfigPath;
import com.abbas.bedWarws2023Gentrator.configuration.Messages;
import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.configuration.ConfigManager;
import com.tomkeuper.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerGeneratorCollectEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerKillEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerLevelUpEvent;
import com.tomkeuper.bedwars.api.language.Language;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RewardsGenerator implements Listener {
    private final BedWars2023Generator plugin;

    public RewardsGenerator(BedWars2023Generator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGameStart(GameStateChangeEvent event) {
        if (event.getNewState() == GameState.playing) {
            ConfigManager config = plugin.getBedWarsConfigManager();
            if (config.getBoolean(ConfigPath.LUCKY_ON_START)) {
                for (Player player : event.getArena().getPlayers()) {
                    plugin.getLuckyBoostManager().tryApplyLuckyBoost(player);
                }
            }
        }
    }

    @EventHandler
    public void onRewardGenerate(PlayerGeneratorCollectEvent event) {
        Player player = event.getPlayer();
        ConfigManager config = plugin.getBedWarsConfigManager();
        Material collectedItem = event.getItemStack().getType();

        if (config.getBoolean(ConfigPath.LUCKY_ON_COLLECT)) {
            plugin.getLuckyBoostManager().tryApplyLuckyBoost(player);
        }

        if (collectedItem == Material.DIAMOND) {
            if (config.getBoolean(ConfigPath.XP_DIAMOND_ENABLED)) {
                plugin.giveXp(player, config.getInt(ConfigPath.XP_DIAMOND_AMOUNT), "Diamond");
            }
        } else if (collectedItem == Material.EMERALD) {
            if (config.getBoolean(ConfigPath.XP_EMERALD_ENABLED)) {
                plugin.giveXp(player, config.getInt(ConfigPath.XP_EMERALD_AMOUNT), "Emerald");
            }
        }
    }

    @EventHandler
    public void onPlayerKill(PlayerKillEvent event) {
        Player killer = event.getKiller();
        if (killer != null) {
            ConfigManager config = plugin.getBedWarsConfigManager();
            if (config.getBoolean(ConfigPath.LUCKY_ON_KILL)) {
                plugin.getLuckyBoostManager().tryApplyLuckyBoost(killer);
            }
        }
    }

    @EventHandler
    public void onPlayerLevelUp(PlayerLevelUpEvent event) {
        Player player = event.getPlayer();
        int newLevel = event.getNewLevel();
        ConfigManager config = plugin.getBedWarsConfigManager();

        if (config.getBoolean(ConfigPath.LUCKY_ON_LEVEL)) {
            plugin.getLuckyBoostManager().tryApplyLuckyBoost(player);
        }

        String message = Language.getMsg(player, Messages.LEVEL_UP);
        if (message != null && !message.isEmpty()) {
            message = message.replace("{level}", String.valueOf(newLevel));
            player.sendTitle(BedWars2023Generator.colorize(message), "");
        }
    }
}
