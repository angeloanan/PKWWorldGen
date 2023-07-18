package xyz.angeloanan.pkwsubmissionutils.ChunkGen;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.angeloanan.pkwsubmissionutils.PKWPlot;

import java.util.Random;

public class Generator extends ChunkGenerator {
  int STRUCTURE_PATTERN_CHUNK_SIZE_X = PKWPlot.PLOT_CHUNK_SIZE_X;
  int STRUCTURE_PATTERN_CHUNK_SIZE_Z = PKWPlot.PLOT_CHUNK_SIZE_Z;

  int STRUCTURE_PATTERN_LOWEST_Y = PKWPlot.STRUCTURE_LOWEST_Y;

  // ---

  public static org.bukkit.generator.BiomeProvider BIOME_PROVIDER = new BiomeProvider();

  @Override
  public @Nullable org.bukkit.generator.BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
    return BIOME_PROVIDER;
  }

  @Override
  public @Nullable Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
    return new Location(world, 0, STRUCTURE_PATTERN_LOWEST_Y + 13, 11);
  }

  // Run in every chunk
  public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {

    int relativePatternX = chunkX >= 0
      ? (chunkX % STRUCTURE_PATTERN_CHUNK_SIZE_X) + 1
      : ((chunkX + 1) % STRUCTURE_PATTERN_CHUNK_SIZE_X) + STRUCTURE_PATTERN_CHUNK_SIZE_X;
    int relativePatternZ = chunkZ >= 0
      ? (chunkZ % STRUCTURE_PATTERN_CHUNK_SIZE_Z) + 1
      : ((chunkZ + 1) % STRUCTURE_PATTERN_CHUNK_SIZE_Z) + STRUCTURE_PATTERN_CHUNK_SIZE_Z;

    // Generate boxes

    // Left border
    if (relativePatternX <= 3 && relativePatternZ == 1) {
      // Border left
      for (int x = 0; x < 16; x++) {
        // Bottom
        chunkData.setBlock(x, STRUCTURE_PATTERN_LOWEST_Y, 0, Material.RED_CONCRETE);
        // Top
        chunkData.setBlock(x, STRUCTURE_PATTERN_LOWEST_Y + 28, 0, Material.RED_CONCRETE);
      }
    }

    // Right border
    if (relativePatternX <= 3 && relativePatternZ == 2) {
      // Border right
      for (int x = 0; x < 16; x++) {
        // Bottom
        chunkData.setBlock(x, STRUCTURE_PATTERN_LOWEST_Y, 6, Material.RED_CONCRETE);
        // Top
        chunkData.setBlock(x, STRUCTURE_PATTERN_LOWEST_Y + 28, 6, Material.RED_CONCRETE);
      }
    }

    // Front border
    if (relativePatternX == 1) {
      if (relativePatternZ == 1) {
        for (int z = 0; z < 16; z++) {
          // Bottom
          chunkData.setBlock(0, STRUCTURE_PATTERN_LOWEST_Y, z, Material.RED_CONCRETE);
          // Top
          chunkData.setBlock(0, STRUCTURE_PATTERN_LOWEST_Y + 28, z, Material.RED_CONCRETE);
        }
      } else if (relativePatternZ == 2) {
        for (int z = 0; z < 6; z++) {
          // Bottom
          chunkData.setBlock(0, STRUCTURE_PATTERN_LOWEST_Y, z, Material.RED_CONCRETE);
          // Top
          chunkData.setBlock(0, STRUCTURE_PATTERN_LOWEST_Y + 28, z, Material.RED_CONCRETE);
        }
      }
    } else if (relativePatternX == 3) { // Back border
      if (relativePatternZ == 1) {
        for (int z = 0; z < 16; z++) {
          // Bottom
          chunkData.setBlock(15, STRUCTURE_PATTERN_LOWEST_Y, z, Material.RED_CONCRETE);
          // Top
          chunkData.setBlock(15, STRUCTURE_PATTERN_LOWEST_Y + 28, z, Material.RED_CONCRETE);
        }
      } else if (relativePatternZ == 2) {
        for (int z = 0; z < 6; z++) {
          // Bottom
          chunkData.setBlock(15, STRUCTURE_PATTERN_LOWEST_Y, z, Material.RED_CONCRETE);
          // Top
          chunkData.setBlock(15, STRUCTURE_PATTERN_LOWEST_Y + 28, z, Material.RED_CONCRETE);
        }
      }
    }

    // Vertical Border
    // Left
    if (relativePatternZ == 1) {
      if (relativePatternX == 1) {
        for (int y = STRUCTURE_PATTERN_LOWEST_Y; y < STRUCTURE_PATTERN_LOWEST_Y + 28; y++) {
          chunkData.setBlock(0, y, 0, Material.RED_CONCRETE);
        }
      } else if (relativePatternX == 3) {
        for (int y = STRUCTURE_PATTERN_LOWEST_Y; y < STRUCTURE_PATTERN_LOWEST_Y + 28; y++) {
          chunkData.setBlock(15, y, 0, Material.RED_CONCRETE);
        }
      }
    } else if (relativePatternZ == 2) { // Right
      if (relativePatternX == 1) {
        for (int y = STRUCTURE_PATTERN_LOWEST_Y; y < STRUCTURE_PATTERN_LOWEST_Y + 28; y++) {
          chunkData.setBlock(0, y, 6, Material.RED_CONCRETE);
        }
      } else if (relativePatternX == 3) {
        for (int y = STRUCTURE_PATTERN_LOWEST_Y; y < STRUCTURE_PATTERN_LOWEST_Y + 28; y++) {
          chunkData.setBlock(15, y, 6, Material.RED_CONCRETE);
        }
      }
    }

    // Starting Golden Blocks
    if (relativePatternX == 1 && relativePatternZ == 1) {
      chunkData.setBlock(0, STRUCTURE_PATTERN_LOWEST_Y + 11, 11, Material.GOLD_BLOCK);
    }

    // Starting blocks for other chunk
    if (relativePatternX == 5 && relativePatternZ <= 2) {
      for (int z = 0; z < (relativePatternZ == 1 ? 16 : 7); z++) {
        chunkData.setBlock(15, STRUCTURE_PATTERN_LOWEST_Y + 11, z, Material.RED_STAINED_GLASS);
        chunkData.setBlock(14, STRUCTURE_PATTERN_LOWEST_Y + 11, z, Material.WHITE_STAINED_GLASS);
        chunkData.setBlock(13, STRUCTURE_PATTERN_LOWEST_Y + 11, z, Material.WHITE_STAINED_GLASS);
        chunkData.setBlock(12, STRUCTURE_PATTERN_LOWEST_Y + 11, z, Material.WHITE_STAINED_GLASS);
      }
    }


    // Redstone Blocks
    // Bottom right
    if (relativePatternX == 1 && relativePatternZ == 2) {
      chunkData.setBlock(1, STRUCTURE_PATTERN_LOWEST_Y, 5, Material.REDSTONE_BLOCK);
    } else if (relativePatternX == 3 && relativePatternZ == 1) {
      chunkData.setBlock(14, STRUCTURE_PATTERN_LOWEST_Y + 27, 1, Material.REDSTONE_BLOCK);
    }

    //
    // Screenshot blocks
    //

    // Screenshot 1 of other chunk ✅
    if (relativePatternX == 5 && relativePatternZ == 1) {
      chunkData.setBlock(8, STRUCTURE_PATTERN_LOWEST_Y + 16, 11, Material.WAXED_CUT_COPPER);
    }

    // Screenshot 2 of other chunk (left)
    if (relativePatternX == 2 && relativePatternZ == 4) {
      chunkData.setBlock(7, STRUCTURE_PATTERN_LOWEST_Y + 16, 0, Material.WAXED_CUT_COPPER);
    }

    // Screenshot 3 - Right ✅
    if (relativePatternX == 2 && relativePatternZ == 3) {
      chunkData.setBlock(7, STRUCTURE_PATTERN_LOWEST_Y + 16, 6, Material.WAXED_CUT_COPPER);
    }

    // Screenshot 4 - Opposite ✅
    if (relativePatternX == 4 && relativePatternZ == 1) {
      chunkData.setBlock(13, STRUCTURE_PATTERN_LOWEST_Y + 16, 11, Material.WAXED_COPPER_BLOCK);
    }

    // Screenshot 5 - Above ✅
    if (relativePatternX == 2 && relativePatternZ == 1) {
      chunkData.setBlock(6, STRUCTURE_PATTERN_LOWEST_Y + 28 + 10, 11, Material.BARRIER);
      chunkData.setBlock(6, STRUCTURE_PATTERN_LOWEST_Y + 28 + 10 + 3, 11, Material.WAXED_CUT_COPPER);
    }
  }
}
