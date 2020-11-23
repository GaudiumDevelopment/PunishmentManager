package me.superbiebel.punishmentmanager.data.datahandlers;

import lombok.Getter;

public class DataHandlerManager {
    @Getter
    private static DataHandler dataHandler;
    public static DataHandler Instantiate(String className) throws Exception {
        Class clazz = Class.forName(className);
        dataHandler = (DataHandler) clazz.getDeclaredConstructor().newInstance();
        dataHandler.init();
        return dataHandler;
    }
}
