package com.abbas.bedWarws2023Gentrator;

import com.tomkeuper.bedwars.api.events.player.PlayerGeneratorCollectEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerLevelUpEvent;
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
    public void onRewardGenerate(PlayerGeneratorCollectEvent event) {
        Material collectedItem = event.getItemStack().getType();
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
    public void onPlayerLevelUp(PlayerLevelUpEvent event) {
        Player player = event.getPlayer();
        int newLevel = event.getNewLevel();
        String message = plugin.getConfig().getString("messages.level-up");
        if (message != null) {
            message = message
                    .replace("{level}", String.valueOf(newLevel));
            player.sendTitle(BedWars2023Generator.colorize(message), "");
        }
    }
}
