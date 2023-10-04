package xyz.angeloanan.pkwsubmissionutils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PKWOutOfBoundsHandler implements Listener {
  public static final int MAX_PLOT_X_COORD = PKWPlot.MAX_PLOT_X * PKWPlot.PLOT_CHUNK_SIZE_X * 16;
  public static final int MAX_PLOT_Z_COORD = PKWPlot.MAX_PLOT_Z * PKWPlot.PLOT_CHUNK_SIZE_Z * 16;

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
