package behrz.xylum.listeners;

import behrz.xylum.Earth;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.DeleteNationEvent;
import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.event.NationMergeEvent;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownNationDelete implements Listener {

    @EventHandler
    public void onTownDelete(DeleteTownEvent event) {

        Earth.sendDiscord("earth-chat"," :milky_way: The town **" + event.getTownName() + "** fell into ruin.");

        try {
            Town town = TownyAPI.getInstance().getDataSource().getTown(event.getTownName());
            Resident mayor = town.getMayor();
            double balance = town.getAccount().getHoldingBalance();

            if (balance != 0.0) {
                town.withdrawFromBank(mayor, (int) balance);
            }
        } catch (TownyException | EconomyException e) {
            Earth.getPlugin().getLogger().warning("Failed to transfer nation balance on delete.");
        }
    }

    @EventHandler
    public void onNationDelete(DeleteNationEvent event) {

        Earth.sendDiscord("earth-chat"," :milky_way: The nation **" + event.getNationName() + "** fell into ruin.");

        try {
            Nation nation = TownyAPI.getInstance().getDataSource().getNation(event.getNationName());
            Resident king = nation.getKing();
            double balance = nation.getAccount().getHoldingBalance();

            if (balance != 0.0) {
                nation.withdrawFromBank(king, (int) balance);
            }
        } catch (TownyException | EconomyException e) {
            Earth.getPlugin().getLogger().warning("Failed to transfer nation balance on delete.");
        }
    }

    @EventHandler
    public void onNationMerge(NationMergeEvent event) {
        Earth.sendDiscord("earth-chat"," :milky_way: The nation **" + event.getNation().getName() + "** merged into **" + event.getRemainingnation().getName() + "**.");
    }
}
