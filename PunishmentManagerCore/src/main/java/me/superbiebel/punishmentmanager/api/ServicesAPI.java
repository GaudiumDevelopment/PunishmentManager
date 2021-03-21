package me.superbiebel.punishmentmanager.api;

import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.data.abstraction.service.Service;

public class ServicesAPI {

    public void register(Service service) throws IllegalStateException{
        if (PunishmentManager.getServiceManager() == null){
            throw new IllegalStateException("ServiceManager not yet instantiated");
        }
        PunishmentManager.getServiceManager().registerService(service);
    }
}
