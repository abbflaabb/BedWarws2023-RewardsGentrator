package com.abbas.bedWarws2023Gentrator;

import com.abbas.bedWarws2023Gentrator.Manager.AFKManager;
import com.tomkeuper.bedwars.api.events.player.PlayerAfkEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

public class AFKListener implements Listener {
    private final BedWars2023Generator plugin;

    public AFKListener(BedWars2023Generator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAfk(PlayerAfkEvent event) {
        Player player = event.getPlayer();
        PlayerAfkEvent.AFKType type = event.getAfkType();

        if (type == PlayerAfkEvent.AFKType.START) {
            BukkitTask task = Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if (!player.isOnline()) return;
                player.kickPlayer(BedWars2023Generator.colorize("&cKick You because AFK 10M"));

            }, 20L * 60L * 10L);
            AFKManager.AFKTask.put(player.getUniqueId(), task);
        }
        if (type == PlayerAfkEvent.AFKType.END) {
            BukkitTask task = AFKManager.AFKTask.remove(player.getUniqueId());
            if (task != null) {
                task.cancel();
            }
        }
    }
}
