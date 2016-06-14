package com.Dimmer2222.small;

import com.Dimmer2222.DAPI.DAPI;
import com.Dimmer2222.DAPI.DCore;
import com.Dimmer2222.DAPI.api.CountdownManager;
import com.Dimmer2222.DAPI.api.Implements.SpecialBlockManager;
import com.Dimmer2222.DAPI.api.Implements.TeamManager;
import com.Dimmer2222.DAPI.api.Implements.WorldManager;
import com.Dimmer2222.DAPI.api.PlayerConfig;
import com.Dimmer2222.DAPI.api.RegionManager;
import com.Dimmer2222.DAPI.api.WarpManager;
import com.Dimmer2222.DAPI.api.Implements.SpecialItemManager;

public class SmallUtils implements DAPI {

    private static CountdownManager cm = null;
    private static WorldManager wm = null;
    private static PlayerConfig pc = null;
    private static RegionManager rm = null;
    private static WarpManager warpm = null;
    private static TeamManager tm = null;
    private static SpecialBlockManager sbm = null;
    private static SpecialItemManager sim = null;

    public SmallUtils(){
        cm = new CountdownManager();
        wm = new WorldManager(Main.getPDataFolder(), "Worlds.yml");
        pc = new PlayerConfig(Main.getPDataFolder(), "PlayerConfig.yml");
        rm = new RegionManager(Main.getPDataFolder(), "Regions.yml");
        warpm = new WarpManager(Main.getPDataFolder(), "Warps.yml");
        tm = new TeamManager(Main.getPDataFolder(), "Teams.yml");
        sbm = new SpecialBlockManager(Main.getPDataFolder(), "Blocks.yml");
        sim = new SpecialItemManager(Main.getPDataFolder(), "Items.yml");
        DCore.registerDAPI(this);
    }

    @Override
    public WorldManager getWorldManager() {
        return SmallUtils.wm;
    }

    @Override
    public CountdownManager getCountdownManager() {
        return SmallUtils.cm;
    }

    @Override
    public PlayerConfig getPlayerConfig() {
        return SmallUtils.pc;
    }

    @Override
    public RegionManager getRegionManager() {
        return SmallUtils.rm;
    }

    @Override
    public WarpManager getWarpManager() {
        return SmallUtils.warpm;
    }

    @Override
    public TeamManager getTeamManager() {
        return SmallUtils.tm;
    }

    @Override
    public SpecialBlockManager getSpecialBlockManager() {
        return SmallUtils.sbm;
    }

    @Override
    public SpecialItemManager getSpecialItemManager(){
        return SmallUtils.sim;
    }

    public static SmallUtils getInstance(){
        return Main.instance;
    }



}
