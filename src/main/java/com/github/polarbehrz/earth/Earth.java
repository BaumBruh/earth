package com.github.polarbehrz.earth;

import com.github.polarbehrz.earth.announcer.*;
import com.github.polarbehrz.earth.commands.*;
import com.github.polarbehrz.earth.listeners.*;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Earth extends JavaPlugin {

    private static Earth plugin;
    private static Economy econ = null;

    long expirationTime = 360L;
    long saveInterval = 0L;
    AltData altData;
    AltListener altListener;

    public List<String> colors = new ArrayList<>();
    public List<String> hex = new ArrayList<>();
    public List<String> blacklist = new ArrayList<>();
    public List<String> cmdc = new ArrayList<>();
    public List<String> getColors() { return this.colors; }
    public List<String> getHex() { return this.hex; }
    public List<String> getBlacklist() { return this.blacklist; }

    @Override
    public void onEnable() {

        plugin = this;

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerAnvil(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new EntityExplode(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getLogger().info("Loaded events.");

        getCommand("map").setExecutor(new MapCommand());
        getCommand("mvp+").setExecutor(new MVPPlusCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("wiki").setExecutor(new WikiCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("rules").setTabCompleter(new RulesCommand());
        getCommand("vip").setExecutor(new VIPCommand());
        getCommand("vip+").setExecutor(new VIPPlusCommand());
        getCommand("mvp").setExecutor(new MVPCommand());
        getCommand("nightvision").setExecutor(new NightVisionCommand());
        getCommand("suffix").setExecutor(new SuffixCommand());
        getCommand("suffix").setTabCompleter(new SuffixCommand());
        getCommand("prefix").setExecutor(new PrefixCommand());
        getCommand("prefix").setTabCompleter(new PrefixCommand());
        getLogger().info("Loaded commands.");

        BukkitTask sell = new StoreAnnouncement(this).runTaskTimer(this,6000l,42000l);
        BukkitTask discord = new DiscordAnnouncement(this).runTaskTimer(this,12000l,42000l);
        BukkitTask map = new MapAnnouncement(this).runTaskTimer(this,18000l,42000l);
        BukkitTask vote = new VoteAnnouncement(this).runTaskTimer(this,24000l,42000l);
        BukkitTask radio = new RadioAnnouncement(this).runTaskTimer(this,30000l,42000l);
     //   BukkitTask company = new CompanyAnnouncement(this).runTaskTimer(this,30000l,42000l);
        BukkitTask wiki = new WikiAnnouncement(this).runTaskTimer(this,36000l,42000l);
        BukkitTask bounty = new BountyAnnouncement(this).runTaskTimer(this,42000l,42000l);
        getLogger().info("Loaded announcements.");

        setupPAPI();
        setupAlts();
        setupAnvil();
        if (!setupEconomy()) {
            getLogger().severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("Fully loaded.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down...");
        if (this.saveInterval > 0L)
            this.altData.saveIpDataConfig();
    }

    public static Earth getPlugin() {
        return plugin;
    }

    private boolean setupPAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new EarthExpansion(this).register();
            getLogger().info("PlaceholderAPI enabled.");
            return true;
        } else {
            getLogger().info("PlaceholderAPI not found.");
            return false;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    private void setupAlts() {
        getCommand("alt").setExecutor(new AltCommand(this));
        saveDefaultConfig();
        this.altData = new AltData(this);
        this.altData.reloadIpDataConfig();
        int entriesRemoved = this.altData.purge();
        this.altData.saveIpDataConfig();
        getLogger().info(entriesRemoved + " record" + ((entriesRemoved == 1) ? "" : "s") + " removed, expiration time " + this.expirationTime + " days.");
        this.altListener = new AltListener(this);
        getLogger().info("Sucessfully loaded alts.");
    }

    public boolean Matches(String text, String regex, String regex2) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(text);
        return !(!matcher.find(1) && !matcher2.find(1));
    }

    private void setupAnvil() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        for (String s : getConfig().getConfigurationSection("Colors").getKeys(false))
            this.colors.add(s);
        int i;
        for (i = 0; i < this.colors.size(); i++)
            this.hex.add(getConfig().getString("Colors." + (String)this.colors.get(i)));
        for (String p : getConfig().getConfigurationSection("Blacklist").getKeys(false))
            this.blacklist.add(p.toUpperCase());
        for (i = 0; i < this.colors.size(); i++)
            this.cmdc.add(ChatColor.of("#" + getConfig().getString("Colors." + (String)this.colors.get(i))) + (String)this.colors.get(i));
        this.cmdc.add(String.valueOf(ChatColor.WHITE));
    }
}
