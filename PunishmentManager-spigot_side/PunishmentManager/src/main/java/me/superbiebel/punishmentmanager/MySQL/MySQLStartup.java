package me.superbiebel.punishmentmanager.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.Utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLStartup {

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
        /*Schedulers.async().run(() -> {
            try {
                Connection con = dataSource.getConnection();
                PreparedStatement pst = con.prepareStatement(initupdate);
                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });*/
        Promise<Void> initPromise = Promise.start()
                .thenRunSync(() ->
            Log.debug("Initializing tables")).thenRunAsync(() -> {try {
            Connection con = dataSource.getConnection();
            PreparedStatement PcreatePunishment_templates = con.prepareStatement(createPunishment_templates);
            PcreatePunishment_templates.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }).thenRunSync(() -> Log.debug("Tables initialized"));


    }
    private static final String createPunishment_templates = "";}