package me.superbiebel.punishmentmanager.offenseprocessing.abstraction;

import java.util.UUID;

public interface OffenseProcessor {
    void execute(UUID uuid,  Object[] args);
}
