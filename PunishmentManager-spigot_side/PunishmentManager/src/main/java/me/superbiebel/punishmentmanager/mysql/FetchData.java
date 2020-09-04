package me.superbiebel.punishmentmanager.mysql;

import me.lucko.helper.Schedulers;
import me.lucko.helper.promise.Promise;
import me.lucko.helper.scheduler.Scheduler;
import me.superbiebel.punishmentmanager.utils.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchData {

    public ResultSet fetchDataWithSql(String sqlStmt) throws SQLException {
            ResultSet result = null;
            PreparedStatement pst = null;
            Promise<Void> fetchDataWithSqlPromise = Promise.start().thenRunSync(()->{
                Log.debug("fetching data with sql statement:\n" + sqlStmt);
            });
            try {
                pst = MySQL.getDataSource().getConnection().prepareStatement("");
                result = pst.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return result;

    }

    public static void fetchAllDataToCache() {
        //put all methods to fetch all data that needs to be cached
    }
}
