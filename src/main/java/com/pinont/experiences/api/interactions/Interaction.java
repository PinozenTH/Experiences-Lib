package com.pinont.experiences.api.interactions;


import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

@Getter
public abstract class Interaction {

    private final NamespacedKey key;
    private final UUID uuid;

    public Interaction(NamespacedKey namespacedKey, UUID uuid) {
        key = namespacedKey;
        this.uuid = uuid;
    }

    public abstract void addInteraction(PlayerInteractEvent interaction);

}
