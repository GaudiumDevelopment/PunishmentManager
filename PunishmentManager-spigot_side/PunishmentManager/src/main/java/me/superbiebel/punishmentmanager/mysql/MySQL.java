package me.superbiebel.punishmentmanager.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.utils.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private static HikariConfig MySQLConfig;
    private static HikariDataSource dataSource;

        public static void configureConnection(String host, String username, String password, String port, String db, String useSSL) {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl( "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=" + useSSL);

            Log.debug("jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=" + useSSL);
            hikariConfig.setUsername( username );
            hikariConfig.setPassword( password );
            hikariConfig.addDataSourceProperty( "cachePrepStmts" , "MySQL.cachePrepStmts" );
            hikariConfig.addDataSourceProperty( "prepStmtCacheSize" , "MySQL.prepStmtCacheSize" );
            hikariConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "MySQL.prepStmtCacheSqlLimit" );
            dataSource = new HikariDataSource( hikariConfig );
            MySQLConfig = hikariConfig;
        }
    
    public static HikariDataSource getDataSource() {return dataSource;}
    public static void initializeTables(String db) {
            if (db == null) {
                Log.fatalError("db is not set!!!");
            } else {

        Promise<Void> initPromise = Promise.start()
                .thenRunAsync(() ->
            Log.debug("Initializing tables")).thenRunAsync(() -> {
                Connection con = null;
               try {
                     final String createCategoriesTable = "CREATE TABLE IF NOT EXISTS " + db + ".categories ( `category_id`        int  NOT NULL    , `category_name`      varchar(30)  NOT NULL );";
                     final String createCategory_data_playersTable = "CREATE TABLE IF NOT EXISTS " + db + ".`category_data_players` ( uuid                 varchar(50)  NOT NULL    , `category_id`        int  NOT NULL    , `category_points`    int  NOT NULL );";
                     final String createcategory_points_addedTable = "CREATE TABLE IF NOT EXISTS " + db + ".category_points_added ( uuid                 varchar(50)      , category_id          int      , history_id           int      , category_points_added int      , offense_id           int );";
                     final String createJailsTable = "CREATE TABLE IF NOT EXISTS " + db + ".jails ( jail_id              int  NOT NULL    , jail_location_x      int      , jail_location_y      int      , jail_location_z      int      , server               varchar(200) );";
                     final String createOffense_category_id_connectionTable = "CREATE TABLE IF NOT EXISTS " + db + ".`offense_categoryid_connection` ( `category_id`        int  NOT NULL    , `offense_id`         int  NOT NULL    , points               int  NOT NULL );";
                     final String createOffense_templateTable = "CREATE TABLE IF NOT EXISTS " + db + ".`offense_template` ( `offense_id`         int  NOT NULL    , `offense_name`       varchar(30)  NOT NULL    , `offense_icon`       varchar(30)  NOT NULL );";
                     final String createPlayer_joinsTable = "CREATE TABLE IF NOT EXISTS "+ db + ".`player_joins` ( uuid                 varchar(50)  NOT NULL    , `join_date`          bigint UNSIGNED  NOT NULL );";
                     final String createPlayer_leavesTable = "CREATE TABLE IF NOT EXISTS "+ db + ".`player_joins` ( uuid                 varchar(50)  NOT NULL    , `leave_date`          bigint UNSIGNED  NOT NULL );";
                     final String createPlayer_IPTable = "CREATE TABLE IF NOT EXISTS " + db + ".`player_IP` ( `IP`                 varchar(18)  NOT NULL    , uuid                 varchar(50)  NOT NULL    , `creation_date`      bigint UNSIGNED  NOT NULL );";
                     final String createPlayer_dataTable = "CREATE TABLE IF NOT EXISTS " + db + ".player_data ( uuid                 varchar(50)  NOT NULL    , player_name           varchar(20)  NOT NULL );";
                     final String createPlayer_historyTable = "CREATE TABLE IF NOT EXISTS " + db + ".player_history ( history_id           int  NOT NULL    , uuid_victim          varchar(50)  NOT NULL    , uuid_executor        varchar(50)  NOT NULL    , formatted_reason     varchar(200)      , calculated_ban_duration bigint      , calculated_jail_duration bigint      , calculated_mute_duration bigint      , jail_id              int      , time_done_mute       bigint UNSIGNED     , time_done_jail       bigint      , status               varchar(200)  NOT NULL );";
                     final String createPunishment_templatesTable = "CREATE TABLE IF NOT EXISTS " + db + ".punishment_templates ( `punishment_id`      int  NOT NULL    , threshold            int  NOT NULL    , `category_id`        int  NOT NULL    , `mute_calculation`   varchar(200)      , `ban-_alculation`    varchar(200)      , `jail_calculation`   varchar(200)      , server               varchar(200) );";
                     final String createPunishment_usedTable = "CREATE TABLE IF NOT EXISTS " + db + ".punishment_used ( history_id           int      , punishment_id        int );";
                     final String createOffenseLoreTable = "CREATE TABLE IF NOT EXISTS " + db + ".offense_lore ( offense_id int      , lore                 varchar(200) )";
            con = dataSource.getConnection();
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            stmt.addBatch(createCategoriesTable);
            stmt.addBatch(createCategory_data_playersTable);
            stmt.addBatch(createcategory_points_addedTable);
            stmt.addBatch(createJailsTable);
            stmt.addBatch(createOffense_category_id_connectionTable);
            stmt.addBatch(createOffense_templateTable);
            stmt.addBatch(createPlayer_joinsTable);
            stmt.addBatch(createPlayer_leavesTable);
            stmt.addBatch(createPlayer_IPTable);
            stmt.addBatch(createPlayer_dataTable);
            stmt.addBatch(createPlayer_historyTable);
            stmt.addBatch(createPunishment_templatesTable);
            stmt.addBatch(createPunishment_usedTable);
            stmt.addBatch(createOffenseLoreTable);
            int[] res = stmt.executeBatch();
            con.commit();


        } catch (SQLException throwables) {
                    throwables.printStackTrace();
               } finally {
                   try {
                       con.setAutoCommit(true);
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               }
    }).thenRunAsync(() -> Log.debug("Tables initialized"));}


    }

    
    
}