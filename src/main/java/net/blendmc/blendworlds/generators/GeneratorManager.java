package net.blendmc.blendworlds.generators;

import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneratorManager {

    // BaseGenerator so we get the advantages of getName();
    private Set<BaseGenerator> generatorSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            new VoidGenerator()
    )));
    private static volatile GeneratorManager instance;

    private GeneratorManager() {}

    public static GeneratorManager getInstance() {
        GeneratorManager result = instance;

        // DCL to avoid race conditions between multiple threads.
        if (result != null) return result;
        synchronized(GeneratorManager.class) {
            if (instance == null) {
                instance = new GeneratorManager();
            }
            return instance;
        }
    }

    public ChunkGenerator getGenerator(String name) {
        return this.generatorSet.stream().filter(generator -> generator.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    /**
     * Get all generators registered under this plugin including the Minecraft vanilla ones.
     * @return String - all generator names registered in this plugin and vanilla: VOID, FLAT, AMPLIFIED, LARGE_BIOMES, NORMAL.
     */
    public Set<String> getGenerators() {
        // Add our own generators;
        Set<String> generators = this.generatorSet.stream().map(BaseGenerator::getName).collect(Collectors.toSet());

        // Add minecraft defaults
        generators.addAll(Arrays.stream(WorldType.values()).map(WorldType::getName).toList());
        return generators;
    }


    /**
     * Get all environments from the game.
     * @return String - all the environments registered in the game: NORMAL, NETHER, END
     */
    public Set<String> getEnvironments() {
        Set<String> environments = new HashSet<>();
        environments.addAll(Arrays.stream(World.Environment.values()).map(World.Environment::toString).toList());
        return environments;
    }

}
