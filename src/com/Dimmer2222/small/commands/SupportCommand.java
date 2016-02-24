package com.Dimmer2222.small.commands;

import com.Dimmer2222.DAPI.api.BukkitEasier;
import com.Dimmer2222.small.events.SupportEvent;
import com.Dimmer2222.DAPI.exceptions.CoreException;
import com.Dimmer2222.DAPI.exceptions.TeamError;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

public final class SupportCommand extends SupportEvent implements CommandExecutor {

    Player p = null;

    public SupportCommand(JavaPlugin js) {
        super(js);
        BukkitEasier.registerCommand(js, "Support", this);
        BukkitEasier.registerCommand(js, "joinSupport", this);
        BukkitEasier.registerCommand(js, "leaveSupport", this);
        BukkitEasier.registerCommand(js, "delSupport", this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        if (p == null) {
            sender.sendMessage(ChatColor.DARK_RED + "Du bist kein Spieler!");
        } else {

            if (cmd.getName().equalsIgnoreCase("Support")) {
                if (args.length == 0) {
                    try {
                        super.createTeam(p.getUniqueId(), p.getName());
                        p.sendMessage(ChatColor.AQUA + "Du hast ein SupportChannel erstellt und das Team wurde bescheit gesagt.");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("SmallUtils.Support.Team")) {
                                p.sendMessage(ChatColor.AQUA + "Der Spieler " + this.p.getName() + " braucht Hilfe vom Team.");
                            }
                        }
                    } catch (CoreException e) {
                        p.sendMessage(ChatColor.DARK_RED + "Dieses Team existiert schon.");
                    }

                }
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("joinSupport")) {
                if (args.length == 0) {
                    return false;
                }
                if (super.getTeam(p.getUniqueId()) == null) {
                    try {
                        super.joinTeam(p.getUniqueId(), args[0]);
                        p.sendMessage(ChatColor.AQUA + "Du bist einem SupportChannel beigetreten.");
                    } catch (CoreException e) {
                        p.sendMessage(ChatColor.DARK_RED + "Du bist bereits in einem SupportChannel.");

                    } catch (TeamError e) {
                        p.sendMessage(ChatColor.DARK_RED + "Der Spieler hatt keinen SupportChannel erstellt.");
                    }
                }else{
                    p.sendMessage(ChatColor.DARK_RED + "Du bist bereitz in einem SupportChannel.");
                }
            }


                if (cmd.getName().equalsIgnoreCase("leaveSupport")) {
                    try {
                        if (super.getTeam(p.getUniqueId()).equalsIgnoreCase(p.getName())) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (super.getTeam(p.getUniqueId()).equalsIgnoreCase(this.p.getName())) {
                                    p.sendMessage(ChatColor.AQUA + "Der SupportChannel Ersteller hat den SupportChannel verlassen und damit den Channel geschlossen.");
                                    super.deleteTeam(this.p.getName());
                                }
                            }
                            return true;
                        } else {
                            super.leaveTeam(p.getUniqueId(), super.getTeam(p.getUniqueId()));
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (super.getTeam(p.getUniqueId()).equalsIgnoreCase(super.getTeam(this.p.getUniqueId()))) {
                                    p.sendMessage(ChatColor.AQUA + "Der Supporter " + this.p.getName() + " hat den SupportChannel verlassen.");
                                    super.leaveTeam(this.p.getUniqueId(), super.getTeam(this.p.getUniqueId()));
                                }

                            }
                            return true;
                        }
                    } catch (TeamError e) {
                        p.sendMessage(ChatColor.DARK_RED + "Du bist in keinem SupportChannel.");
                    }
                    return true;
                }

                    if (cmd.getName().equalsIgnoreCase("delSupport")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (super.getTeam(p.getUniqueId()).equalsIgnoreCase(super.getTeam(this.p.getUniqueId()))) {
                                p.sendMessage(ChatColor.AQUA + "Der Supporter " + this.p.getName() + " hat den SupportChannel geschlossen.");
                                try {
                                    super.deleteTeam(super.getTeam(this.p.getUniqueId()));
                                } catch (TeamError | NullPointerException e) {
                                    p.sendMessage(ChatColor.DARK_RED + "Du warst in keinem SupportChannel.");
                                }
                            }
                        }
                }
        }
        return true;
    }
}





