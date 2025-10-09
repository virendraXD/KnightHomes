package com.knightgost.knighthomes;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TeleportUtils implements Listener {
    private static final Set<UUID> teleportingPlayers = new HashSet<>();

    public static void teleportWithCountdown(Player player, Location location, String successMessage, JavaPlugin plugin) {
        UUID uuid = player.getUniqueId();

        int cooldownTimeMinutes = plugin.getConfig().getInt("cooldown.time", 1); // default 1 minute
        int cooldownTimeSeconds = cooldownTimeMinutes * 60; // convert to seconds

        boolean cooldownEnabled = plugin.getConfig().getBoolean("cooldown.enabled", true);
        String bypassPermission = plugin.getConfig().getString("cooldown.bypass-permission", "knighthomes.cooldown.bypass");

        if (cooldownEnabled && !player.hasPermission(bypassPermission)) {
            if (CooldownManager.isOnCooldown(uuid, cooldownTimeSeconds)) {
                int remaining = CooldownManager.getRemaining(uuid, cooldownTimeSeconds);
                player.sendMessage(MessageUtils.getColoredIconMessage(plugin,
                    "teleport_cooldown", "§cPlease wait " + remaining/60 + "m " + (remaining%60) + "s before teleporting again!"));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 0.8f);
                return;
            }
            CooldownManager.setCooldown(uuid);
        }

        teleportingPlayers.add(uuid);

        Location startLocation = player.getLocation().clone();

        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                if (!teleportingPlayers.contains(uuid)) {
                    player.sendMessage(
                        MessageUtils.getColoredIconMessage(plugin, "teleport_cancelled", "&cTeleport cancelled because you moved.")
                    );
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                    this.cancel();
                    return;
                }

                Location current = player.getLocation();
                if (hasMoved(startLocation, current)) {
                    teleportingPlayers.remove(uuid);
                    player.sendMessage(
                        MessageUtils.getColoredIconMessage(plugin, "teleport_cancelled", "&cTeleport cancelled because you moved.")
                    );
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                    this.cancel();
                    return;
                }

                if (countdown > 0) {
                    player.sendActionBar(
                        MessageUtils.getColoredIconMessage(plugin, "teleporting", "§7(Do not move) §eTeleporting in " + countdown + "...")
                    );
                    countdown--;
                } else {
                    teleportingPlayers.remove(uuid);
                    player.teleport(location);
                    player.sendMessage(successMessage);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private static boolean hasMoved(Location from, Location to) {
        return from.getBlockX() != to.getBlockX()
                || from.getBlockY() != to.getBlockY()
                || from.getBlockZ() != to.getBlockZ();
    }
}
