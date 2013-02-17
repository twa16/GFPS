/*
 * DO NOT DISTRIBUTE. ©2012 MG ENTERPRISES(MANUEL GAUTO)
 */
package org.mgenterprises.java.bukkit.gfps;

import org.bukkit.plugin.java.JavaPlugin;
import org.mgenterprises.java.bukkit.gmcfps.Core.BasicCommands.CommandRegisterer;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.Game;
import org.mgenterprises.java.bukkit.gmcfps.Core.GameManagement.GameManager;
import org.mgenterprises.java.bukkit.gmcfps.Core.Teams.Team;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicRocketLauncher;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicSMG;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicShotgun;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.BasicSniper;
import org.mgenterprises.java.bukkit.gmcfps.Core.Weapons.Implementations.Twa16GodWeapon;

/**
 *
 * @author Manuel Gauto
 */
public class GFPS extends JavaPlugin {

    GameManager gm;

    @Override
    public void onEnable() {
        this.getDataFolder().mkdir();
        gm = new GameManager(this);
        
        gm.addDefaultWeapon(new BasicSMG(null));
        gm.addDefaultWeapon(new BasicSniper(null));
        gm.addDefaultWeapon(new BasicRocketLauncher(null));
        gm.addDefaultWeapon(new BasicShotgun(null));
        gm.addDefaultWeapon(new Twa16GodWeapon(null));
        
        gm.loadAllGames();
        
        CommandRegisterer cr = new CommandRegisterer(this, gm);
        cr.registerCommands();

    }

    @Override
    public void onDisable() {
        gm.saveAllGames();
    }
}