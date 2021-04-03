package me.superbiebel.punishmentmanager.punishsystem;

import java.util.UUID;
import me.superbiebel.punishmentmanager.offenseprocessing.abstraction.OffenseProcessorFactoryManager;

public class PunishSystem {

    public void init() throws Exception {
        OffenseProcessorFactoryManager.instantiate();
        OffenseProcessorFactoryManager.getOffenseProcessorFactory().init();
    }


    public void triggerOffense(int offenseId, UUID playeruuid, Object[] args) throws Exception {
        
    }
}
