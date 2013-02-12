/*
 * DO NOT DISTRIBUTE. ©2012 MG ENTERPRISES(MANUEL GAUTO)
 */
package org.mgenterprises.java.bukkit.gfps;

import org.bukkit.plugin.java.JavaPlugin;
import org.mgenterprises.java.bukkit.gmcfps.Core.BasicCommands.CommandRegisterer;
import org.mgenterprises.java.bukkit.gmcfps.Core.BasicCommands.GameManagementCommands;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.Game;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.GameManager;
import org.mgenterprises.java.bukkit.gmcfps.Core.Teams.Team;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicRocketLauncher;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicSMG;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicShotgun;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicSniper;

/**
 *
 * @author Manuel Gauto
 */
public class GFPS extends JavaPlugin {

    GameManager gm;

    @Override
    public void onEnable() {
        gm  = new GameManager(this);
        GameManagementCommands gmcommand = new GameManagementCommands(gm);
        CommandRegisterer cr = new CommandRegisterer(this, gm);

        
        this.getDataFolder().mkdir();
        
        Game g = new Game(this, "test");
        Team t1 = new Team("Team1");
        Team t2 = new Team("Team2");
        g.getFPSCore().getTeamManager().registerTeam(t1);
        g.getFPSCore().getTeamManager().registerTeam(t2);

        g.getFPSCore().getWeaponManager().registerWeapon(new BasicSMG(g.getFPSCore().getWeaponManager()));
        g.getFPSCore().getWeaponManager().registerWeapon(new BasicSniper(g.getFPSCore().getWeaponManager()));
        g.getFPSCore().getWeaponManager().registerWeapon(new BasicRocketLauncher(g.getFPSCore().getWeaponManager()));
        g.getFPSCore().getWeaponManager().registerWeapon(new BasicShotgun(g.getFPSCore().getWeaponManager()));
        
        gm.registerGame(g);
        this.getServer().getPluginManager().registerEvents(g.getFPSCore().getCombatListener(), this);
        this.getServer().getPluginManager().registerEvents(g.getFPSCore().getWeaponListeners(), this);
    }

    @Override
    public void onDisable() {
        gm.saveAllGames();
    }
}