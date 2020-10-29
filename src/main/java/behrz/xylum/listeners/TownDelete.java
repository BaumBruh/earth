package behrz.xylum.listeners;

import behrz.xylum.Xylum;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.PreDeleteTownEvent;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownDelete implements Listener {

    @EventHandler
    public void onNationDelete(PreDeleteTownEvent event) {
        try {
            Town town = TownyAPI.getInstance().getDataSource().getTown(event.getTownName());
            Resident mayor = town.getMayor();
            double balance = town.getAccount().getHoldingBalance();

            if (balance != 0.0) {
                town.withdrawFromBank(mayor, (int) balance);
            }
        } catch (TownyException | EconomyException e) {
        Xylum.getPlugin().getLogger().warning("Failed to transfer nation balance on delete.");
        }
    }
}