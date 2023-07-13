package xyz.angeloanan.pkwworldgen;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class PKWChunkGen extends ChunkGenerator {
  static int STRUCTURE_PATTERN_CHUNK_SIZE_X = 5;
  static int STRUCTURE_PATTERN_CHUNK_SIZE_Z = 4;

  static int STRUCTURE_PATTERN_LOWEST_Y = 64;

  @Override
  // Run in every chunk
  public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {

    int relativePatternX = Math.abs((chunkX % STRUCTURE_PATTERN_CHUNK_SIZE_X) + 1);
    int relativePatternZ = Math.abs((chunkZ % STRUCTURE_PATTERN_CHUNK_SIZE_Z) + 1);

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
      chunkData.setBlock(8, STRUCTURE_PATTERN_LOWEST_Y + 16,  11, Material.CUT_COPPER);
    }

    // Screenshot 2 of othe chunk (left)
    if (relativePatternX == 2 && relativePatternZ == 4) {
      chunkData.setBlock(7, STRUCTURE_PATTERN_LOWEST_Y + 16, 0, Material.CUT_COPPER);
    }

    // Screenshot 3 - Right ✅
    if (relativePatternX == 2 && relativePatternZ == 3) {
      chunkData.setBlock(7, STRUCTURE_PATTERN_LOWEST_Y + 16, 6, Material.WAXED_EXPOSED_COPPER);
    }

    // Screenshot 4 - Opposite ✅
    if (relativePatternX == 4 && relativePatternZ == 1) {
      chunkData.setBlock(13, STRUCTURE_PATTERN_LOWEST_Y + 16,  11, Material.CUT_COPPER);
    }

    // Screenshot 5 - Above ✅
    if (relativePatternX == 2 && relativePatternZ == 1) {
      chunkData.setBlock(6, STRUCTURE_PATTERN_LOWEST_Y + 28 + 10, 11, Material.BARRIER);
      chunkData.setBlock(6, STRUCTURE_PATTERN_LOWEST_Y + 28 + 10 + 3, 11, Material.CUT_COPPER);
    }
  }
}
