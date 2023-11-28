package org.regicide.test;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.regicide.test.SystemOfGrow.Grow;
import java.util.logging.Logger;

public final class Test extends JavaPlugin implements Listener {

    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new Grow(this), this);
        Logger logger = getLogger();
        logger.info("The plagin get started");
        getServer().getPluginManager().registerEvents(this, this);
        logger.info("CustomTickSpeedPlugin has been enabled!");
    }


    public void onDisable() {
        // Plugin shutdown logic
    }

}
