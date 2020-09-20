package me.superbiebel.punishmentmanager.mysql;

import me.lucko.helper.promise.Promise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class FetchData {

    public static void setupDataFetching() {
        
    }

   public static ResultSet FetchOffenseListGuiData() throws ExecutionException, InterruptedException {
       Promise<ResultSet> offenseListGuiDataPromise = null;

       offenseListGuiDataPromise = Promise.start().thenApplyAsync((e)->{
          ResultSet rst = null;
          try {
             PreparedStatement stmt = MySQL.getDataSource().getConnection().prepareStatement("");
             rst = stmt.executeQuery();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
      return rst;
       });
       return offenseListGuiDataPromise.get();
   }
}
