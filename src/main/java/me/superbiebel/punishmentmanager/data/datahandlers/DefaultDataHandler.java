package me.superbiebel.punishmentmanager.data.datahandlers;

import me.superbiebel.punishmentmanager.data.cache.Cache;
import me.superbiebel.punishmentmanager.data.databases.Database;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class DefaultDataHandler implements DataHandler{
    private static Database db;
    private static Cache cache;





    public void init() throws Exception{
        Log.debug("init called in DataHandler",false,true,true);
        Log.debug("DataHandler init completed",false,true,true);
    }


    public void close() throws Exception {

    }

    @Override
    public void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult) throws Exception {
        
    }

    @Override
    public void insertLeave(UUID uuid, String leaveMessage) throws Exception {

    }

    @Override
    public void insertKick(UUID uuid, String kickMessage) throws Exception {

    }
}










/*public static ResultSet FetchOffenseListGuiData(boolean forceCache){

       /*Promise<ResultSet> offenseListGuiDataPromise = null;

       offenseListGuiDataPromise = Promise.start().thenApplyAsync((e)->{
          ResultSet rst = null;
          try {
             PreparedStatement stmt = inCache ? CacheManager.getCacheDataSource().getConnection().prepareStatement("")
                     : MySQL.getMysqlDataSource().getConnection().prepareStatement("");
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
   }*/