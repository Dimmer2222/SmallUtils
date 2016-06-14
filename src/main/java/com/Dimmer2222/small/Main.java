package com.Dimmer2222.small;

import com.Dimmer2222.small.commands.*;
import com.Dimmer2222.small.events.TestEvent;
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


public final class Main extends JavaPlugin {

	static SmallUtils instance;
	public static Main MainInstance;

	@Override
	public void onEnable() {
		super.onEnable();
		config();
		if(testDAPI()) {
			instance = new SmallUtils();
			new WartungenCommand(this);
			new WarpCommand(this);
			new MainCommand(this).checkforUpdate();
			new ClearChat(this);
			new JoinEffectsCommand(this);
			//new RegionCommand(this);
		}
		else this.getServer().getPluginManager().registerEvents(new TestEvent(), this);


	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public boolean testDAPI(){
		if(this.getServer().getPluginManager().getPlugin("DAPI") != null){
			return true;
		}
		return false;
	}

	public void config() {
		reloadConfig();
		getConfig().options().header("SmallUtils's Einstellungen");
		getConfig().addDefault("SmallUtils.Events.Wartungen.An/Aus", false);
		getConfig().addDefault("SmallUtils.Events.JoinEffects.An/Aus", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}


	public static String getPDataFolder() {
		return "plugins/SmallUtils/";
	}

}






