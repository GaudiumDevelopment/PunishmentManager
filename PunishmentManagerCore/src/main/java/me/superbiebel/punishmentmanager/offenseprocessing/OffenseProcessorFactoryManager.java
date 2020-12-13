package me.superbiebel.punishmentmanager.offenseprocessing;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;

public class OffenseProcessorFactoryManager {
    @Getter
    private static OffenseProcessorFactory offenseProcessorFactory;
    public static OffenseProcessorFactory instantiate() throws Exception {
        String className = PunishmentManager.giveConfig().getString("offenseprocessor.type");
        Log.debug("Instantiating OffenseProcessorFactoryManager!",false,true,true);
        Class clazz = Class.forName(className);
        offenseProcessorFactory = (OffenseProcessorFactory) clazz.getDeclaredConstructor().newInstance();
        return offenseProcessorFactory;
    }
}
