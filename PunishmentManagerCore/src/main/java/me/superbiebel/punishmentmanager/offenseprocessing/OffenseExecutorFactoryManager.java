package me.superbiebel.punishmentmanager.offenseprocessing;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;

public class OffenseExecutorFactoryManager {
    @Getter
    private static OffenseExecutorFactory offenseExecutorFactory;
    public static OffenseExecutorFactory instantiate() throws Exception {
        String className = PunishmentManager.giveConfig().getString("datahandler.choice");
        Log.debug("Instantiating OffenseProcessor!",false,true,true);
        Class clazz = Class.forName(className);
        offenseExecutorFactory = (OffenseExecutorFactory) clazz.getDeclaredConstructor().newInstance();
        return offenseExecutorFactory;
    }
}
