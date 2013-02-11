/*
 * DO NOT DISTRIBUTE. ©2012 MG ENTERPRISES(MANUEL GAUTO)
 */
package org.mgenterprises.java.bukkit.gfps;

import org.bukkit.plugin.java.JavaPlugin;
import org.mgenterprises.java.bukkit.gmcfps.Core.BasicCommands.GameManagementCommands;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.Game;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.GameManager;
import org.mgenterprises.java.bukkit.gmcfps.Core.Teams.Team;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicSMG;

/**
 *
 * @author Manuel Gauto
 */
public class GFPS extends JavaPlugin {

    @Override
    public void onEnable() {
        GameManager gm = new GameManager(this);
        GameManagementCommands gmcommand = new GameManagementCommands(gm);
        getCommand("join").setExecutor(gmcommand);
        
        Game g = new Game(this, "test");
        Team t1 = new Team("Team1");
        Team t2 = new Team("Team2");
        g.getFPSCore().getTeamManager().registerTeam(t1);
        g.getFPSCore().getTeamManager().registerTeam(t2);

        g.getFPSCore().getWeaponManager().registerWeapon(new BasicSMG(g.getFPSCore().getWeaponManager()));
        
        gm.registerGame(g);
        this.getServer().getPluginManager().registerEvents(g.getFPSCore().getCombatListener(), this);
        this.getServer().getPluginManager().registerEvents(g.getFPSCore().getWeaponListeners(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }
}