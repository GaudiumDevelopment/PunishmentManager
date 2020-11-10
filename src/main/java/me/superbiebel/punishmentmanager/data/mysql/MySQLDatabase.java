package me.superbiebel.punishmentmanager.data.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.simplix.core.database.sql.SqlDatabaseConnection;
import dev.simplix.core.database.sql.handlers.HikariConnectionHandler;
import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.databases.Database;
import me.superbiebel.punishmentmanager.utils.Log;

import java.sql.SQLException;

public class MySQLDatabase implements Database {
    public static boolean isInitialized = false;
    private static HikariConfig mySQLConfig;
    @Getter
    private static HikariDataSource mysqlDataSource;
    @Getter
    private static SqlDatabaseConnection sourceConnection;
    private static String host = PunishmentManager.giveConfig().getString("MySQL.host");
    private static String username = PunishmentManager.giveConfig().getString("MySQL.username");
    private static String password = PunishmentManager.giveConfig().getString("MySQL.password");
    private static String port = PunishmentManager.giveConfig().getString("MySQL.port");
    private static String db = PunishmentManager.giveConfig().getString("MySQL.db");
    private static String useSSL = PunishmentManager.giveConfig().getString("MySQL.useSSL");
    public MySQLDatabase() {
        if (isInitialized) {
            throw new IllegalStateException("An instance of this class already exists!");
        }
    }
    @Override
    public void initialize() throws SQLException {
        mySQLConfig = new HikariConfig();
        mySQLConfig.setJdbcUrl( "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=" + useSSL);

        Log.debug("jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=" + useSSL,false, true,true,"");
        mySQLConfig.setUsername( username );
        mySQLConfig.setPassword( password );
        mySQLConfig.addDataSourceProperty( "cachePrepStmts" , PunishmentManager.giveConfig().getString("MySQL.cachePrepStmts"));
        mySQLConfig.addDataSourceProperty( "prepStmtCacheSize" , PunishmentManager.giveConfig().getString("MySQL.prepStmtCacheSize") );
        mySQLConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , PunishmentManager.giveConfig().getString("MySQL.prepStmtCacheSqlLimit") );
        mysqlDataSource = new HikariDataSource( mySQLConfig );
        SqlDatabaseConnection connection = new SqlDatabaseConnection(
                mysqlDataSource,
                host,
                username,
                password,
                port,
                db,
                new HikariConnectionHandler());
        sourceConnection = connection;
    }
}
