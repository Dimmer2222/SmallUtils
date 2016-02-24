package com.Dimmer2222.small.events;

import com.Dimmer2222.small.Main;
import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.api.Implements.TeamManager;
import com.Dimmer2222.DAPI.exceptions.TeamError;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/*

The MIT License (MIT)

Copyright (c) 2016 Dimmer2222

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */

public class SupportEvent extends TeamManager implements Listener{


    public SupportEvent(JavaPlugin js){
        super(Main.getPDataFolder(), "Support.yml");
        BukkitEasier.registerEvents(js, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(super.getTeam(p.getUniqueId()) == null){
            return;
        }else{
            e.setCancelled(true);
            for(Player player : Bukkit.getOnlinePlayers()){
                if(super.getTeam(p.getUniqueId()).equalsIgnoreCase(super.getTeam(player.getUniqueId()))) {
                    if(!p.hasPermission("SmallUtils.Support.Team")){
                        player.sendMessage((ChatColor.DARK_BLUE + "[" + ChatColor.AQUA + "Support" + ChatColor.DARK_BLUE + "]" + ChatColor.WHITE + p.getName() + ": " + e.getMessage()));
                    }else{
                        player.sendMessage((ChatColor.DARK_RED + "[Team] " + ChatColor.DARK_BLUE + "[" + ChatColor.AQUA + "Support" + ChatColor.DARK_BLUE + "] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage()));
                    }
                }else{

                }
            }



        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();
        UUID uuid = p.getUniqueId();
            if (name.equalsIgnoreCase(super.getTeam(uuid))) {
                try {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (super.getTeam(player.getUniqueId()).equalsIgnoreCase(name)) {
                            p.sendMessage(ChatColor.AQUA + "Der Spieler " + name + " hat das Spiel verlassen und damit den SupportChannel geschlossen.");
                            super.deleteTeam(name);
                        }
                    }


                } catch (TeamError e1) {
                    e1.printStackTrace();
                }
            } else if (super.getTeam(uuid) != null) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (super.getTeam(uuid).equalsIgnoreCase(player.getName())) {
                        p.sendMessage(ChatColor.AQUA + "Der Supporter " + name + " hat das Spiel verlassen und damit den SupportChannel verlassen.");
                    }
                }
                try {
                    super.leaveTeam(uuid, super.getTeam(uuid));
                } catch (TeamError teamError) {
                    teamError.printStackTrace();
                }
            }

    }

    @EventHandler
    public void onServerStop(PluginDisableEvent e){
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(super.getTeam(p.getUniqueId()) != null){
                try {
                    super.deleteTeam(super.getTeam(p.getUniqueId()));
                } catch (TeamError e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
