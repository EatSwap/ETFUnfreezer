package net.unserversinley.etfunfreezer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class MC1_18_1_R1 implements Listener {

    public static void AIEntitiesArea(String area_string, Player player, Player sender) {
        final double area = Double.parseDouble(area_string);
        final int num = player.getNearbyEntities(area, area, area).size();
        int num_tick = 0;
        for (Entity e : player.getNearbyEntities(area, area, area)) {
            if (e instanceof Mob) {
                if (!((Mob) e).isAware()) {
                    ((Mob) e).setAware(true);
                    ++num_tick;
                }
            }
        }
        sender.sendMessage("§a[ETFU Area Unfreezer] Detected " + num + " entities.");
        sender.sendMessage("§a[ETFU Area Unfreezer] " + num_tick + " had no AI, but now have.");
    }

    public static void DisableAIEntitiesArea(String area_string, Player player) {
        final double area = Double.parseDouble(area_string);
        final int num = player.getNearbyEntities(area, area, area).size();
        int num_tick = 0;
        for (Entity e : player.getNearbyEntities(area, area, area)) {
            if (e instanceof Mob) {
                if (!((Mob) e).isAware()) {
                    ((Mob) e).setAware(true);
                    ++num_tick;
                }
            }
        }
        player.sendMessage("§a[ETFU AI Remover Tool] Detected " + num + " entities.");
        player.sendMessage("§a[ETFU AI Remover Tool] " + num_tick + " had AI, but now don't have.");
    }

    public static void AIAllPlayersNearEntities(Player sender) {
        int num = 0, num_tick = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Entity e : player.getNearbyEntities(300, 300, 300)) {
                if (e instanceof Mob) {
                    ++num;
                    if (!((Mob) e).isAware()) {
                        ((Mob) e).setAware(true);
                        ++num_tick;
                    }
                }
            }
        }
        sender.sendMessage("§a[ETFU All Players] Detected " + num + " entities near all players.");
        sender.sendMessage("§a[ETFU All Players] " + num_tick + " had no AI, but now have.");
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        for (Entity e : event.getChunk().getEntities()) {
            if (e instanceof Mob) {
                ((Mob) e).setAware(true);
            }
        }
    }
}
