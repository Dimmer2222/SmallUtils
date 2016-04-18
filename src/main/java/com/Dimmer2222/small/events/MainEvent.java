package com.Dimmer2222.small.events;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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

public class MainEvent implements Listener{

    private JavaPlugin js;
    protected String temp = "";
    protected boolean isNewerVersionAvailable = false;

    /*

    Dieser Konstruktor ist für

     */

    public MainEvent(JavaPlugin js){
        BukkitEasier.registerEvents(js, this);
        this.js = js;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(isNewerVersionAvailable){
            if(e.getPlayer().hasPermission("SmallUtils.Update"))
            e.getPlayer().sendMessage(ChatColor.AQUA + "Die neue Version " + temp + " ist verfügbar Sie besitzen die Version " + js.getDescription().getVersion() + " . Link: https://www.mediafire.com/folder/3mke72rf34mz4/SmallUtils.");
        }
    }

}
