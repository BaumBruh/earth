package behrz.xylum;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;

public class AltData {
    private Earth plugin;

    private boolean saveData = false;

    private File ipDataFile = null;

    private FileConfiguration ipDataConfig = null;

    private static final String IP_FILE_NAME = "ipdata.yml";

    public AltData(Earth plugin) {
        this.plugin = plugin;
        if (plugin.saveInterval > 0L)
            Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)plugin, new Runnable() {
                        public void run() {
                            if (AltData.this.saveData)
                                AltData.this.saveIpDataConfig();
                        }
                    },  plugin.saveInterval * 60L * 20L,
                    plugin.saveInterval * 60L * 20L);
    }

    public void reloadIpDataConfig() {
        if (this.ipDataFile == null)
            this.ipDataFile = new File(this.plugin.getDataFolder(), "ipdata.yml");
        this.ipDataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.ipDataFile);
        try {
            Reader defConfigStream = new InputStreamReader(this.plugin.getResource("ipdata.yml"), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.ipDataConfig.setDefaults((Configuration)defConfig);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getIpDataConfig() {
        if (this.ipDataConfig == null)
            reloadIpDataConfig();
        return this.ipDataConfig;
    }

    public void saveIpDataConfig() {
        if (this.ipDataConfig == null || this.ipDataFile == null)
            return;
        try {
            getIpDataConfig().save(this.ipDataFile);
            this.saveData = false;
        } catch (IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.ipDataFile, ex);
        }
    }

    public void saveDefaultConfig() {
        if (this.ipDataFile == null)
            this.ipDataFile = new File(this.plugin.getDataFolder(), "ipdata.yml");
        if (!this.ipDataFile.exists())
            this.plugin.saveResource("ipdata.yml", false);
    }

    public int purge() {
        List<String> removeList = new ArrayList<>();
        int recordsPurged = 0;
        Date oldestDate = new Date(System.currentTimeMillis() - this.plugin.expirationTime * 24L * 60L * 60L * 1000L);
        ConfigurationSection ipConfSect = getIpDataConfig().getConfigurationSection("ip");
        if (ipConfSect != null)
            for (String ip : ipConfSect.getKeys(false)) {
                Set<String> uuidKeys = getIpDataConfig().getConfigurationSection("ip." + ip).getKeys(false);
                int remainingKeys = uuidKeys.size();
                for (String uuid : uuidKeys) {
                    String uuidData = getIpDataConfig().getString("ip." + ip + "." + uuid);
                    String[] arg = uuidData.split(",");
                    Date date = new Date(Long.valueOf(arg[0]).longValue());
                    if (date.before(oldestDate)) {
                        removeList.add("ip." + ip + "." + uuid);
                        remainingKeys--;
                        recordsPurged++;
                    }
                }
                if (remainingKeys <= 0)
                    removeList.add("ip." + ip);
            }
        for (String key : removeList)
            getIpDataConfig().set(key, null);
        return recordsPurged;
    }

    public void addUpdateIp(String ip, String uuid, String name) {
        Date date = new Date();
        getIpDataConfig().set("ip." +
                        ip.replace('.', '_') + "." +
                        uuid,
                String.valueOf(date.getTime()) + "," + name);
        this.saveData = true;
    }

    public List<String> getAltNames(String ip, String excludeUuid) {
        List<String> altList = new ArrayList<>();
        Date oldestDate = new Date(System.currentTimeMillis() - this.plugin.expirationTime * 24L * 60L * 60L * 1000L);
        ConfigurationSection ipIpConfSect = getIpDataConfig().getConfigurationSection("ip." + ip.replace('.', '_'));
        if (ipIpConfSect != null)
            for (String uuid : ipIpConfSect.getKeys(false)) {
                String uuidData = getIpDataConfig().getString("ip." + ip.replace('.', '_') + "." + uuid);
                String[] arg = uuidData.split(",");
                Date date = new Date(Long.valueOf(arg[0]).longValue());
                if (!uuid.equals(excludeUuid) && date.after(oldestDate))
                    altList.add(arg[1]);
            }
        Collections.sort(altList, String.CASE_INSENSITIVE_ORDER);
        return altList;
    }

    public class PlayerDataType {
        String ip;

        String uuid;

        String name;
    }

    public PlayerDataType lookupOfflinePlayer(String playerName) {
        PlayerDataType playerData = null;
        Date oldestDate = new Date(System.currentTimeMillis() - this.plugin.expirationTime * 24L * 60L * 60L * 1000L);
        Date newestDate = new Date(0L);
        ConfigurationSection ipConfSect = getIpDataConfig().getConfigurationSection("ip.");
        Map<String, Object> confMap = ipConfSect.getValues(true);
        for (Map.Entry<String, Object> entry : confMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                String[] key = ((String)entry.getKey()).split("\\.");
                String ip = key[0];
                String uuid = key[1];
                String[] value = ((String)entry.getValue()).split(",");
                String timestamp = value[0];
                String name = value[1];
                if (playerName.equalsIgnoreCase(name)) {
                    Date date = new Date(Long.valueOf(timestamp).longValue());
                    if (date.after(oldestDate) && date.after(newestDate)) {
                        playerData = new PlayerDataType();
                        playerData.ip = ip.replace('_', '.');
                        playerData.uuid = uuid;
                        playerData.name = name;
                        newestDate = date;
                    }
                }
            }
        }
        return playerData;
    }

    public String getFormattedAltString(String name, String ip, String uuid, String playerFormat, String playerListFormat, String playerSeparator) {
        List<String> altList = getAltNames(ip, uuid);
        if (!altList.isEmpty()) {
            StringBuilder sb = new StringBuilder(MessageFormat.format(playerFormat, new Object[] { name }));
            boolean outputComma = false;
            for (String altName : altList) {
                if (outputComma)
                    sb.append(playerSeparator);
                outputComma = true;
                sb.append(MessageFormat.format(playerListFormat, new Object[] { altName }));
            }
            return sb.toString();
        }
        return null;
    }
}
