package me.superbiebel.punishmentmanager.data.managers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.layers.DataHandler;
import me.superbiebel.punishmentmanager.utils.Log;

public class DataHandlerManager {
    @Getter
    private static DataHandler dataHandler;
    public static DataHandler instantiate() throws Exception {
        //uses reflection to instantiate the data handler
        String className = PunishmentManager.giveConfig().getString("datahandler.choice");
        Log.debug("Instantiating datahandler!",false,true,true);
        Class clazz = Class.forName(className);
        dataHandler = (DataHandler) clazz.getDeclaredConstructor().newInstance();
        dataHandler.init();
        return dataHandler;
    }
}
