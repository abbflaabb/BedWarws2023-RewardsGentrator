package com.abbas.bedWarws2023Gentrator;

import com.abbas.bedWarws2023Gentrator.configuration.ConfigPath;
import com.abbas.bedWarws2023Gentrator.configuration.Messages;
import com.tomkeuper.bedwars.api.BedWars;
import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.arena.IArena;
import com.tomkeuper.bedwars.api.configuration.ConfigManager;
import com.tomkeuper.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.tomkeuper.bedwars.api.events.player.*;
import com.tomkeuper.bedwars.api.language.Language;
import com.tomkeuper.bedwars.api.levels.Level;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RewardsGenerator implements Listener {
    private final BedWars2023Generator plugin;
    private final Map<IArena, Boolean> firstBloodGiven = new HashMap();

    public RewardsGenerator(BedWars2023Generator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGameStart(@NotNull GameStateChangeEvent event) {
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
    public void onRewardGenerate(@NotNull PlayerGeneratorCollectEvent event) {
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
    public void onPlayerKill(@NotNull PlayerKillEvent event) {
        Player killer = event.getKiller();
        IArena arena = event.getArena();
        if (killer != null) {
            if (plugin.getBedWarsConfigManager().getBoolean(ConfigPath.LUCKY_ON_KILL)) {
                plugin.getLuckyBoostManager().tryApplyLuckyBoost(killer);
            }
        }
        // add first blood
        if (arena != null && !(Boolean) firstBloodGiven.getOrDefault(arena, false)) {
            if (!plugin.getBedWarsConfigManager().getBoolean(ConfigPath.XP_FIRST_BLOOD_ENABLE)) {
                return;
            }
            int fbXP = plugin.getBedWarsConfigManager().getInt(ConfigPath.XP_FIRST_BLOOD_AMOUNT);
            if (fbXP > 0) {
                Level levelHandler = BedWars2023Generator.getApi().getLevelsUtil();
                levelHandler.addXp(killer, fbXP, PlayerXpGainEvent.XpSource.valueOf(BedWars2023Generator.colorize("&6First &bBlood")));

//                BedWars2023Generator.colorize(killer.sendMessage(Language.getMsg(killer, Messages.FIRST_BLOOD_MESSAGE)
//                        .replace("{amount}", String.valueOf(fbXP))));

                killer.sendMessage(BedWars2023Generator.colorize(Language.getMsg(killer, Messages.FIRST_BLOOD_MESSAGE
                        .replace("{amount}", String.valueOf(fbXP)))));// fixed message code
                firstBloodGiven.put(arena, true);
            }
        }
    }

    @EventHandler
    public void onPlayerLevelUp(@NotNull PlayerLevelUpEvent event) {
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

    // new reward system for first bed destroy
    @EventHandler
    public void onBedDestroy(PlayerBedBreakEvent event) {
        Player player = event.getPlayer();
        if (player != null) {
            if (!plugin.getBedWarsConfigManager().getBoolean(ConfigPath.XP_FIRST_BED_DESTROY_ENABLED)) {
                return;
            }
            Level levelHandler = BedWars2023Generator.getApi().getLevelsUtil();
            BedWars.IStats stats = BedWars2023Generator.getApi().getStatsUtil();
            if (stats.getPlayerBedsDestroyed(player.getUniqueId()) == 0) {
                int fistBedXp = plugin.getBedWarsConfigManager().getInt(ConfigPath.XP_FIRST_BED_DESTROY_AMOUNT);
                levelHandler.addXp(player, fistBedXp, PlayerXpGainEvent.XpSource.valueOf(BedWars2023Generator.colorize("&6First BedDestroy")));
                BedWars2023Generator.colorize(Language.getMsg(player, Messages.FIRST_BED_DESTROY))
                        .replace("{amount}", String.valueOf(fistBedXp));
            }
        }
    }
}
