package me.superbiebel.punishmentmanager.data;

import me.lucko.helper.promise.Promise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class FetchData {

    public static ResultSet FetchOffenseListGuiData() {
       Promise<ResultSet> offenseListGuiDataPromise = null;

       offenseListGuiDataPromise = Promise.start().thenApplyAsync((e)->{
          ResultSet rst = null;
          try {
             PreparedStatement stmt = MySQL.getMysqlDataSource().getConnection().prepareStatement("");
             rst = stmt.executeQuery();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
      return rst;
       });
       try {
           return offenseListGuiDataPromise.get();
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       }
       return null;
   }
}
