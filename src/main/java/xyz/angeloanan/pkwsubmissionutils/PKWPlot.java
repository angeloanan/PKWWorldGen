package xyz.angeloanan.pkwsubmissionutils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class PKWPlot {
  public static final int PLOT_CHUNK_SIZE_X = 5;
  public static final int PLOT_CHUNK_SIZE_Z = 4;
  public static final int STRUCTURE_LOWEST_Y = 64;


  /// The X coordinate of the plot
  /// Starting from 0, adding 1 for every (PlotChunkSizeX * 16)
  public int x;

  /// The Z coordinate of the plot
  /// Starting from 0, adding 1 for every (PlotChunkSizeZ * 16)
  public int z;

  public PKWPlot(int x, int z) {
    this.x = x;
    this.z = z;
  }

  /// Loc may be anywhere in the plot
  public static Location goldBlock(Location loc) {
    PKWPlot plot = from(loc);

    return goldBlock(plot, loc.getWorld());
  }

  public static Location goldBlock(PKWPlot plot, World world) {
    return new Location(
      world,
      plot.x * (PLOT_CHUNK_SIZE_X * 16),
      STRUCTURE_LOWEST_Y + 11,
      plot.z * (PLOT_CHUNK_SIZE_Z * 16) + 11
    );
  }

  public static PKWPlot from(Location loc) {
    int x = loc.getBlockX() >= 0
      ? loc.getBlockX() / (PLOT_CHUNK_SIZE_X * 16)
      : (loc.getBlockX() / (PLOT_CHUNK_SIZE_X * 16)) - 1;
    int z = loc.getBlockZ() >= 0
      ? loc.getBlockZ() / (PLOT_CHUNK_SIZE_Z * 16)
      : (loc.getBlockZ() / (PLOT_CHUNK_SIZE_Z * 16)) - 1;

    return new PKWPlot(x, z);
  }

  // ---

  @Override
  /// Only compares the plot coordinate
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PKWPlot pkwPlotId = (PKWPlot) o;
    return x == pkwPlotId.x && z == pkwPlotId.z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, z);
  }
}
