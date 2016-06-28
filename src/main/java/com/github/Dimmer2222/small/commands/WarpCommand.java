package com.github.Dimmer2222.small.commands;

import com.github.Dimmer2222.DLIB.api.BukkitEasier;
import com.github.Dimmer2222.DLIB.exceptions.CoreException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.Dimmer2222.small.Main;
import com.github.Dimmer2222.DLIB.api.WarpManager;

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


public final class WarpCommand implements CommandExecutor{

	Main m;
	Player p = null;
	WarpManager wm;
     public WarpCommand(Main m) {
	 BukkitEasier.registerCommand(m, "suwarp", this);
	 BukkitEasier.registerCommand(m, "susetwarp", this);
	 BukkitEasier.registerCommand(m, "sudelwarp", this);
	 wm = new WarpManager(Main.getPDataFolder(), "Warp.yml");

	 this.m = m;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			p = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("suwarp")){
		if(p != null){	
			if(args.length == 0){
				p.sendMessage("Hier ist die Warplist:");
				try {
					p.sendMessage(wm.getWarps());
				}catch (NullPointerException e){
					p.sendMessage("Es wurde noch kein Warp erstellt.");
				}
			}
			if(args.length == 1) {

					Location loc = wm.getWarp(args[0]);
					if (loc != null) {
						if (loc.getWorld().getName() != null) {
							p.teleport(loc);
							p.sendMessage("Du wurdest zum Warp " + args[0] + " gewarpt");
						} else {
							sender.sendMessage("Der Warp exestiert nicht.");
						}
					}else{
						p.sendMessage("Die Welt wurde nicht gefunden.");
					}
				}

		}else{
			sender.sendMessage("Dieser Befehl ist nur fuer Spieler.");
		
		}
		return true;
		}
	
				if(cmd.getName().equalsIgnoreCase("susetwarp")){
					if(p != null){
					if(args.length == 1) {
						try {
								wm.createWarp(args[0], p.getLocation());
								p.sendMessage("Der Warp " + args[0] + " wurde gesetzt.");

						}catch(CoreException e){
							sender.sendMessage("Dieser Warp " + args[0] + " exestiert schon.");
						}
					}
					if(args.length == 0){
						return false;
				}
					}
				
				else{
					sender.sendMessage("Dieser Befehl ist nur fuer Spieler.");
				
				
				}
					return true;
	}
		
				if(cmd.getName().equalsIgnoreCase("sudelwarp")){
					if(p != null){
					if(args.length == 1){
						try {
							if (wm.getWarp(args[0]) != null) {
								wm.deleteWarp(args[0]);
								p.sendMessage("Der Warp " + args[0] + " wurde geloescht.");
							} else {
								sender.sendMessage("Dieser Warp " + args[0] + " exestiert nicht.");
							}
						}catch (CoreException e){
							e.printStackTrace();
						}
					}
					if(args.length == 0){
						return false;
					}
					}
				else{
					sender.sendMessage("Dieser Befehl ist nur fuer Spieler.");
				
				}
					return true;
				}
					
					
				
					
				
	
				
		
	
			
			
		
		
		
	
		return false;
	}
	
	

}
