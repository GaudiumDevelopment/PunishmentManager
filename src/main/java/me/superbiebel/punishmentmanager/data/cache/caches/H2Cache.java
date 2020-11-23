package me.superbiebel.punishmentmanager.data.cache.caches;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.superbiebel.punishmentmanager.data.cache.Cache;
import me.superbiebel.punishmentmanager.utils.Log;

import java.sql.Statement;
@Deprecated 
public class H2Cache implements Cache {

    private static HikariConfig cacheDataSourceConfig;
    private static HikariDataSource cacheDataSource;

    @Override
    public void init() throws Exception {
        //actually stolen code from devLeoko, go check out his spigot and his awesome map plugin. He is truly a pro dev.
        String driverClassName = "org.hsqldb.jdbc.JDBCDriver";
        Class.forName(driverClassName);
        cacheDataSourceConfig = new HikariConfig();
        cacheDataSourceConfig.setDriverClassName(driverClassName);
        cacheDataSourceConfig.setJdbcUrl("jdbc:hsqldb:mem:hsqldb.lock_file=false");
        cacheDataSourceConfig.setUsername("PunishmentManager");
        cacheDataSourceConfig.setPassword("");
        Log.info("Don't worry about the error about a driver not supported, this is alright. Do NOT report this to my github!",false,true,true);
        cacheDataSource = new HikariDataSource(cacheDataSourceConfig);
        Log.debug("Statements sent to cache: " + initializeTables(), false,true,true);
    }
    public int initializeTables() throws Exception {
        Statement statement = cacheDataSource.getConnection().createStatement();
        statement.addBatch("CREATE TABLE offense_template ( offense_id int not null, offense_name varchar(30) not null, offense_icon varchar(30) not null, offense_lore varchar(200));");
        int[] res = statement.executeBatch();
        return res.length;
    }

    @Override
    public void transfer() throws Exception {

    }

    @Override
    public void close() throws Exception {
        cacheDataSource.close();
    }
}
