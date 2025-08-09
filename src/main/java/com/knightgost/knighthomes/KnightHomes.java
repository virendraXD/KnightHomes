package com.knightgost.knighthomes;

import org.bukkit.plugin.java.JavaPlugin;

public class KnightHomes extends JavaPlugin {

    private static KnightHomes instance;

    @Override
    public void onEnable() {
        getLogger().info("KnightHomes is now enabled!");

        saveDefaultConfig(); // Copies config.yml from resources to plugin folder if not exists
        getLogger().info("KnightHomes loaded with configuration.");
        
        getCommand("home").setExecutor(new HomeCommand(this));
        
        instance = this;
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new TeleportUtils(), this);
    }

    public static KnightHomes getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        getLogger().info("KnightHomes is now disabled.");
    }
}
