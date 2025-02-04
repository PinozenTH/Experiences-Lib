package com.pinont.experiences.api.interactions;

import java.util.ArrayList;
import java.util.List;

public class InteractionManager {

    private final List<Interaction> interactions = new ArrayList<>();

    public InteractionManager(Interaction interaction) {
        interactions.add(interaction);
    }

}
