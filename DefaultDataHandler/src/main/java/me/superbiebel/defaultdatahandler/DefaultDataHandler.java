package me.superbiebel.defaultdatahandler;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishmentmanager.data.AVAILABILITY;
import me.superbiebel.punishmentmanager.data.abstraction.depracated.Cache;
import me.superbiebel.punishmentmanager.data.abstraction.depracated.DataHandler;
import me.superbiebel.punishmentmanager.data.abstraction.depracated.Database;
import me.superbiebel.punishmentmanager.data.dataObjects.HistoryRecord;
import me.superbiebel.punishmentmanager.data.dataObjects.OffenseKey;
import me.superbiebel.punishmentmanager.data.providers.DatabaseProvider;
import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class DefaultDataHandler implements DataHandler {
    private static Database db = DatabaseProvider.getDatabase();
    private static Cache cache;

    @Override
    public void init() throws Exception{
        Log.debug("init called in DataHandler",false,true,true);
        Log.debug("DataHandler init completed",false,true,true);
    }

    @Override
    public void shutdown() throws Exception {

    }

    @Override
    public void insertJoin(UUID uuid, String joinMessage, String kickMessage, AsyncPlayerPreLoginEvent.Result loginresult, InetAddress IP) throws Exception {
    
    }

    @Override
    public void insertLeave(UUID uuid, String leaveMessage) throws Exception {

    }
    
    @Override
    public void insertKick(UUID uuid, String kickreason, String leaveMessage) throws Exception {
    
    }
    
    @Override
    public <T> T getCachedInventory(String key) {
        return null;
    }
    
    @Override
    public <T> void putCachedInventory(String key, AVAILABILITY availability, T inventory) {
    
    }
    
    @Override
    public List<HistoryRecord> getHistory(UUID uuid, boolean nocache) {
        return null;
    }
    
    @Override
    public List<OffenseKey> getAllOffenseKeys() {
        return null;
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