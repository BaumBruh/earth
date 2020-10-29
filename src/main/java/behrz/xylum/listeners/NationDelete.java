package behrz.xylum.listeners;

import behrz.xylum.Xylum;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.PreDeleteNationEvent;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NationDelete implements Listener {

    @EventHandler
    public void onNationDelete(PreDeleteNationEvent event) {
        try {
            Nation nation = TownyAPI.getInstance().getDataSource().getNation(event.getNationName());
            Resident king = nation.getKing();
            double balance = nation.getAccount().getHoldingBalance();

            if (balance != 0.0) {
                nation.withdrawFromBank(king, (int) balance);
            }
        } catch (TownyException | EconomyException e) {
            Xylum.getPlugin().getLogger().warning("Failed to transfer nation balance on delete.");
        }

    }
}
