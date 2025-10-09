package com.knightgost.knighthomes;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(UUID uuid, int cooldownSeconds) {
        if (!cooldowns.containsKey(uuid)) return false;
        long lastUse = cooldowns.get(uuid);
        return (System.currentTimeMillis() - lastUse) < (cooldownSeconds * 1000L);
    }

    public static int getRemaining(UUID uuid, int cooldownSeconds) {
        if (!cooldowns.containsKey(uuid)) return 0;
        long elapsed = System.currentTimeMillis() - cooldowns.get(uuid);
        int remaining = (int) ((cooldownSeconds * 1000L - elapsed) / 1000L);
        return Math.max(remaining, 0);
    }

    public static void setCooldown(UUID uuid) {
        cooldowns.put(uuid, System.currentTimeMillis());
    }
}
