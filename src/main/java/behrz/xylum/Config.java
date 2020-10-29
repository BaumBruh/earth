package behrz.xylum;

public class Config {

    public String getJoinPlayerPrefix() {
        return "&4[Alts] &c";
    }

    public String getJoinPlayer() {
        return "{0} may be an alt of ";
    }

    public String getJoinPlayerList() {
        return "{0}";
    }

    public String getJoinPlayerSeparator() {
        return ", ";
    }

    public String getAltCmdPlayer() {
        return "&c{0} may be an alt of ";
    }

    public String getAltCmdPlayerList() {
        return "&c{0}";
    }

    public String getAltCmdPlayerSeparator() {
        return "&c, ";
    }

    public String getAltCmdPlayerNoAlts() {
        return "&c{0} has no known alts";
    }

    public String getAltCmdNoAlts() {
        return "&cNo alts found";
    }

    public String getAltCmdPlayerNotFound() {
        return "&c{0} not found";
    }

    public String getAltCmdParamError() {
        return "&cMust specify at most one player";
    }

    public String getAltCmdNoPerm() {
        return "&cYou do not have permission for this command";
    }
}
