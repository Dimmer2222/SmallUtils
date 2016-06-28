package com.github.Dimmer2222.small.commands;

import com.github.Dimmer2222.DLIB.api.BukkitEasier;
import com.github.Dimmer2222.small.events.JoinEffectsEvent;
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

public final class JoinEffectsCommand extends JoinEffectsEvent implements CommandExecutor{

    JavaPlugin js;

    public JoinEffectsCommand(JavaPlugin js){
        super(js);
        BukkitEasier.registerCommand(js, "JoinEffects", this);
        this.js = js;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("JoinEffects")){
            if(js.getConfig().getBoolean("SmallUtils.Events.JoinEffects.An/Aus")){
                js.getConfig().set("SmallUtils.Events.JoinEffects.An/Aus", false);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Die Effekte beim beitreten sind nun aus.");
            }else{
                js.getConfig().set("SmallUtils.Events.JoinEffects.An/Aus", true);
                js.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "Die Effecte beim beitreten sind nun an.");
            }
            return true;
        }

        return false;
    }
}
