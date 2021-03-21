package me.superbiebel.defaultmysqldatabase;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DefaultMysqlDatabaseStartup extends JavaPlugin {
    
    @Override
    public void onEnable() {
        PunishmentManager.getServicesAPI().register(new LoginInfoLoggerMysql());
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
