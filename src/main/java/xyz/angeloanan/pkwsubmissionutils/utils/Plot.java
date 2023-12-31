package xyz.angeloanan.pkwsubmissionutils.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Plot {
  public static final int PLOT_CHUNK_SIZE_X = 5;
  public static final int PLOT_CHUNK_SIZE_Z = 4;
  public static final int STRUCTURE_LOWEST_Y = 64;

  public static final int MAX_PLOT_X = 10_000;

  public static final int MAX_PLOT_Z = 10_000;

  public static final Vector PLAYER_PLOT_OFFSET = new Vector(14.0, 0.0, 20.0);

  /// The X coordinate of the plot
  /// Starting from 0, adding 1 for every (PlotChunkSizeX * 16)
  public int x;

  /// The Z coordinate of the plot
  /// Starting from 0, adding 1 for every (PlotChunkSizeZ * 16)
  public int z;

  public Plot(int x, int z) {
    this.x = x;
    this.z = z;
  }

  /// Loc may be anywhere in the plot
  public static Location goldBlock(Location loc) {
    Plot plot = from(loc);

    return goldBlock(plot, loc.getWorld());
  }

  public static Location goldBlock(Plot plot, World world) {
    return new Location(
      world,
      plot.x * (PLOT_CHUNK_SIZE_X * 16),
      STRUCTURE_LOWEST_Y + 11,
      plot.z * (PLOT_CHUNK_SIZE_Z * 16) + 11
    );
  }

  public static Plot from(Location loc) {
    int x = loc.getBlockX() >= 0
      ? loc.getBlockX() / (PLOT_CHUNK_SIZE_X * 16)
      : (loc.getBlockX() / (PLOT_CHUNK_SIZE_X * 16)) - 1;
    int z = loc.getBlockZ() >= 0
      ? loc.getBlockZ() / (PLOT_CHUNK_SIZE_Z * 16)
      : (loc.getBlockZ() / (PLOT_CHUNK_SIZE_Z * 16)) - 1;

    return new Plot(x, z);
  }

  // ---

  @Override
  /// Only compares the plot coordinate
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Plot pkwPlotId = (Plot) o;
    return x == pkwPlotId.x && z == pkwPlotId.z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, z);
  }
}
