package com.Dimmer2222.small.events;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.api.RegionManager;
import com.Dimmer2222.DAPI.api.Region;
import com.Dimmer2222.small.Main;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionEvent implements Listener{

    RegionManager manager = new RegionManager(Main.getPDataFolder(), "Test.yml");
    public RegionEvent(JavaPlugin js){
        BukkitEasier.registerEvents(js, this);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location loc1 = e.getFrom();
        Location loc2 = e.getTo();
        manager.getRegions().forEach((region) -> {
            System.out.println(region.getminX());
            System.out.println(region.getminY());
            System.out.println(region.getminZ());
            System.out.println(region.getmaxX());
            System.out.println(region.getmaxY());
            System.out.println(region.getmaxZ());
            System.out.println(region.insideRegion(loc1));
            System.out.println(region.insideRegion(loc2));
            if (region.insideRegion(loc2) && !region.insideRegion(loc1))
                System.out.println(":D");
       });
    }

}

