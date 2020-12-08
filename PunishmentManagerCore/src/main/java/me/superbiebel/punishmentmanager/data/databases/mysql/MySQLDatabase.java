package me.superbiebel.punishmentmanager.data.databases.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.simplix.core.database.sql.SqlDatabaseConnection;
import dev.simplix.core.database.sql.handlers.HikariConnectionHandler;
import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.databases.Database;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class MySQLDatabase implements Database {
    public static boolean isInitialized = false;
    private static HikariConfig mySQLConfig;
    @Getter
    private static HikariDataSource mysqlDataSource;
    @Getter
    private static SqlDatabaseConnection sourceConnection;
    private static final String host = PunishmentManager.giveConfig().getString("MySQL.host");
    private static final String username = PunishmentManager.giveConfig().getString("MySQL.username");
    private static final String password = PunishmentManager.giveConfig().getString("MySQL.password");
    private static final String port = PunishmentManager.giveConfig().getString("MySQL.port");
    private static final String db = PunishmentManager.giveConfig().getString("MySQL.db");
    private static final String useSSL = PunishmentManager.giveConfig().getString("MySQL.useSSL");
    public MySQLDatabase() {
        if (isInitialized) {
            throw new IllegalStateException("An instance of this class already exists!");
        } else {
            isInitialized = true;
        }
    }

    public static SqlDatabaseConnection initializeDatabase() {
        mySQLConfig = new HikariConfig();
        mySQLConfig.setJdbcUrl( "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=" + useSSL);

        Log.debug("jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=" + useSSL,false, true,true);
        mySQLConfig.setUsername( username );
        mySQLConfig.setPassword( password );
        mySQLConfig.addDataSourceProperty( "cachePrepStmts" , PunishmentManager.giveConfig().getOrDefault("MySQL.cachePrepStmts","true"));
        mySQLConfig.addDataSourceProperty( "prepStmtCacheSize" , PunishmentManager.giveConfig().getOrDefault("MySQL.prepStmtCacheSize","250") );
        mySQLConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , PunishmentManager.giveConfig().getOrDefault("MySQL.prepStmtCacheSqlLimit","2048") );
        mysqlDataSource = new HikariDataSource( mySQLConfig );
        sourceConnection = new SqlDatabaseConnection(
                mysqlDataSource,
                host,
                username,
                password,
                port,
                db,
                new HikariConnectionHandler());
        return sourceConnection;
    }
    public static int initializeTables(SqlDatabaseConnection dbconnection) throws SQLException {
        Connection con = dbconnection.getDataSource().getConnection();
        Statement stmt = con.createStatement();

        con = mysqlDataSource.getConnection();
        con.setAutoCommit(false);
        SQL_QUERIES.init(dbconnection.getData());
        stmt.addBatch(SQL_QUERIES.getCategoriesTable());
        stmt.addBatch(SQL_QUERIES.getCategory_data_playersTable());
        stmt.addBatch(SQL_QUERIES.getCategory_points_addedTable());
        stmt.addBatch(SQL_QUERIES.getJailsTable());
        stmt.addBatch(SQL_QUERIES.getOffense_category_id_connectionTable());
        stmt.addBatch(SQL_QUERIES.getOffense_templateTable());
        stmt.addBatch(SQL_QUERIES.getPlayer_joinsTable());
        stmt.addBatch(SQL_QUERIES.getPlayer_leavesTable());
        stmt.addBatch(SQL_QUERIES.getPlayer_IPTable());
        stmt.addBatch(SQL_QUERIES.getPlayer_dataTable());
        stmt.addBatch(SQL_QUERIES.getPlayer_historyTable());
        stmt.addBatch(SQL_QUERIES.getPunishment_templatesTable());
        stmt.addBatch(SQL_QUERIES.getPunishment_usedTable());
        stmt.addBatch(SQL_QUERIES.getScriptTable());
        int[] res = stmt.executeBatch();
        Log.debug("updates sent: " + res.length,false, true,true);
        con.commit();
        return res.length;
    }

    @Override
    public void init() throws Exception {
        initializeTables(initializeDatabase());
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public void fetchOffenselistGUIData() throws Exception {

    }

    @Override
    public void fetchHistoryGUIData() throws Exception {

    }

    @Override
    public ArrayList fetchBannedPlayers() throws Exception {
        return null;
    }
    
    @Override
    public void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception {
    
    }

    @Override
    public void insertIp(String ip) throws Exception {

    }
    
    @Override
    public void insertLeave(UUID uuid, String leaveMessage) throws Exception {
    
    }
    
    @Override
    public void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception {
    
    }
    
}
