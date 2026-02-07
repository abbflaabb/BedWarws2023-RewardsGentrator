package com.abbas.bedWarws2023Gentrator;

import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerGeneratorCollectEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerKillEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerLevelUpEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerXpGainEvent;
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
            if (plugin.getConfig().getBoolean("lucky-boost.enabled", true)) {
                if (plugin.getConfig().getBoolean("lucky-boost.on-game-start", true)) {
                    for (Player player : event.getArena().getPlayers()) {
                        plugin.getLuckyBoostManager().tryApplyLuckyBoost(player);
                    }
                }
            }
        }
    }
    /**
     * Reward for Collecting Items from Generators
     */
    @EventHandler
    public void onRewardGenerate(PlayerGeneratorCollectEvent event) {
        Material collectedItem = event.getItemStack().getType();
        if (plugin.getConfig().getBoolean("lucky-boost.enabled", true)) {
            if (plugin.getConfig().getBoolean("lucky-boost.on-resource-collect", false)) {
                plugin.getLuckyBoostManager().tryApplyLuckyBoost(event.getPlayer());
            }
        }
        if (collectedItem == Material.DIAMOND) {
            if (plugin.getConfig().getBoolean("xp-rewards.diamond-collect.enabled")) {
                plugin.giveXp(event.getPlayer(), plugin.getConfig().getInt("xp-rewards.diamond-collect.amount"));
            }
        } else if (collectedItem == Material.EMERALD) {
            if (plugin.getConfig().getBoolean("xp-rewards.emerald-collect.enabled")) {
                plugin.giveXp(event.getPlayer(), plugin.getConfig().getInt("xp-rewards.emerald-collect.amount"));
            }
        }
    }
    @EventHandler
    public void onPlayerKill(PlayerKillEvent event) {
        Player killer = event.getKiller();
        if (killer != null) {
            // Try to apply lucky boost on kill
            if (plugin.getConfig().getBoolean("lucky-boost.enabled", true)) {
                if (plugin.getConfig().getBoolean("lucky-boost.on-kill", false)) {
                    plugin.getLuckyBoostManager().tryApplyLuckyBoost(killer);
                }
            }
        }
    }
    /**
     * Reward for Level Up
     */
    @EventHandler
    public void onPlayerLevelUp(PlayerLevelUpEvent event) {
        Player player = event.getPlayer();
        int newLevel = event.getNewLevel();
        if (plugin.getConfig().getBoolean("lucky-boost.enabled", true)) {
            if (plugin.getConfig().getBoolean("lucky-boost.on-level-up", false)) {
                plugin.getLuckyBoostManager().tryApplyLuckyBoost(player);
            }
        }
        String message = plugin.getConfig().getString("messages.level-up");
        if (message != null) {
            message = message
                    .replace("{level}", String.valueOf(newLevel));
            player.sendTitle(BedWars2023Generator.colorize(message), "");
        }
    }
}
