package net.blendmc.blendworlds.objects;

import java.util.HashSet;
import java.util.Set;

public class BlendWorldsManager {

    private Set<BlendWorld> worlds = new HashSet<>();
    private static volatile BlendWorldsManager instance;

    private BlendWorldsManager() {}

    public static BlendWorldsManager getInstance() {
        BlendWorldsManager result = instance;

        // DCL to avoid race conditions between multiple threads.
        if (result != null) return result;
        synchronized(BlendWorldsManager.class) {
            if (instance == null) {
                instance = new BlendWorldsManager();
            }
            return instance;
        }
    }

    public BlendWorld getBlendWorld(String name) {
        return this.worlds.stream().filter(world -> world.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
