package net.blendmc.blendworlds.generators;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;


public class VoidGenerator extends BaseGenerator {

    public final ChunkData generateChunkData(World world, Random r, int ChunkX, int ChunkZ, ChunkGenerator.BiomeGrid biome) {
        ChunkGenerator.ChunkData chunkData = createChunkData(world);
        if (ChunkX == 0 && ChunkZ == 0)
            chunkData.setBlock(0, 64, 0, Material.STONE.createBlockData());
        return chunkData;
    }

    @Override
    public String getName() {
        return "VOID";
    }

}
