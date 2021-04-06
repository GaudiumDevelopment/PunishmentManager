package me.superbiebel.punishmentmanager.data.providers;

import lombok.Getter;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.abstraction.depracated.DataHandler;
import me.superbiebel.punishmentmanager.utils.Log;
import me.superbiebel.punishmentmanager.utils.ReflectionUtils;

@Deprecated(forRemoval = true)
public class DataHandlerProvider implements Provider {
    @Getter
    private static DataHandler dataHandler;
    @Override
    public void init() throws Exception {
        String pathToClass = PunishmentManager.giveConfig().getString("reflectionpath.datahandlerdriver");
        dataHandler = (DataHandler) ReflectionUtils.stringToInstance(pathToClass, Log.LogLevel.DEBUG);
        dataHandler.init();
    }
}
