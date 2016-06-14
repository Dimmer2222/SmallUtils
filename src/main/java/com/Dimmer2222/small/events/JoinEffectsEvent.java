package com.Dimmer2222.small.events;

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

import com.Dimmer2222.DAPI.DAPI;
import com.Dimmer2222.DAPI.DCore;
import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.exceptions.ValueExistException;
import com.Dimmer2222.DAPI.exceptions.ValueNotExistException;
import com.Dimmer2222.small.SmallUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.Dimmer2222.DAPI.api.Implements.SpecialItemManager;



public class JoinEffectsEvent implements Listener{

    private JavaPlugin js;

    /*

        Dieser Konstruktor registiert das Plugin und macht es für Bukkit Sichtbar.
        Und initalisiert die Variable js

     */

    public JoinEffectsEvent(JavaPlugin js){
        BukkitEasier.registerEvents(js, this);
        this.js = js;
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        DAPI dapi = DCore.getInstance(SmallUtils.getInstance());
        SpecialItemManager sim = dapi.getSpecialItemManager();
        try {
            sim.createSpecialObject(new Location(Bukkit.getWorld("world") ,0.5, 1.0, 0.5), "stone");
        } catch (ValueExistException e1) {
            e1.printStackTrace();
        }
        try {
            Item item = sim.getSpecialObject("stone");
            sim.changeAmout();
        } catch (ValueNotExistException e1) {
            e1.printStackTrace();
        }
    }

       /*
       Bei diesem Event wird beim Joinen eine permanente Nachtsicht und Geschwindigkeits Effect übergeben.
       Diese Effekte werden durch den Boolean von der Standart Config "SmallUtils.Events.JoinEffects.An/Aus"
       kontrolliert.

     */


    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        if(js.getConfig().getBoolean("SmallUtils.Events.JoinEffects.An/Aus")) {
            Player p = e.getPlayer();
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 0, false, false));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 3, false, false));

        }

    }

}
