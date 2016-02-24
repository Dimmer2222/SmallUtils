package com.Dimmer2222.small.events;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

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

public class WartungenEvent implements Listener {

        JavaPlugin js;

        public WartungenEvent(JavaPlugin js){
            BukkitEasier.registerEvents(js, this);
            this.js = js;
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void OnJoin(PlayerJoinEvent e){
            if(js.getConfig().getBoolean("SmallUtils.Events.Wartungen.An/Aus")) {
                if (!e.getPlayer().hasPermission("SmallUtils.Wartungen.join")) {
                    e.getPlayer().kickPlayer(ChatColor.DARK_RED + "Wartungen");
                    e.setJoinMessage("");
                }
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onLeave(PlayerQuitEvent e){
            if(js.getConfig().getBoolean("SmallUtils.Events.Wartungen.An/Aus")) {
                if (!e.getPlayer().hasPermission("SmallUtils.Wartungen.join")) {
                    e.setQuitMessage("");
                }
            }

        }

    }

