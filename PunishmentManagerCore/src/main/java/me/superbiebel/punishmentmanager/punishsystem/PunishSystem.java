package me.superbiebel.punishmentmanager.punishsystem;

import java.util.Map;
import java.util.UUID;
import me.superbiebel.punishmentmanager.offenseprocessing.abstraction.OffenseProcessor;
import me.superbiebel.punishmentmanager.offenseprocessing.abstraction.OffenseProcessorFactoryManager;

public class PunishSystem {

    private boolean initialized = false;

    public void init() throws Exception {
        OffenseProcessorFactoryManager.instantiate();
        OffenseProcessorFactoryManager.getOffenseProcessorFactory().init();
        initialized = true;
    }


    public void triggerOffense(int offenseId, UUID playeruuid, Map<String,Object> args) throws Exception {
        if (!initialized) {
            throw new IllegalStateException("Tried to trigger an offense but PunishSystem was not yet initialized");
        }
        OffenseProcessor offenseProcessor = OffenseProcessorFactoryManager.getOffenseProcessorFactory().giveOffenseProcessor();
        offenseProcessor.execute(playeruuid, args);
    }
}
