package com.knightgost.knighthomes;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatManager {

    private static final Map<UUID, Long> combatTimestamps = new HashMap<>();
    private static final Map<UUID, BukkitRunnable> combatTimers = new HashMap<>();
    private static final long COMBAT_DURATION = 10 * 1000; // 10 seconds in milliseconds

    public static void tagCombat(Player player) {
        UUID uuid = player.getUniqueId();
        boolean alreadyInCombat = isInCombat(uuid);

        // Update timestamp
        combatTimestamps.put(uuid, System.currentTimeMillis());

        // Cancel old timer if it exists
        if (combatTimers.containsKey(uuid)) {
            combatTimers.get(uuid).cancel();
        }

        // Start new timer
        BukkitRunnable timer = new BukkitRunnable() {
            @Override
            public void run() {
                combatTimestamps.remove(uuid);
                combatTimers.remove(uuid);
                player.sendMessage(MessageUtils.getColoredIconMessage(KnightHomes.getInstance(), "out_of_combat", "§aYou are no longer in combat."));
            }
        };
        timer.runTaskLater(KnightHomes.getInstance(), 200L); // 10s = 200 ticks
        combatTimers.put(uuid, timer);

        // Only notify if player just entered combat
        if (!alreadyInCombat) {
            player.sendMessage(MessageUtils.getColoredIconMessage(KnightHomes.getInstance(), "in_combat", "§cYou are now in combat!"));
        }
    }

    public static boolean isInCombat(UUID uuid) {
        Long lastTagged = combatTimestamps.get(uuid);
        return lastTagged != null && (System.currentTimeMillis() - lastTagged) < COMBAT_DURATION;
    }

    public static void untag(Player player) {
        UUID uuid = player.getUniqueId();
        BukkitRunnable task = combatTimers.remove(uuid);
        if (task != null) task.cancel();
        combatTimestamps.remove(uuid);
    }
}
