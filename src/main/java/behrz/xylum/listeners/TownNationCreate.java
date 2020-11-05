package behrz.xylum.listeners;

import behrz.xylum.Earth;
import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownNationCreate implements Listener {

    @EventHandler
    public void onTownCreate(NewTownEvent event) {
        Earth.sendDiscord("general"," :milky_way: The town **" + event.getTown().getName() + "** was created by **" + event.getTown().getMayor().getName() + "**.");
    }

    @EventHandler
    public void onNationCreate(NewNationEvent event) {
        Earth.sendDiscord("general"," :milky_way: The nation **" + event.getNation().getName() + "** was created with **" + event.getNation().getCapital().getName() + "** as its capital.");
    }
}
