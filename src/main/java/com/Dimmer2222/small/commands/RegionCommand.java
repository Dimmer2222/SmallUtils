package com.Dimmer2222.small.commands;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.api.RegionManager;
import com.Dimmer2222.DAPI.exceptions.RegionError;
import com.Dimmer2222.small.Main;
import com.Dimmer2222.small.events.RegionEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class RegionCommand extends RegionEvent implements CommandExecutor{

    Player p = null;
    RegionManager manager = new RegionManager(Main.getPDataFolder(), "Test.yml");
    HashMap<UUID, Location> loc1 = new HashMap<>();

    HashMap<UUID, Location> loc2 =   new HashMap<>();;

    public RegionCommand(JavaPlugin js){
        super(js);
        BukkitEasier.registerCommand(js, "pos1", this);
        BukkitEasier.registerCommand(js, "pos2", this);
        BukkitEasier.registerCommand(js, "region", this);
    }





    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            p = (Player) sender;
        }

        if(cmd.getName().equalsIgnoreCase("pos1")){
            loc1.put(p.getUniqueId() ,p.getLocation());
            p.sendMessage("Location 1 wurde gesichert");
        }

        if(cmd.getName().equalsIgnoreCase("pos2")){
            loc2.put(p.getUniqueId() ,p.getLocation());

            if(!loc1.get(p.getUniqueId()).getWorld().equals(loc2.get(p.getUniqueId()).getWorld())){
                p.sendMessage(ChatColor.DARK_RED + "Error: Punkte befinden sich in anderen Welten.");
                return true;
            }

            p.sendMessage("Location 2 wurde gesichert");

        }
        if(cmd.getName().equalsIgnoreCase("region") && args.length == 1){
            if(loc1.get(p.getUniqueId()) == null | loc2.get(p.getUniqueId()) == null){
                p.sendMessage(ChatColor.DARK_RED + "Error: Es wurde ein Punkt nicht gesetzt.");
                return true;
            }

            if(!loc1.get(p.getUniqueId()).getWorld().equals(loc2.get(p.getUniqueId()).getWorld())){
                p.sendMessage(ChatColor.DARK_RED + "Error: Punkte befinden sich in anderen Welten.");
                return true;
            }

            manager.createRegion(args[0], loc1.get(p.getUniqueId()), loc2.get(p.getUniqueId()));
            p.sendMessage("Region wurde erzeugt.");
            return true;
        }
        return false;
    }
}
