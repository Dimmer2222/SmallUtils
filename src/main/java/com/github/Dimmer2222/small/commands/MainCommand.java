package com.github.Dimmer2222.small.commands;

import com.github.Dimmer2222.small.Main;
import com.github.Dimmer2222.small.events.MainEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

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


public final class MainCommand extends MainEvent implements CommandExecutor {


    JavaPlugin js;
    Player p = null;

    public MainCommand(JavaPlugin js) {
        super(js);
        js.getCommand("SmallUtils").setExecutor(this);
        this.js = js;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if (cmd.getName().equalsIgnoreCase("SmallUtils")) {
            if (args.length == 0) {
                return false;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                js.getServer().getPluginManager().disablePlugin(js);
                js.getServer().getPluginManager().enablePlugin(js);
                sender.sendMessage(ChatColor.AQUA + "Das SmallUtils wurde reloaded.");


            }

            if(args.length == 1 && args[0].equalsIgnoreCase("updateNotes")) {
                if (p != null) {
                    PlayerInventory pi = p.getInventory();
                    ItemStack wb = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bookMeta = (BookMeta) wb.getItemMeta();
                    bookMeta.setTitle("Changelog");
                    bookMeta.setAuthor("Dimmer2222");
                    bookMeta.addPage("Changelog\n"
                            + "23.10.15\n"
                            + "-Changed Name\n"
                            + "-Added Changelog\n"
                            + "-Added Alias(su)\n"
                            + "-Bitte Ideen Vorschlagen\n"
                            + '\n'
                            + "02.11.15\n"
                            + "-added Konstruktoren zum API"
                            + "\n");
                    bookMeta.addPage("03.11.16\n"
                            + "-added clearchat Kommando(schon laenger)\n"
                            + "-fixed bugs\n"
                            + "-add version Argument zum Hauptcommand\n"
                            + "-Manche Wörter oder Kommandonamen wurden geändert"
                            + "\n");


                    wb.setItemMeta(bookMeta);
                    pi.setItemInHand(wb);
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Du bist kein Spieler");
                }


            }

            if(args.length == 1 && args[0].equalsIgnoreCase("version")){
                sender.sendMessage(ChatColor.AQUA + "Die Version von SmallUtils betraegt: " + js.getDescription().getVersion());
                sender.sendMessage(ChatColor.AQUA + "©Dimmer2222 2016, Licensed under the MIT License.");
                if(super.isNewerVersionAvailable){
                    p.sendMessage(ChatColor.AQUA + "Es ist die neuere Version " + temp + " verfügbar.");
                }
            }

            return true;
        }
        return false;



    }

    public void checkforUpdate() {


        try {
            URL url = new URL("https://dl.dropboxusercontent.com/s/plmz6ro3srh05eg/Versions.txt");
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(Main.getPDataFolder() + "Versions.txt");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.flush();
            fos.close();
            File file = new File(Main.getPDataFolder() + "Versions.txt");
            Scanner scanner = new Scanner(file);
            String temp = scanner.nextLine();
            super.temp = temp;
            scanner.close();
            file.delete();
            if (temp.equalsIgnoreCase(js.getDescription().getVersion())) {
                super.isNewerVersionAvailable = false;
                return;
            }else
                super.isNewerVersionAvailable = true;
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

}






