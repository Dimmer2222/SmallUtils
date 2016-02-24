package com.Dimmer2222.small.commands;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.small.events.WartungenEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

public final class WartungenCommand extends WartungenEvent implements CommandExecutor{

    JavaPlugin js;

    public WartungenCommand(JavaPlugin js) {
        super(js);
        BukkitEasier.registerCommand(js, "Wartungen", this);
        this.js = js;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        if(cmd.getName().equalsIgnoreCase("Wartungen")){
            if(!js.getConfig().getBoolean("SmallUtils.Events.Wartungen.An/Aus")) {
                js.getConfig().set("SmallUtils.Events.Wartungen.An/Aus", true);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Wartungen sind nun an.");
            }else{
                js.getConfig().set("SmallUtils.Events.Wartungen.An/Aus", false);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Wartungen sind nun aus.");
            }
            return true;
        }
        return false;
    }
}