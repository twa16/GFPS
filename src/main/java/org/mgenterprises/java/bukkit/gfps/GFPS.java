/*
 * DO NOT DISTRIBUTE. ©2012 MG ENTERPRISES(MANUEL GAUTO)
 */
package org.mgenterprises.java.bukkit.gfps;

import org.bukkit.plugin.java.JavaPlugin;
import org.mgenterprises.java.bukkit.gmcfps.Core.BasicCommands.GameManagementCommands;
import org.mgenterprises.java.bukkit.gmcfps.Core.FPSCore;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.Game;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.GameManager;
import org.mgenterprises.java.bukkit.gmcfps.Core.Teams.Team;

/**
 *
 * @author Manuel Gauto
 */
public class GFPS extends JavaPlugin {

    public void onEnable() {
        Game g = new Game("test");
        FPSCore core = new FPSCore(g);
        core.getTeamManager().registerTeam(new Team("Team1"));
        core.getTeamManager().registerTeam(new Team("Team2"));
        GameManager gm = new GameManager(this);
        gm.registerGame(g);
        GameManagementCommands gmcommand = new GameManagementCommands(gm);
        getCommand("join").setExecutor(gmcommand);

        
        this.getServer().getPluginManager().registerEvents(core.getCombatListener(), this);
        this.getServer().getPluginManager().registerEvents(core.getWeaponListeners(), this);
    }

    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }
}