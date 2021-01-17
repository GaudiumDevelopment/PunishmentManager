package me.superbiebel.punishmentmanager.offenseprocessing.abstraction;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.ReflectionUtils;

public class OffenseProcessorFactoryManager {
    @Getter
    private static OffenseProcessorFactory offenseProcessorFactory;

    public static void instantiate() throws Exception {
        String className = PunishmentManager.giveConfig().getString("offenseprocessor.type");
        offenseProcessorFactory = (OffenseProcessorFactory) ReflectionUtils.stringToInstance(className, Log.LogLevel.DEBUG);
    }
}
