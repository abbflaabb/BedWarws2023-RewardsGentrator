package com.abbas.bedWarws2023Gentrator.Manager;

import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AFKManager {
    public static Map<UUID, BukkitTask> AFKTask = new HashMap<>();
}
