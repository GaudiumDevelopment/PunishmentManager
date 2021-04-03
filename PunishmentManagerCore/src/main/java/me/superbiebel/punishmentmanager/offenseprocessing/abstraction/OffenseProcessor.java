package me.superbiebel.punishmentmanager.offenseprocessing.abstraction;

import java.util.Map;
import java.util.UUID;

public interface OffenseProcessor {
    void execute(UUID uuid, Map<String,Object> args);
}
