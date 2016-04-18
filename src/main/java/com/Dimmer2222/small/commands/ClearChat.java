package com.Dimmer2222.small.commands;

import com.Dimmer2222.small.Main;
import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.DAPI.api.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

public final class ClearChat implements CommandExecutor {

    Player p = null;
    Main m;

    public ClearChat(Main m) {
        BukkitEasier.registerCommand(m, "clearChat", this);
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if (cmd.getName().equalsIgnoreCase("clearChat")) {


            if (p != null) {

                if (args.length > 1)
                    return false;


                if (args.length == 0) {
                    for (int i = 0; i < 100; i++) {
                        p.sendMessage("");
                    }
                    p.sendMessage(ChatColor.AQUA + "Dein Chat wurde von dir für dich geloescht");
                    return true;
                }


                if (args.length == 1) {


                    if (p.hasPermission("SmallUtils.clearChat.all")) {

                        if (args[0].equalsIgnoreCase("all")) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                for (int i = 0; i < 100; i++) {
                                    players.sendMessage("");
                                }
                                players.sendMessage(ChatColor.AQUA + "Der Chat wurde von " + p.getName() + " geloescht");
                            }
                            return true;
                        } else {
                            if (p.hasPermission("SmallUtils.clearChat.Player")) {
                                if (PlayerManager.getPlayer(args[0]) != null) {
                                    Player zielSpieler = m.getServer().getPlayer(PlayerManager.getPlayer(args[0]).getUniqueId());
                                    for (int i = 0; i < 100; i++) {
                                        zielSpieler.sendMessage("");
                                    }
                                    zielSpieler.sendMessage(ChatColor.AQUA + "Dein Chat wurde von " + p.getName() + " geloescht");
                                } else {
                                    p.sendMessage(ChatColor.DARK_RED + "Der Spieler ist nicht online.");
                                }
                                return true;
                            }


                        }

                    }
                }

            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Du bist kein Spieler.");
                return true;
            }
        }
        return false;
    }
}



