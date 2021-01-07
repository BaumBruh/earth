package com.github.polarbehrz.earth;

import com.github.polarbehrz.earth.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Earth extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("map").setExecutor(new map());
        getCommand("mvp+").setExecutor(new mvpplus());
        getCommand("help").setExecutor(new help());
        getCommand("store").setExecutor(new store());
        getCommand("wiki").setExecutor(new wiki());
        getCommand("rules").setExecutor(new rules());
        getCommand("rules").setTabCompleter(new rules());
        getCommand("vip").setExecutor(new vip());
        getCommand("vip+").setExecutor(new vipplus());
        getCommand("mvp").setExecutor(new mvp());
        getCommand("nightvision").setExecutor(new nightvision());
        getLogger().info("Fully loaded.");
    }
}
