package net.unserversinley.etfunfreezer;

import net.minecraft.world.entity.EntityInsentient;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class MC1_17_R1 implements Listener {

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        for(Entity ent : event.getChunk().getEntities()) {
            net.minecraft.world.entity.Entity enthandle = ((CraftEntity)ent).getHandle();
            if(enthandle instanceof EntityInsentient) {
                if(!((EntityInsentient)enthandle).aware) {
                    ((EntityInsentient)enthandle).aware = true;
                }
            }
        }
    }

    public static void AIEntitiesArea(String area_string, Player player, Player sender) {
        double area = Double.parseDouble(area_string);
        int num = player.getNearbyEntities(area, area, area).size();
        int num_tick = 0;
        for(Entity ent : player.getNearbyEntities(area, area, area)) {
            net.minecraft.world.entity.Entity enthandle = ((CraftEntity)ent).getHandle();
            if(enthandle instanceof EntityInsentient) {
                if(!((EntityInsentient)enthandle).aware) {
                    num_tick = num_tick + 1;
                    ((EntityInsentient)enthandle).aware = true;
                }
            }
        }
        sender.sendMessage("§a[ETFU Area Unfreezer] Detected " + num + " entities.");
        sender.sendMessage("§a[ETFU Area Unfreezer] " + num_tick + " had no AI, but now have.");
    }

    public static void DisableAIEntitiesArea(String area_string, Player player) {
        double area = Double.parseDouble(area_string);
        int num = player.getNearbyEntities(area, area, area).size();
        int num_tick = 0;
        for(Entity ent : player.getNearbyEntities(area, area, area)) {
            net.minecraft.world.entity.Entity enthandle = ((CraftEntity)ent).getHandle();
            if(enthandle instanceof EntityInsentient) {
                if(((EntityInsentient)enthandle).aware) {
                    num_tick = num_tick + 1;
                    ((EntityInsentient)enthandle).aware = false;
                }
            }
        }
        player.sendMessage("§a[ETFU AI Remover Tool] Detected " + num + " entities.");
        player.sendMessage("§a[ETFU AI Remover Tool] " + num_tick + " had AI, but now don't have.");
    }

    public static void AIAllPlayersNearEntities(Player sender) {
        int num = 0;
        int num_tick = 0;
        for(Player player : Bukkit.getOnlinePlayers()) {
            for (Entity ent : player.getNearbyEntities(300, 300, 300)) {
                net.minecraft.world.entity.Entity enthandle = ((CraftEntity) ent).getHandle();
                if (enthandle instanceof EntityInsentient) {
                    num = num + 1;
                    if (!((EntityInsentient) enthandle).aware) {
                        num_tick = num_tick + 1;
                        ((EntityInsentient) enthandle).aware = true;
                    }
                }
            }
        }
        sender.sendMessage("§a[ETFU All Players] Detected " + num + " entities near all players.");
        sender.sendMessage("§a[ETFU All Players] " + num_tick + " had no AI, but now have.");
    }
}
