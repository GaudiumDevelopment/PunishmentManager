package me.superbiebel.punishmentmanager.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.lucko.helper.Schedulers;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Cache {
    private static boolean isInitialized;
    private static HikariConfig cacheDataSourceConfig;
    private static HikariDataSource cacheDataSource;

    public static void initCache() {
        if (!isInitialized) {
            Schedulers.async().run(()-> {
                isInitialized = true;
                cacheDataSourceConfig = new HikariConfig();
                //stole some code from the AdvancedBan plugin from DevLeoko. This is actually his code but adapted to me.
                String driverClassName = "org.hsqldb.jdbc.JDBCDriver";
                try {
                    Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                cacheDataSourceConfig.setDriverClassName(driverClassName);
                cacheDataSourceConfig.setJdbcUrl("jdbc:hsqldb:mem:hsqldb.lock_file=false");
                cacheDataSourceConfig.setUsername("PunishmentManager");
                cacheDataSourceConfig.setPassword("");
                Log.info("Don't worry about the error about a driver not supported, this is alright. Do NOT report this to my github!",false,true,true,"");
                cacheDataSource = new HikariDataSource(cacheDataSourceConfig);
                try {
                    Statement statement = cacheDataSource.getConnection().createStatement();
                    statement.addBatch("create table offense_template ( offense_id int not null, offense_name varchar(30) not null, offense_icon varchar(30) not null, offense_lore text null );");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            });
        } else {
            Log.warning("Tried to initialize the cache, but it was already initialized, pls report to developer.",true,true,true,"");
        }
    }

    public static List<Integer> checkOffenseIdRange(int range, List<Integer> idList, Player p) {
        idList.clear();
        for (int i = 0; i <= range; i++) {
            if (p.hasPermission("punishmentmanager.offense.open." + String.valueOf(i))) {
                idList.add(i);
            }
        }
        return idList;
    }

    public static boolean saveOffenseListGuiDataToCache() throws SQLException {
    Statement statement = MySQL.getMysqlDataSource().getConnection().createStatement();
    ResultSet cacheResultSet = statement.executeQuery("SELECT `offense_id`,`offense_icon`,`offense_name`,`offense_lore` FROM offense_template");
    cacheResultSet.last();
    int max = cacheResultSet.getRow();
    cacheResultSet.first();




        return false;
    }


    public static HikariDataSource getCacheDataSource() {
        return cacheDataSource;
    }
}
