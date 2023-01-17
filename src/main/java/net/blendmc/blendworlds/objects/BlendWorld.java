package net.blendmc.blendworlds.objects;

import lombok.Getter;
import net.blendmc.blendworlds.generators.GeneratorManager;

import lombok.Builder;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;

@Builder
public class BlendWorld {

    @Getter private final String name;
    private final Difficulty difficulty;
    private final World.Environment environment;
    private final String generationType;
    private final long seed;
    private Location spawn;

    @Getter private World world;

    // TODO: Check if the world hasn't been created beforehand through the command.
    public BlendWorld build() {
        WorldCreator worldCreator = new WorldCreator(this.name);
        worldCreator.environment(this.environment);

        // Let's attempt to search the generation picked in the generator manager.
        ChunkGenerator chunkGenerator = GeneratorManager.getInstance().getGenerator(this.generationType);
        if (chunkGenerator != null) {
            worldCreator.generator(chunkGenerator);
        } else { // If no generation was found, let's try using the minecraft default values instead.
            worldCreator.type(WorldType.valueOf(this.generationType.toUpperCase()));
        }

        this.world = Bukkit.createWorld(worldCreator);
        this.world.setDifficulty(this.difficulty);
        this.world.setSpawnLocation(this.spawn);
        return this;
    }

}