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
            hikariConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
            hikariConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            hikariConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            dataSource = new HikariDataSource( hikariConfig );
            MySQLConfig = hikariConfig;
        }
    public static HikariDataSource getDataSource() {return dataSource;}
    public static void initializeTables() {
        Promise<Void> initPromise = Promise.start()
                .thenRunAsync(() ->
            Log.debug("Initializing tables")).thenRunAsync(() -> {
                try {
            Connection con = dataSource.getConnection();
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            stmt.addBatch(createCategoriesTable);
            stmt.addBatch(createCategory_data_playersTable);
            stmt.addBatch(createcategory_points_addedTable);
            stmt.addBatch(createJailsTable);
            stmt.addBatch(createOffense_category_id_connectionTable);
            stmt.addBatch(createOffense_templateTable);
            stmt.addBatch(createPlayer_joinsLeavesTable);
            stmt.addBatch(createPlayer_IPTable);
            stmt.addBatch(createPlayer_dataTable);
            stmt.addBatch(createPlayer_historyTable);
            stmt.addBatch(createPunishment_templatesTable);
            stmt.addBatch(createPunishment_usedTable);
            stmt.addBatch(createOffenseLoreTable);
            int[] res = stmt.executeBatch();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }).thenRunAsync(() -> Log.debug("Tables initialized"));


    }
    private static final String createCategoriesTable = "CREATE TABLE IF NOT EXISTS minecraft.categories ( `category_id`        int  NOT NULL    , `category_name`      varchar(30)  NOT NULL );";
    private static final String createCategory_data_playersTable = "CREATE TABLE IF NOT EXISTS minecraft.`category_data_players` ( uuid                 varchar(50)  NOT NULL    , `category_id`        int  NOT NULL    , `category_points`    int  NOT NULL );";
    private static final String createcategory_points_addedTable = "CREATE TABLE IF NOT EXISTS minecraft.category_points_added ( uuid                 varchar(50)      , category_id          int      , history_id           int      , category_points_added int      , offense_id           int );";
    private static final String createJailsTable = "CREATE TABLE IF NOT EXISTS minecraft.jails ( jail_id              int  NOT NULL    , jail_location_x      int      , jail_location_y      int      , jail_location_z      int      , server               varchar(200) );";
    private static final String createOffense_category_id_connectionTable = "CREATE TABLE IF NOT EXISTS minecraft.`offense_categoryid_connection` ( `category_id`        int  NOT NULL    , `offense_id`         int  NOT NULL    , points               int  NOT NULL );";
    private static final String createOffense_templateTable = "CREATE TABLE IF NOT EXISTS minecraft.`offense_template` ( `offense_id`         int  NOT NULL    , `offense_name`       varchar(30)  NOT NULL    , `offense_icon`       varchar(30)  NOT NULL );";
    private static final String createPlayer_joinsLeavesTable = "CREATE TABLE IF NOT EXISTS minecraft.`player_joins/leaves` ( uuid                 varchar(50)  NOT NULL    , `join_date`          datetime  NOT NULL    , `leave_date`         datetime );";
    private static final String createPlayer_IPTable = "CREATE TABLE IF NOT EXISTS minecraft.`player_IP` ( `IP`                 varchar(18)  NOT NULL    , uuid                 varchar(50)  NOT NULL    , `creation_date`      datetime  NOT NULL );";
    private static final String createPlayer_dataTable = "CREATE TABLE IF NOT EXISTS minecraft.player_data ( uuid                 varchar(50)  NOT NULL    , playername           varchar(20)  NOT NULL );";
    private static final String createPlayer_historyTable = "CREATE TABLE IF NOT EXISTS minecraft.player_history ( history_id           int  NOT NULL    , uuid_victim          varchar(50)  NOT NULL    , uuid_executor        varchar(50)  NOT NULL    , formatted_reason     varchar(200)      , calculated_ban_duration bigint      , calculated_jail_duration bigint      , calculated_mute_duration bigint      , jail_id              int      , time_done_mute       bigint UNSIGNED     , time_done_jail       bigint      , status               varchar(200)  NOT NULL );";
    private static final String createPunishment_templatesTable = "CREATE TABLE IF NOT EXISTS minecraft.punishment_templates ( `punishment_id`      int  NOT NULL    , threshold            int  NOT NULL    , `category_id`        int  NOT NULL    , `mute_calculation`   varchar(200)      , `ban-_alculation`    varchar(200)      , `jail_calculation`   varchar(200)      , server               varchar(200) );";
    private static final String createPunishment_usedTable = "CREATE TABLE IF NOT EXISTS minecraft.punishment_used ( history_id           int      , punishment_id        int );";
    private static final String createOffenseLoreTable = "CREATE TABLE IF NOT EXISTS minecraft.offense_lore ( offense_id int      , lore                 varchar(200) )";
}