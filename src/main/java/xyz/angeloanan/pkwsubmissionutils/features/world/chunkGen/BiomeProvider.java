package xyz.angeloanan.pkwsubmissionutils.features.world.chunkGen;

import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BiomeProvider extends org.bukkit.generator.BiomeProvider {
  static final List<Biome> BIOMES;

  static {
    BIOMES = new ArrayList<>();
    BIOMES.add(Biome.PLAINS);
  }

  @Override
  public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
    return Biome.PLAINS;
  }

  @Override
  public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
    return BIOMES;
  }
}
