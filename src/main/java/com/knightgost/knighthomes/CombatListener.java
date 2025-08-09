package com.knightgost.knighthomes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim) {
            CombatManager.tagCombat(victim);
        }

        if (event.getDamager() instanceof Player attacker) {
            CombatManager.tagCombat(attacker);
        }
    }
}
