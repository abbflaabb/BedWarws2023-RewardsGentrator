package com.abbas.bedWarws2023Gentrator.commands;

import com.abbas.bedWarws2023Gentrator.BedWars2023Generator;
import com.tomkeuper.bedwars.api.command.ParentCommand;
import com.tomkeuper.bedwars.api.command.SubCommand;
import com.tomkeuper.bedwars.arena.Misc;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StatsCommand extends SubCommand {
    private final BedWars2023Generator plugin;

    public StatsCommand(ParentCommand parent, String name, BedWars2023Generator plugin) {
        super(parent, name);
        this.plugin = plugin;
        showInList(true);
        setPermission("bwtest.test");
        setDisplayInfo(Misc.msgHoverClick("description", "hoveDescription","run this on click", ClickEvent.Action.RUN_COMMAND));
        setPriority(14);
        setArenaSetupCommand(false);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage(ChatColor.RED + "Only players can use this command!");
            return false;
        }

        Player p = (Player) s;

        if (args.length == 0) {
            showPlayerStats(p, p);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            s.sendMessage(ChatColor.RED + "Player " + args[0] + " not found!");
            return true; // FIX: Added return here
        }

        showPlayerStats(s, target);
        return true;
    }

    private void showPlayerStats(CommandSender viewer, Player target) {
        double multiplier = plugin.getLuckyBoostManager().getMultiplier(target);
        boolean hasActiveBoost = multiplier > 1.0;
        send(viewer, ChatColor.GOLD + "====== " + ChatColor.YELLOW + target.getName() + "'s Stats" + ChatColor.GOLD + " ======");
        send(viewer, ChatColor.GRAY + "Active Lucky Boost: " + (hasActiveBoost ? ChatColor.GREEN + "YES (" + String.format("%.1fx", multiplier) + ")" : ChatColor.RED + "NO"));
        send(viewer, ChatColor.GRAY + "Current Multiplier: " + ChatColor.YELLOW + String.format("%.1fx", multiplier));
        send(viewer, ChatColor.GOLD + "================================================");
    }
    private void send(CommandSender sender, String message) {
        sender.sendMessage(BedWars2023Generator.colorize(message));
    }
    @Override
    public List<String> getTabComplete() {
        return null;
    }
}
