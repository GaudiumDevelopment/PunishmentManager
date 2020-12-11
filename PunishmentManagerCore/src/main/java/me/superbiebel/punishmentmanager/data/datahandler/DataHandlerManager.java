package me.superbiebel.punishmentmanager.data.datahandler;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;

public class DataHandlerManager {
    @Getter
    private static DataHandler dataHandler;
    public static DataHandler Instantiate() throws Exception {
        String className = PunishmentManager.giveConfig().getString("datahandler.choice");
        Log.debug("Instantiating datahandler!",false,true,true);
        Class clazz = Class.forName(className);
        dataHandler = (DataHandler) clazz.getDeclaredConstructor().newInstance();
        dataHandler.init();
        return dataHandler;
    }
}
