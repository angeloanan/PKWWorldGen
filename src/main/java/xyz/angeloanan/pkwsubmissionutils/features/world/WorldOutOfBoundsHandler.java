package xyz.angeloanan.pkwsubmissionutils.features.world;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.angeloanan.pkwsubmissionutils.utils.Plot;

public class WorldOutOfBoundsHandler implements Listener {
  public static final int MAX_PLOT_X_COORD = Plot.MAX_PLOT_X * Plot.PLOT_CHUNK_SIZE_X * 16;
  public static final int MAX_PLOT_Z_COORD = Plot.MAX_PLOT_Z * Plot.PLOT_CHUNK_SIZE_Z * 16;

  @EventHandler
  public void onPlayerMoveOutOfBounds(PlayerMoveEvent e) {
    if (e.getPlayer().getLocation().getX() > MAX_PLOT_X_COORD || e.getPlayer().getLocation().getZ() > MAX_PLOT_Z_COORD) {
      e.getPlayer().sendActionBar(
        Component
          .text("You are out of bounds!")
          .color(TextColor.color(0xFF5555))
      );
      e.setCancelled(true);
    }
  }
}
