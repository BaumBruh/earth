package behrz.xylum;

import behrz.xylum.commands.*;
import behrz.xylum.listeners.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import behrz.xylum.announcer.*;
import org.bukkit.scheduler.BukkitTask;

public final class Earth extends JavaPlugin {

    private static Earth plugin;
    private static Economy econ = null;

    long expirationTime = 360L;
    long saveInterval = 0L;
    Config config;
    AltData altData;
    AltListener altListener;

    @Override
    public void onEnable() {

        plugin = this;

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new Riptide(), this);
        getServer().getPluginManager().registerEvents(new AnvilRepair(this), this);
        getServer().getPluginManager().registerEvents(new Riptide(), this);
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
        getCommand("color").setExecutor(new ColorCommand());
        getCommand("alt").setExecutor(new AltCommand(this));
        getLogger().info("Loaded commands.");

        BukkitTask sell = new StoreAnnouncement(this).runTaskTimer(this,6000l,42000l);
        BukkitTask discord = new DiscordAnnouncement(this).runTaskTimer(this,12000l,42000l);
        BukkitTask map = new MapAnnouncement(this).runTaskTimer(this,18000l,42000l);
        BukkitTask vote = new VoteAnnouncement(this).runTaskTimer(this,24000l,42000l);
        BukkitTask company = new CompanyAnnouncement(this).runTaskTimer(this,30000l,42000l);
        BukkitTask wiki = new WikiAnnouncement(this).runTaskTimer(this,36000l,42000l);
        BukkitTask bounty = new BountyAnnouncement(this).runTaskTimer(this,42000l,42000l);
        getLogger().info("Loaded announcements.");

        setupPAPI();
        if (!setupEconomy() ) {
            getLogger().severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.config = new Config();
        saveDefaultConfig();
        this.altData = new AltData(this);
        this.altData.reloadIpDataConfig();
        int entriesRemoved = this.altData.purge();
        this.altData.saveIpDataConfig();
        getLogger().info(entriesRemoved + " record" + ((entriesRemoved == 1) ? "" : "s") + " removed, expiration time " + this.expirationTime + " days.");
        this.altListener = new AltListener(this);
        getLogger().info("Sucessfully loaded alts.");

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

    public static boolean usingPermissions() {
        return true;
    }
    public static boolean usingPermissionsForNonNameChanges() {
        return true;
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
}
