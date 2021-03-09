package me.superbiebel.defaultmysqldatabase;

import me.superbiebel.punishmentmanager.PunishmentManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DefaultMysqlDatabaseStartup extends JavaPlugin {
    
    @Override
    public void onEnable() {
        PunishmentManager.getServiceManager().registerService(new LoginInfoLoggerMysql());
        
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
