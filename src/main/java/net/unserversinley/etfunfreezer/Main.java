package net.unserversinley.etfunfreezer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    public static String getNMSVersion() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new MC1_18_1_R1(), this);
        Bukkit.getLogger().info("[ETFUnfreezer] Enabled successfully, detected version " + getNMSVersion());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            if (!Objects.requireNonNull(((Player) commandSender).getPlayer()).hasPermission("etfunfreezer.commands")) {
                commandSender.sendMessage(ChatColor.RED + "[ETFU] You don't have permission to use that command.");
                return true;
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "[ETFU] Only for players!");
            return true;
        }

        if (args.length == 0) {
            commandSender.sendMessage("§8-----------------------------------------------------");
            commandSender.sendMessage("§r[§aETFUnfreezer§r] Available options.");
            commandSender.sendMessage("§b/etfu §3unfreeze §7<area/player> <radius/playername/all>");
            commandSender.sendMessage("§b/etfu §3freeze area§7 <area>");
            commandSender.sendMessage("§8-----------------------------------------------------");
            return true;
        }

        if (args[0].equalsIgnoreCase("unfreeze")) {
            if (args.length == 1) {
                commandSender.sendMessage(ChatColor.RED + "[ETFU] Add the type of unfreeze to perform.");
                commandSender.sendMessage(ChatColor.RED + "[ETFU] You can use 'area' to unfreeze entities in a determined radius. (Ex: /etfu unfreeze area 30)");
                commandSender.sendMessage(ChatColor.RED + "[ETFU] Or you can use 'player' to unfreeze entities near to a player or all players. (/etfu unfreeze player <username/all>)");
                return true;
            }

            if (args[1].equalsIgnoreCase("area")) {
                if (args.length == 2) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] Please, add the radius in which you want to unfreeze entities. (Ex: /etfu unfreeze area 30)");
                    return true;
                }

                try {
                    Integer.parseInt(args[2]);
                } catch (NumberFormatException nfe) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] That's not a number or has decimals!");
                    return true;
                }

                if (Integer.parseInt(args[2]) > 3000) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] That's too big!");
                    return true;
                }

                MC1_18_1_R1.AIEntitiesArea(args[2], Objects.requireNonNull(((Player) commandSender).getPlayer()), Objects.requireNonNull(((Player) commandSender).getPlayer()));
                return true;
            }

            if (args[1].equalsIgnoreCase("player")) {
                if (args.length == 2) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] Add the name of the player you want the surrounding entities to have their AI fixed.");
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] Write 'all' to do the process for all the online players.");
                    return true;
                }
                if (args[2].equalsIgnoreCase("all") || args[2].equalsIgnoreCase("*")) {
                    MC1_18_1_R1.AIAllPlayersNearEntities(((Player) commandSender).getPlayer());
                    return true;
                }
                Player player = Bukkit.getPlayerExact(args[2]);
                if (player == null) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] Player not found or offline.");
                    return true;
                }

                MC1_18_1_R1.AIEntitiesArea("300", player, Objects.requireNonNull(((Player) commandSender).getPlayer()));
                return true;
            }

            commandSender.sendMessage(ChatColor.RED + "[ETFU] Invalid argument. Use 'area' or 'player'.");
            return true;
        }

        if (args[0].equalsIgnoreCase("freeze")) {
            if (args.length == 1) {
                commandSender.sendMessage(ChatColor.RED + "[ETFU] Add the type of freeze to perform.");
                commandSender.sendMessage(ChatColor.RED + "[ETFU] You can use 'area' to freeze entities in a determined radius. (Ex: /etfu freeze area 30)");
                return true;
            }

            if (args[1].equalsIgnoreCase("area")) {
                if (args.length == 2) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] Please, add the radius in which you want to freeze entities. (Ex: /etfu freeze area 30)");
                    return true;
                }

                try {
                    Integer.parseInt(args[2]);
                } catch (NumberFormatException nfe) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] That's not a number or has decimals!");
                    return true;
                }

                if (Integer.parseInt(args[2]) > 3000) {
                    commandSender.sendMessage(ChatColor.RED + "[ETFU] That's too big!");
                    return true;
                }

                MC1_18_1_R1.DisableAIEntitiesArea(args[2], Objects.requireNonNull(((Player) commandSender).getPlayer()));
                return true;
            }
            commandSender.sendMessage(ChatColor.RED + "[ETFU] Invalid argument. Use '/etfu freeze area'.");
            return true;
        }

        commandSender.sendMessage("§8-----------------------------------------------------");
        commandSender.sendMessage("§r[§aETFUnfreezer§r] Unknown command. Available options.");
        commandSender.sendMessage("§b/etfu §3unfreeze §7<area/player> <radius/playername/all>");
        commandSender.sendMessage("§b/etfu §3freeze area§7 <area>");
        commandSender.sendMessage("§8-----------------------------------------------------");
        return true;
    }
}
