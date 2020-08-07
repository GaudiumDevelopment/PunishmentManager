package me.superbiebel.punishmentmanager.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Startup {
        private static HikariConfig MySQLConfig;
        private static HikariDataSource dataSource;

        public static void configureConnection() {
            MySQLConfig.setJdbcUrl( "jdbc_url" );
            MySQLConfig.setUsername( "database_username" );
            MySQLConfig.setPassword( "database_password" );
            MySQLConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
            MySQLConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            MySQLConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            dataSource = new HikariDataSource( MySQLConfig );
        }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
