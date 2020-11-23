package me.superbiebel.punishmentmanager.data.databases.mysql;

@Deprecated
public class MySQL {
}

    /*private static HikariConfig mySQLConfig;
    @Getter
    private static HikariDataSource mysqlDataSource;
    @Getter
    private static SqlDatabaseConnection sourceConnection;

    private MySQL() {
        //shouldn't be used tbh
    }
    public static void start(String host, String username, String password, String port, String db, String useSSL) throws SQLException {
        initializeTables(instantiate(host, username, password, port, db, useSSL));
    }

    public static SqlDatabaseConnection instantiate(String host, String username, String password, String port, String db, String useSSL) {

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
            Log.debug("updates sent: " + res.length,false, true,true,"");
            con.commit();
        return res.length;
    }





    /*public static void initializeTables(String db) {
        if (db == null) {
                Log.fatalError("db is not set!!!",false,true,true,"");
                Log.fatalError("Please check your config and restart your server!",false,true,true,"");
            } else {

            Log.debug("Initializing tables",false,true,true,"");
                Connection con = null;
               try {
                     final String createCategoriesTable = "CREATE TABLE IF NOT EXISTS " + db + ".categories ( `category_id` int NOT NULL , `category_name` varchar(30) NOT NULL );";
                     final String createCategory_data_playersTable = "CREATE TABLE IF NOT EXISTS " + db + ".`category_data_players` ( uuid varchar(50) NOT NULL , `category_id` int NOT NULL , `category_points` int NOT NULL );";
                     final String createcategory_points_addedTable = "CREATE TABLE IF NOT EXISTS " + db + ".category_points_added ( uuid varchar(50) , category_id int , history_id int , category_points_added int , offense_id int );";
                     final String createJailsTable = "CREATE TABLE IF NOT EXISTS " + db + ".jails ( jail_id int NOT NULL , jail_location_x int , jail_location_y int , jail_location_z int , server varchar(200) );";
                     final String createOffense_category_id_connectionTable = "CREATE TABLE IF NOT EXISTS " + db + ".`offense_categoryid_connection` ( `category_id` int NOT NULL , `offense_id` int NOT NULL , points int NOT NULL );";
                     final String createOffense_templateTable = "CREATE TABLE IF NOT EXISTS " + db + ".`offense_template` ( `offense_id` int NOT NULL , `offense_name` varchar(30) NOT NULL , `offense_icon` varchar(30) NOT NULL, `offense_lore` TEXT );";
                     final String createPlayer_joinsTable = "CREATE TABLE IF NOT EXISTS "+ db + ".`player_joins` ( uuid varchar(50) NOT NULL , `join_date` bigint UNSIGNED NOT NULL, result varchar(20) NOT NULL, ip varchar(20) NOT NULL );";
                     final String createPlayer_leavesTable = "CREATE TABLE IF NOT EXISTS "+ db + ".`player_leaves` ( uuid varchar(50) NOT NULL , `leave_date` bigint UNSIGNED NOT NULL );";
                     final String createPlayer_IPTable = "CREATE TABLE IF NOT EXISTS " + db + ".`player_IP` ( `IP` varchar(18) NOT NULL , uuid varchar(50) NOT NULL , `creation_date` bigint UNSIGNED NOT NULL );";
                     final String createPlayer_dataTable = "CREATE TABLE IF NOT EXISTS " + db + ".player_data ( uuid varchar(50) NOT NULL , player_name varchar(20) NOT NULL );";
                     final String createPlayer_historyTable = "CREATE TABLE IF NOT EXISTS " + db + ".player_history ( history_id int NOT NULL , uuid_victim varchar(50) NOT NULL , uuid_executor varchar(50) NOT NULL , formatted_reason varchar(200) , calculated_ban_duration bigint , calculated_jail_duration bigint , calculated_mute_duration bigint , jail_id int , time_done_mute bigint UNSIGNED , time_done_jail bigint , status varchar(200) NOT NULL );";
                     final String createPunishment_templatesTable = "CREATE TABLE IF NOT EXISTS " + db + ".punishment_templates ( `punishment_id` int NOT NULL , threshold int NOT NULL , `category_id` int NOT NULL , `mute_calculation` varchar(200) , `IP_mute_calculation` varchar(200) ,`ban_calculation` varchar(200) ,`IP_ban_calculation` varchar(200) , `jail_calculation` varchar(200) , server varchar(200) );";
                     final String createPunishment_usedTable = "CREATE TABLE IF NOT EXISTS " + db + ".punishment_used ( history_id int , punishment_id int );";
                     final String createScriptTable = "CREATE TABLE IF NOT EXISTS " + db + ".scripts (script_id int, script TEXT);";
            con = mysqlDataSource.getConnection();
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
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
            stmt.addBatch(createScriptTable);
            int[] res = stmt.executeBatch();
            Log.debug("updates sent: " + res.length,false, true,true,"");
            con.commit();
        } catch (SQLException throwables) {
                    throwables.printStackTrace();
               } finally {
                   try {
                       if (con != null) {
                       con.close();}
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
               }
    Log.debug("Tables initialized",false, true,true,"");
            }
    }*/
//}