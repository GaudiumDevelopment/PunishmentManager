package me.superbiebel.punishmentmanager.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Startup {
        private static HikariConfig MySQLConfig;

    private static HikariDataSource dataSource;

        public static void configureConnection(String host, String username, String password, int port, String db, Boolean useSSL) {
            MySQLConfig.setJdbcUrl( "jdbc:mysql://" + host + ":" + port + "/" + db + "useSSL=useSSL");
            MySQLConfig.setUsername( username );
            MySQLConfig.setPassword( password );
            MySQLConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
            MySQLConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            MySQLConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            dataSource = new HikariDataSource( MySQLConfig );

        }
    public static HikariDataSource getDataSource() {return dataSource;}
}
