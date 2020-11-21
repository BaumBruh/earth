package behrz.xylum;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class EarthExpansion extends PlaceholderExpansion {

    private final Earth plugin;

    public EarthExpansion(Earth plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "xylum";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        if(player == null){
            return "";
        }

        switch (identifier) {
            case "isafk":
                String afk = PlaceholderAPI.setPlaceholders(player, "%essentials_afk%");
                if (afk.equals("yes")) {
                    return " (AFK)";
                } else if (afk.equals("no")){
                    return "";
                } else {
                    return "essentials not found";
                }
            case "fbracket":
                String ftown = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_town%");
                if (ftown.equals("true")) {
                    return "#74b9ff[";
                } else if (ftown.equals("false")){
                    return "";
                } else {
                    return "towny not found";
                }
            case "localfbracket":
                String fftown = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_town%");
                if (fftown.equals("true")) {
                    return "#55efc4[";
                } else if (fftown.equals("false")){
                    return "";
                } else {
                    return "towny not found";
                }
            case "bbracket":
                String btown = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_town%");
                if (btown.equals("true")) {
                    return "#74b9ff] ";
                } else if (btown.equals("false")){
                    return "";
                } else {
                    return "towny not found";
                }
            case "localbbracket":
                String bbtown = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_town%");
                if (bbtown.equals("true")) {
                    return "#55efc4] ";
                } else if (bbtown.equals("false")){
                    return "";
                } else {
                    return "towny not found";
                }
            case "natcomma":
                String nat = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_nation%");
                if (nat.equals("true")) {
                    String natt = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_nation_tag%");
                    if (!natt.equals("")) {
                        return "&7, ";
                    }
                } else if (nat.equals("false")){
                    return "";
                } else {
                    return "towny not found";
                }
            case "isvanished":
                String vanish = PlaceholderAPI.setPlaceholders(player, "%premiumvanish_isvanished%");
                if (vanish.equals("Yes")) {
                    return " &7[VANISHED]";
                } else if (vanish.equals("No")){
                    return "";
                } else {
                    return "sv not found";
                }
            case "hover":
                String realname = "&7Real name: &f%townyadvanced_towny_prefix%%player_name%%townyadvanced_towny_postfix%%vault_suffix%";

                String bounty = PlaceholderAPI.setPlaceholders(player, "%bounty_get%");
                if (bounty.equals("0")) {
                    bounty = "";
                } else {
                    bounty = "\n&7Bounty: %bounty_formatted%";
                }

                String married = PlaceholderAPI.setPlaceholders(player, "%marriage_status%");
                if (married.equals("Unmarried")) {
                    married = "";
                } else {
                    married = "\n&7Married: %marriage_status%";
                }

                String townnation = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_town%");
                Boolean hastown = null;
                Boolean hasnation = null;
                if (townnation.equals("false")) {
                    hastown = false;
                    townnation = "";
                } else {

                    hastown = true;
                    townnation = PlaceholderAPI.setPlaceholders(player, "%townyadvanced_has_nation%");
                    if (townnation.equals("true")) {
                        hasnation = true;
                        townnation = "\n\n&7Town: %townyadvanced_towny_colour%%townyadvanced_town_formatted%\n&7Nation: %townyadvanced_towny_colour%%townyadvanced_nation_formatted%";
                    } else {
                        hasnation = false;
                        townnation = "\n\n&7Town: %townyadvanced_towny_colour%%townyadvanced_town_formatted%";
                    }
                }

                String hover = realname + bounty + married + townnation;
                hover = PlaceholderAPI.setPlaceholders(player, hover);
                return hover;
        }
        return null;
    }
}
